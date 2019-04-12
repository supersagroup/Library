package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Login extends JFrame {
    //GUI
    private Image loginImage=new ImageIcon("login.jpg").getImage();//login background
    private JTextField accountField=new JTextField(15);
    private JTextField passwordField=new JTextField(15);
    //socket
    private Socket socket;
    private String serverAddr="localhost";
    private int serverPort=10086;
    private PrintWriter pw;
    public Login(){
        try{
            socket=new Socket(serverAddr, serverPort);
            pw=new PrintWriter(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        this.add(new JPanel(){
            protected void paintComponent(Graphics g){
                g.drawImage(loginImage, 0, 0, this.getWidth(), this.getHeight(), this);//set login background
            }
            {
                this.setLayout(new GridLayout(1, 2));
                //left half of login UI
                this.add(new NonopaquePanel(){
                    {
                        this.setLayout(new GridLayout(3, 1));
                        this.add(new NonopaquePanel());
                        this.add(new NonopaquePanel(){
                            {
                                this.add(new JLabel("图书管理系统"));
                            }
                        });
                    }
                });
                //right half of login UI
                this.add(new NonopaquePanel(){
                    {
                        this.setLayout(new GridLayout(8, 1));
                        this.add(new NonopaquePanel());
                        this.add(new NonopaquePanel(){
                            {
                                this.add(new JLabel("帐号"));
                            }
                        });
                        this.add(new NonopaquePanel(){
                            {
                                this.add(accountField);
                            }
                        });
                        this.add(new NonopaquePanel(){
                            {
                                this.add(new JLabel("密码"));
                            }
                        });
                        this.add(new NonopaquePanel(){
                            {
                                this.add(passwordField);
                            }
                        });
                        //to be debug
                        this.add(new NonopaquePanel(){
                            {
                                this.add(new JButton("确定", new ImageIcon("button.png")){
                                    {
                                        this.setHorizontalTextPosition(JButton.CENTER);
                                        this.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                String account=accountField.getText();
                                                String password=passwordField.getText();

                                                pw.println(account+" "+password);
                                                pw.flush();

                                                //get line from Serer
                                                InputStream in=null;
                                                try{
                                                    in=socket.getInputStream();
                                                    BufferedReader bf=new BufferedReader(new InputStreamReader(in));
                                                    String line;
                                                    while((line=bf.readLine())!=null){
                                                        System.out.println(line);
                                                        if(line.equals("0"))
                                                            break;
                                                        if(line.equals("1")){
                                                            Login.this.dispose();
                                                            //run other GUI
                                                            break;
                                                        }
                                                    }
                                                }catch (SocketException se){
                                                }catch (Exception e1){
                                                    e1.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }/*
    public class Button extends JButton{
        private Image buttonImage=new ImageIcon("button.png").getImage();
        protected void paintComponent(Graphics g){
            g.drawImage(buttonImage, 0, 0, this.getWidth(), this.getHeight(), this);
            g.drawString(this.getText(), this.getWidth()/2, this.getHeight()/2);
        }
        public Button(String s){
            super(s);
        }
    }*/
    //Nonopaque panel
    public class NonopaquePanel extends JPanel{
        public NonopaquePanel(){
            super();
            this.setOpaque(false);
            this.setBackground(null);
        }
    }
    public static void run(JFrame jf, int width, int height){
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(width, height);
    }
    public static void main(String[] args){
        run(new Login(), 500, 300);
    }
}
