package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPart extends JFrame implements ActionListener {
    JPanel jPanel;
    JTextField textField;
    JButton button;
    static JTextArea textArea;

    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream din;
    static DataOutputStream dout;

    public ServerPart() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(new Color(7,94,84));
        jPanel.setBounds(0,0,340,50);
        add(jPanel);

        //This code is for arrow symbol in the header
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(
                "code\\icons\\3.png"));
        Image image = icon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);

        JLabel label = new JLabel(imageIcon);
        label.setBounds(3,10,30,30);
        jPanel.add(label);

        //action listener event for arrow to exit the window
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        //this code is for uploading user image
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource(
                "code\\icons\\1.png"));
        Image image1 = icon1.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image1);

        JLabel label2 = new JLabel(imageIcon1);
        label2.setBounds(40,5,40,40);
        jPanel.add(label2);

        //this code is for video button
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource(
                "code\\icons\\video.png"));
        Image image2 = icon2.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image2);

        JLabel label5 = new JLabel(imageIcon2);
        label5.setBounds(195,14,25,25);
        jPanel.add(label5);

        //this code is for call button
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource(
                "code\\icons\\phone.png"));
        Image image3 = icon3.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon imageIcon3 = new ImageIcon(image3);

        JLabel label6 = new JLabel(imageIcon3);
        label6.setBounds(240,14,25,25);
        jPanel.add(label6);

        //this code is for cursor dot
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource(
                "code\\icons\\3icon.png"));
        Image image4 = icon4.getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT);
        ImageIcon imageIcon4 = new ImageIcon(image4);

        JLabel label7 = new JLabel(imageIcon4);
        label7.setBounds(280,15,18,18);
        jPanel.add(label7);

        //This code is for showing user name on top of the bar
        JLabel label3 = new JLabel("Asif");
        label3.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        label3.setForeground(Color.WHITE);
        label3.setBounds(100,7 ,110,20);
        jPanel.add(label3);

        //This code is for showing user active part on top of the bar
        JLabel label4 = new JLabel("active now");
        label4.setFont(new Font("SAN_SERIF",Font.PLAIN,14 ));
        label4.setForeground(Color.WHITE);
        label4.setBounds(100,24 ,90,20);
        jPanel.add(label4);



        //text area code for showing chat between two friends
        textArea = new JTextArea();
        textArea.setBounds(0,53,340,420);
        textArea.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(textArea);
        //lower text field code
        textField = new JTextField();
        textField.setBounds(3,475,240,27);
        textField.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(textField);

        //send button
        button = new JButton("sent");
        button.setBackground(new Color(7,94,84));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SEN_SERIF",Font.PLAIN,17));
        button.setBounds(249,475,87,27);
        button.addActionListener(this::actionPerformed);
        add(button);

        setLayout(null);
        setSize(340,510);
        setLocation(200,150);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try {
            String message = textField.getText();
            textArea.setText(textArea.getText()+"\t\t\t\t"+message);
            textArea.setText(textArea.getText()+"\n");
            dout.writeUTF(message);
            textField.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerPart().setVisible(true);

        String msginput = "";
        try{
            serverSocket = new ServerSocket(6001);
            while(true){
                socket = serverSocket.accept();
                din = new DataInputStream(socket.getInputStream());
                dout = new DataOutputStream(socket.getOutputStream());

                while(true){
                    msginput = din.readUTF();
                    textArea.setText(textArea.getText()+"\n"+msginput);
                }

            }

        }catch(Exception e){}

    }
}
