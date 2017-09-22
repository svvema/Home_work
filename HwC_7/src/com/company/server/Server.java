package com.company.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private final int PORT = 8189;
    private Vector<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public Server() {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            server = new ServerSocket(PORT);
            authService = new BaseAuthService();
            authService.start();
            System.out.println("Server start");
            while (true) {
                socket = server.accept();
                new ClientHandler(socket, this);
                //subscribe(new com.company.server.ClientHandler(socket, this));
                System.out.println("Client open connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server init error");
        } finally {
            try {
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.stop();
        }
    }

    public void broadcast(String message) {
        for (ClientHandler c : clients) {
            c.sendMessage(message);
        }
    }

    public void brodcastUserList() {
        StringBuffer sb = new StringBuffer("/userlist");
        for (ClientHandler c : clients) {
            sb.append(" " + c.getName());
        }
        for (ClientHandler c : clients) {
            c.sendMessage(sb.toString());
        }
    }

    public void sendMessageTo(ClientHandler from, String to, String msg) {
        for (ClientHandler c : clients) {
            if (c.getName().equalsIgnoreCase(to)) {
                c.sendMessage(from.time() + " from " + from.getName() + ": " + msg);
                from.sendMessage(from.time() + " to " + to + " msg " + msg);
                break;
            }
        }
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler c : clients) {
            if (c.getName().equals(nick)) return true;
        }
        return false;
    }

    public void subscribe(ClientHandler c) {
        clients.add(c);
        //       brodcastUserList();
    }

    public void unsubscribe(ClientHandler c) {
        clients.remove(c);
        brodcastUserList();
    }
}
