import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            try {
                while (true){//auth
                    String str = in.readUTF();
                    if (str.startsWith("/auth")){
                        String[] parts = str.split(" ");
                        String nick = server.getAuthService().getNickByLoginPass(parts[1],parts[2]);
                        System.out.println(nick);
                        if (nick!=null){
                            if (!server.isNickBusy(nick)){
                                sendMessage("/authok " + nick);
                                name = nick;
                                server.broadcast(time() + " " + name + " join to chat");
                                server.subscribe(this);
                                break;
                            }else sendMessage("Account already in use");
                        }else sendMessage("Wrong login or password");
                    }

                }
                while (true){
                    String str = in.readUTF();
                    if (str.equalsIgnoreCase("/end"))break;
                    System.out.println(time() + " " +  "from " + name+ ": " + str);
                    server.broadcast(time() + " " + name+ ": " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                server.unsubscribe(this);
                server.broadcast(time() + " " + name + " left chat");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void sendMessage(String message){
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getName(){
        return name;
    }
    public String time(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
