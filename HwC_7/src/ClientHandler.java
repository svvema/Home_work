import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Админ on 15.09.2017.
 */
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
//                while (true){
//                    String str = in.readUTF();
//
//                }
                while (true){
                    String str = in.readUTF();
                    if (str.equalsIgnoreCase("/end"))break;
                    System.out.println(" client: " + str);
                    server.broadcast(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                server.unsubscribe(this);
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
}
