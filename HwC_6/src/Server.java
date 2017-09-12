import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static DataInputStream in;
    public static DataOutputStream out;
    public  static Scanner sc;
    public  static Scanner pw;
    public static void main(String[] args) {

    ServerSocket server = null;
    Socket socket = null;

    //init
        try {
            server = new ServerSocket(8189);
            System.out.println("Server start");
            socket = server.accept();
            System.out.println("Client connected");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);
        } catch (IOException e) {
            System.out.println("Server init error");
        }

        Socket finalSocket = socket;
        ServerSocket finalServer = server;

        Thread threadMessager = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String str = in.readUTF();
                        System.out.println("Client: " + str);
                        out.writeUTF("echo: " + str);
                        out.flush();
                        if (str.equalsIgnoreCase("end")) break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        finalSocket.close();
                        finalServer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadMessager.start();

        Thread threadConsole = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String str = sc.nextLine();
                        System.out.println("Console: " + str);
                        out.writeUTF("Console: " + str);
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadConsole.setDaemon(true);
        threadConsole.start();
        try {
            threadMessager.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
