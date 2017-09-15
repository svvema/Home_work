import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainWindow extends JFrame{
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    public static String name = "Anon";
    public static JTextArea jta;
    private JTextField jTextArea;
    private DataInputStream in;
    private DataOutputStream out;
    private String hMes ="Type here your message..." ;

    public  MainWindow(){

        try {
            socket = new Socket(SERVER_ADDR,SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Chat#1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(200,200);
        setSize(400,600);
        setResizable(true);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        jta.setFont(new Font("Courier New",Font.CENTER_BASELINE,16));
        jta.setBackground(new Color(255,153,102));

        JScrollPane jsp = new JScrollPane(jta);

        JPanel jp1 = new JPanel(new BorderLayout());
        add(jp1);
        jp1.setPreferredSize(new Dimension(280,450));
        jp1.add(jsp);

        JPanel jp2 = new JPanel(new BorderLayout());
        add(jp2);
        jp2.setPreferredSize(new Dimension(300,50));

        jTextArea = new JTextField();
        jTextArea.setFont(new Font("Courier New",Font.CENTER_BASELINE,16));
        jTextArea.setBackground(new Color(204, 255, 153));

        JScrollPane jspTextArea = new JScrollPane(jTextArea);
        jp2.add(jspTextArea);

        JButton jb = new JButton("Send");
        jb.setBackground(Color.green);
        jp2.add(jb,BorderLayout.EAST);

        jTextArea.setText(hMes);

        jTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (jTextArea.getText().equals(hMes))
                jTextArea.setText("");
            }
        });

        jTextArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextArea.getText().equals(""))jta.append("Write something\n");else
                if (!jTextArea.getText().equals(hMes)){
                    sendMsg();
                }
            }
        });

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextArea.getText().equals(hMes))jta.append("Write something\n");else
                if (!jTextArea.getText().equals(hMes)){
                    sendMsg();
                }
                jTextArea.grabFocus();
            }
        });

        jTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextArea.getText().equals(hMes))
                    jTextArea.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
               if (jTextArea.getText().equals(""))
                jTextArea.setText(hMes);
            }
        });

        Thread threadMessager = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true){
                            String w = in.readUTF();
                            if (w.equalsIgnoreCase("end"))break;

                            jta.append(w + "\n");
                    }
                }
                catch (SocketException e){//here KOSTL
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadMessager.start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                close();
             //  threadMessager.stop();
            }
        });

        JMenuBar mainMenu = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mEdit = new JMenu("Edit");
        JMenuItem mFileExit = new JMenuItem("Exit");
        JMenuItem mEditClear = new JMenuItem("Clear");
        JMenuItem mEditName = new JMenuItem("Set Name");
        setJMenuBar(mainMenu);
        mainMenu.add(mFile);

        mainMenu.add(mEdit);
        mFile.add(mFileExit);
        mEdit.add(mEditClear);
        mEdit.add(mEditName);

        mFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                System.exit(0);
            }
        });

        mEditClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            jta.setText("");
            }
        });
        NameWindow nw = new NameWindow();
        nw.setVisible(false);
        mEditName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (!nw.isVisible())
                nw.setVisible(true);else nw.setVisible(false);
            }
        });

        setVisible(true);
    }


    public String time(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    public void sendMsg(){
        try {
            out.writeUTF((time() + " " + name + ": " + jTextArea.getText()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jTextArea.setText("");
    }
    public void close(){
        try {
            out.writeUTF("/end");

            out.flush();
            socket.close();
            out.close();
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
