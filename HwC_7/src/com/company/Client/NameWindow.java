package com.company.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class NameWindow extends JFrame {
    public static JTextField jta;

    public NameWindow() {

        setTitle("Change name");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
//        setLocation(200,200);
        setSize(400, 100);
        setResizable(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel jp1 = new JPanel();
        add(jp1);

        jp1.setLayout(new BorderLayout());
        jp1.setPreferredSize(new Dimension(400, 50));
        jta = new JTextField();
        jta.setFont(new Font("Courier New", Font.CENTER_BASELINE, 16));
        jta.setBackground(new Color(255, 153, 102));

        jp1.add(jta);

        JButton jb = new JButton("Apply");
        jb.setBackground(Color.green);
        jp1.add(jb, BorderLayout.EAST);
        jta.setText("");

        JLabel jLabel = new JLabel("Type here your new name:");
        jp1.add(jLabel, BorderLayout.NORTH);

        jLabel.setFont(new Font("Courier New", Font.CENTER_BASELINE, 16));

        jta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jta.getText().equals(""))
                    setName();
            }
        });

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jta.getText().equals(""))
                    setName();
            }
        });

        setVisible(true);

    }

    public void setName() {
        MainWindow.name = jta.getText();
        try {
            MainWindow.out.writeUTF("/nick " + MainWindow.name);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        MainWindow.jta.append("Your name now: " + MainWindow.name + "\n");
        jta.setText("");
        setVisible(false);

    }
}
