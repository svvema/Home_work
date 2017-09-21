package com.company.Client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class MainWindow extends JFrame {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    public static String name = "Anon";
    public static String login = "";
    public static String password = "";
    public static JTextArea jta;
    public static JTextField jtf;
    private JTextField jTextArea, loginField, passField;
    private JPanel jp1, jp2, authPanel;
    private Socket socket;
    private DataInputStream in;
    public static DataOutputStream out;
    private String hMes = "Type here your message...";
    private boolean isAuthorized;
    //    private JTextArea jtaUsers;
    private JList jtaUsers;
    private JScrollPane jspUsers;
    private DefaultListModel<String> users = new DefaultListModel<>();
    public static Label label;

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;

        authPanel.setVisible(!isAuthorized);
        jp2.setVisible(isAuthorized);
        jspUsers.setVisible(isAuthorized);
        if  (isAuthorized)label.setVisible(true);else label.setVisible(false);
        if (isAuthorized) jTextArea.grabFocus();

    }

    public MainWindow() {


        connect();

        setTitle("Chat#1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(200, 200);
        setBounds(400, 300, 400, 600);
        setMinimumSize(new Dimension(400, 500));
        setResizable(true);
        //  setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        jta = new JTextArea();
        jtf = new JTextField();
        jta.setEditable(false);
        jta.setLineWrap(true);
        jta.setFont(new Font("Courier New", Font.CENTER_BASELINE, 16));
        jta.setBackground(new Color(255, 153, 102));
        JScrollPane jsp = new JScrollPane(jta);

//        jtaUsers = new JTextArea();
//        jtaUsers.setPreferredSize(new Dimension(70,1));
//        jtaUsers.setEditable(false);
//        jtaUsers.setLineWrap(true);
//        jspUsers = new JScrollPane(jtaUsers);

        jtaUsers = new JList();
        jtaUsers.setModel(users);


        jspUsers = new JScrollPane(jtaUsers);
        jspUsers.setPreferredSize(new Dimension(70, 1));


        jp2 = new JPanel();
        jp2.setPreferredSize(new Dimension(330, 50));


        jTextArea = new JTextField();
        jTextArea.setFont(new Font("Courier New", Font.CENTER_BASELINE, 16));
        jTextArea.setBackground(new Color(204, 255, 153));
        jTextArea.setPreferredSize(new Dimension(getWidth() - 100, 35));
        JScrollPane jspTextArea = new JScrollPane(jTextArea);
        jp2.add(jspTextArea, BorderLayout.CENTER);

        JButton jb = new JButton("Send");
        jb.setBackground(Color.green);
        jb.setPreferredSize(new Dimension(70, 35));
        jp2.add(jb, BorderLayout.EAST);

        jTextArea.setText(hMes);

        authPanel = new JPanel(new GridLayout(1, 3));
        loginField = new JTextField();
        passField = new JPasswordField();
        JButton jbAuth = new JButton("Login");
        authPanel.add(loginField);
        authPanel.add(passField);
        authPanel.add(jbAuth);

        add(authPanel, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);

        add(jspUsers, BorderLayout.EAST);

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
                if (jTextArea.getText().equals("")) jta.append("Write something\n");
                else if (!jTextArea.getText().equals(hMes)) {
                    sendMsg();
                }
            }
        });

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextArea.getText().equals(hMes)) jta.append("Write something\n");
                else if (!jTextArea.getText().equals(hMes)) {
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


        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        jtaUsers.setSelectionModel(m);

        jtaUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selected = jtaUsers.getSelectedIndex();
                jTextArea.setText("/w " + jtaUsers.getModel().getElementAt(selected) + " ");
                jTextArea.grabFocus();
                jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
            }
        });
//        jtaUsers.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//
//                int selected = jtaUsers.getSelectedIndex();
//                System.out.println(jtaUsers.getModel().getElementAt(selected));
//            }
//        });


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
        JMenuItem mFileReconnect = new JMenuItem("Reconnect");
        JMenuItem mEditClear = new JMenuItem("Clear");
        JMenuItem mEditName = new JMenuItem("Set Name");
        setJMenuBar(mainMenu);
        label = new Label("Your name is "+name);
        Label separator = new Label("");
        separator.setPreferredSize(new Dimension(200,1));
        mainMenu.add(mFile);
        mainMenu.add(mEdit);
        mainMenu.add(separator);
        mainMenu.add(label);
//        label.setVisible(false);

        mFile.add(mFileReconnect);
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
        mFileReconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAuthorized(false);
                close();
                connect();
            }
        });

        mEditClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText("");
            }
        });
        NameWindow nw = new NameWindow(this);
        nw.setVisible(false);
        mEditName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nw.isVisible())
                    nw.setVisible(true);
                else nw.setVisible(false);
            }
        });
        setAuthorized(false);
        setVisible(true);
    }

    public void sendMsg() {
        try {
            out.writeUTF(jTextArea.getText());
            jTextArea.setText("");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jTextArea.setText("");
    }

    public void close() {
        try {
            out.writeUTF("/end");

            out.flush();
            socket.close();
            out.close();
            in.close();

        } catch (SocketException e) {
            System.out.println("Socket " + name + " closed");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void loginSend() {
        try {
            if (!socket.isClosed())
                out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        login = loginField.getText();
        password = passField.getText();

    }

    public void connect() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            setAuthorized(false);
        }
        Thread threadMessager = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/timeout")) {
                            close();
                            break;
                        }
                        if (msg.startsWith("/authok")) {
                            String[] authmsg = msg.split(" ");
                            name = authmsg[1];
                            label.setText("Your name is "+name);
                            setAuthorized(true);
                            jta.setText("");
                            jta.append("You login as " + name + "\n");
                            

                            break;
                        } else if (!msg.startsWith("/userlist")) {
                            jta.append(msg + "\n");
                            jta.setCaretPosition(jta.getDocument().getLength());
                        }
                    }
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/")) {
                            if (msg.startsWith("/userlist")) {
                                users.clear();
                                String[] userslist = msg.split(" ");
                                for (int i = 1; i < userslist.length; i++) {
                                    users.addElement(userslist[i]);
                                }
                            }
                        } else {
                            jta.append(msg + "\n");
                            jta.setCaretPosition(jta.getDocument().getLength());
                        }
                    }

                } catch (SocketException e) {
                    System.out.println("Socket " + name + " closed");
                } catch (IOException e) {//here KOSTL
                    e.printStackTrace();
                    setAuthorized(false);
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadMessager.start();
    }
}
