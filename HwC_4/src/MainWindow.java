import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainWindow extends JFrame{
    public static String name = "Anon";
    public static  JTextArea jta = new JTextArea();
    public static boolean close = false;
    public  MainWindow(){
        setTitle("Chat#1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(200,200);
        setSize(400,600);
        setResizable(true);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JPanel jp1 = new JPanel();
        add(jp1);

        jp1.setLayout(new BorderLayout());
        jp1.setPreferredSize(new Dimension(280,450));
       // JTextArea jta = new JTextArea();
        jta.setFont(new Font("Courier New",Font.CENTER_BASELINE,16));
        jta.setBackground(new Color(255,153,102));
        JScrollPane jsp = new JScrollPane(jta);
        jta.setEditable(false);
        jp1.add(jsp);



        JPanel jp2 = new JPanel();
        add(jp2);
        jp2.setLayout(new BorderLayout());
        jp2.setPreferredSize(new Dimension(300,50));
        JTextField jTextArea = new JTextField();

        jTextArea.setFont(new Font("Courier New",Font.CENTER_BASELINE,16));
        jTextArea.setBackground(new Color(204, 255, 153));
        JScrollPane jspTextArea = new JScrollPane(jTextArea);
        jp2.add(jspTextArea);
        JButton jb = new JButton("Send");
        jb.setBackground(Color.green);
        jp2.add(jb,BorderLayout.EAST);
        String hMes ="Type here your message..." ;
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
                jta.append(time()+ " " + name + ": " + jTextArea.getText() + "\n");
                jTextArea.setText("");
               // jTextArea.setText(hMes);
                     }
            }
        });

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextArea.getText().equals(hMes))jta.append("Write something\n");else
                if (!jTextArea.getText().equals(hMes)){
                jta.append(time() + " " + name + ": " + jTextArea.getText() + "\n");
                jTextArea.setText("");
     //          jTextArea.setText(hMes);

                }
                jTextArea.requestFocus();
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
}
