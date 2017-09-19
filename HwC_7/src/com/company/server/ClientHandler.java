package com.company.server;

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
                //auth start
                while (true) {
                    String str = in.readUTF();
                    if (str.startsWith("/auth")) {
                        String[] parts = str.split(" ");
                        String nick = server.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                        if (nick != null) {
                            if (!server.isNickBusy(nick)) {
                                sendMessage("/authok " + nick);
                                name = nick;
                                server.broadcast(time() + " " + name + " join to chat");
                                server.subscribe(this);
                                break;
                            } else sendMessage("Account already in use");
                        } else sendMessage("Wrong login or password");
                    }else sendMessage("Login please");
                }//auth end
                while (true) {
                    String str = in.readUTF();
                    if (str.equalsIgnoreCase("/end")) break;

                    // nick change
                    String oldName = name;
                    if (str.startsWith("/nick")) {
                        String[] parts = str.split(" ");
                        String nick = parts[1];
                        if (!server.isNickBusy(nick)) {
                            name = nick;
                            str = "Change nick to: " + name;
                            server.broadcast(time() + " " + oldName + ": " + str);
                            server.brodcastUserList();
                            System.out.println(time() + " " + "from " + oldName + ": " + str);
                        }
                    }else
                    // whisper
                    if (str.startsWith("/w")){//w nick msg
                        String[] parts = str.split(" ",3);
                        String nameTo = parts[1];
                        String msg = parts[2];
                        server.sendMessageTo(this, nameTo,time() + " " + msg);
                    }else
                        server.broadcast(time() + " " + name + ": " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String time() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
