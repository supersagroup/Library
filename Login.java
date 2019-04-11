package login;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private Image loginImage=new ImageIcon("login.jpg").getImage();//login background
    private JTextField accountField=new JTextField(15);
    private JTextField passwordField=new JTextField(15);
    public Login(){
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
                                                System.out.println(account+" "+password);
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
