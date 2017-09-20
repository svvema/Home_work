package com.company.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;
    private boolean timeout = false;
    private boolean login = false;


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
            Thread timer = new Thread(){
                @Override
                public void run(){
                    int count = 0;

                    while (true) {
                        count++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                        if (count == 10) {if (login)break;
                            System.out.println("TIME OUT \nClient disconnected");
                            timeout = true;

                            break;
                        }
                    }
                }};
            timer.start();
            try {

                //auth start

                    while (!timeout) {
                        String str = null;
                        System.out.println("Wait login");
                        try {
                            str = in.readUTF();
                            if (timeout)break;
                            if (str.startsWith("/auth")) {
                                String[] parts = str.split(" ");
                                String nick = null;
                                if (parts.length>1)
                                nick = server.getAuthService().getNickByLoginPass(parts[1], parts[2]);

                                if (nick != null) {
                                    if (!server.isNickBusy(nick)) {
                                        sendMessage("/authok " + nick);
                                        name = nick;
                                        server.broadcast(time() + " " + name + " join to chat");
                                        server.subscribe(this);
                                        login = true;
                                        System.out.println("Client logged");
                                        timer.interrupt();
                                        break;
                                    } else sendMessage("Account already in use");
                                } else {sendMessage("Wrong login or password");
                                    System.out.println("Wrong login or password");}
                            } else sendMessage("Login please");
                        } catch (EOFException e) {}
                    }//auth end
                    if (timeout) {
                        sendMessage("Time out! \nTry reconnect(File/reconnect)");
                    Thread.currentThread().interrupt();
                        System.out.println("timeout");
                    }

                    while (true) {
                        String str = null;
                        try {
                            str = in.readUTF();
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
                            } else
                                // whisper
                                if (str.startsWith("/w")) {//w nick msg
                                    String[] parts = str.split(" ", 3);
                                    String nameTo = parts[1];
                                    String msg = parts[2];
                                    server.sendMessageTo(this, nameTo, time() + " " + msg);
                                } else
                                    server.broadcast(time() + " " + name + ": " + str);
                        } catch (EOFException e) {

                        }
                    }
                System.out.println("Client close connection");

            }catch (SocketException e){

            }
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                server.unsubscribe(this);
                server.broadcast(time() + " " + name + " left chat");
                try {
                    sendMessage("Something totally wrong");
                    socket.close();
                }catch (SocketException e){}
                catch (IOException e) {

                }
            }
        }).start();

    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        }catch (SocketException e){}
        catch (IOException e) {
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
