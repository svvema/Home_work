import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private final int PORT = 8189;
    private ServerSocket server;
    private Vector<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public Server() {
        try {
            server = new ServerSocket(PORT);
            Socket socket = null;
            authService = new BaseAuthService();
            authService.start();
            clients = new Vector<>();
            System.out.println("Server start");
            while (true) {
                socket = server.accept();
                new ClientHandler(socket, this);
                //subscribe(new ClientHandler(socket, this));
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server init error");
        } finally {
            try {
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

    public boolean isNickBusy(String nick) {
        for (ClientHandler c : clients) {
            if (c.getName().equals(nick)) return true;
        }
        return false;
    }

    public void subscribe(ClientHandler c) {
        clients.add(c);
    }

    public void unsubscribe(ClientHandler c) {
        clients.remove(c);
    }
}
