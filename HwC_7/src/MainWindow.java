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
    public static String name = "Anon";
    public static String login = "";
    public static String password = "";
    public static JTextArea jta;
    private JTextField jTextArea, loginField, passField;
    private JPanel jp1,jp2, authPanel;
    private Socket socket;
    private DataInputStream in;
    public static DataOutputStream out;
    private String hMes ="Type here your message..." ;
    private boolean isAuthorized;

    public void setAuthorized(boolean authorized){
        isAuthorized = authorized;

        authPanel.setVisible(!isAuthorized);
        jp2.setVisible(isAuthorized);
        if  (isAuthorized)jTextArea.grabFocus();

    }
    public  MainWindow(){

        try {
            socket = new Socket(SERVER_ADDR,SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            setAuthorized(false);
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

        jp1 = new JPanel(new BorderLayout());
        add(jp1);
        jp1.setPreferredSize(new Dimension(280,450));
        jp1.add(jsp);

        jp2 = new JPanel(new BorderLayout());
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

        authPanel = new JPanel(new GridLayout(1,3));
        loginField = new JTextField();
        passField = new JPasswordField();
        JButton jbAuth = new JButton("Login");
        authPanel.add(loginField);
        authPanel.add(passField);
        authPanel.add(jbAuth);

        add(authPanel, BorderLayout.SOUTH);

        jbAuth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               loginSend();

            }
        });
        loginField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginSend();
            }
        });
        passField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginSend();
            }
        });

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
                            String msg = in.readUTF();
                            if (msg.startsWith("/authok")){
                                setAuthorized(true);
                                jta.append("You login" + "\n");

                                break;
                            }
                            jta.append(msg + "\n");
                            jta.setCaretPosition(jta.getDocument().getLength());
                    }
                    while (true){

                        String msg = in.readUTF();
                        jta.append(msg + "\n");
                        jta.setCaretPosition(jta.getDocument().getLength());}

                }catch (SocketException e){
                    System.out.println("Socket " + name + " closed");
                }
                catch (IOException e){//here KOSTL
                    e.printStackTrace();
                    setAuthorized(false);
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadMessager.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    setAuthorized(false);
                }
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
        setAuthorized(false);
        setVisible(true);
    }

//    public void onAuthClick(){
//        try{
//            out.writeUTF("/auth " + loginField.getText() + passField.getText());
//            loginField.clear;
//            passField.clear;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    public void sendMsg(){
        try {
            out.writeUTF(jTextArea.getText());
            jTextArea.setText("");
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
    public void loginSend(){
        try {
            out.writeUTF("/auth "+ loginField.getText() +" "+ passField.getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        login = loginField.getText();
        password = passField.getText();

    }
}
