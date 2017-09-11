import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Админ on 11.09.2017.
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Server start");
            socket = server.accept();
            System.out.println("Client connected");
            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            while (true)
            {
                String str = sc.nextLine();
                if (str.equals("end"))break;
                pw.println("echo: "+str);
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println("Server init error");
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
