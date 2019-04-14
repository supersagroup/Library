package managerUi;

import login.Button;
import login.NonopaquePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerUi extends JFrame {
    private Image background=new ImageIcon("managerUi.jpg").getImage();
    public ManagerUi(){
        this.add(new JPanel(){
            protected void paintComponent(Graphics g){
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            {
                this.setLayout(new GridLayout(3, 2));
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("borrow"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //借书
                                    }
                                });
                            }
                        });
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("return"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //还书
                                    }
                                });
                            }
                        });
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("add book"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //添书
                                    }
                                });
                            }
                        });
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("rmv book"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //
                                    }
                                });
                            }
                        });
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("pay fine"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //
                                    }
                                });
                            }
                        });
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("print log"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
    public static void run(JFrame jf, int width, int height){
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(width, height);
    }
    public static void main(String[] args){
        run(new ManagerUi(), 300, 300);
    }
}
