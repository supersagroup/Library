package Client;


import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



public class ManagerUi extends JFrame {

    //Attandent manager;

    private Image background=new ImageIcon("managerUi.jpg").getImage();

    public ManagerUi(String id){

        //manager=new Attandent(id);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

                                        run(new BorrowFrame(), 200, 200);

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

                                        run(new ReturnRemoveFrame(ReturnRemoveFrame.RETURN), 100 ,150);

                                        //if the book has fine, run PayFrame in Client

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

                                        run(new AddFrame(), 100, 300);

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

                                        run(new ReturnRemoveFrame(ReturnRemoveFrame.REMOVE), 100 ,150);

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

                                        run(new PayFineFrame(), 100, 180);

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

                                        run(new PrintFrame(), 100, 150);

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

        run(new ManagerUi("111"), 300, 300);


    }

}




class ReturnRemoveFrame extends BackgroundFrame{

    static public final int RETURN=1;

    static public final int REMOVE=2;

    private JTextField IDField=new JTextField(8);

    int pattern;

    public ReturnRemoveFrame(int p){

        pattern=p;

        backgroundPanel.setLayout(new GridLayout(2, 1));

        backgroundPanel.add(new JLabel("input book ID"));

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(IDField);

                this.add(new Button("yes"){

                    {

                        this.addActionListener(new ActionListener() {

                            @Override

                            public void actionPerformed(ActionEvent e) {

                                String ID=IDField.getText();

                                //System.out.println(ID+" "+pattern);

                                if(pattern==RETURN)

                                    ;//communicate with Server in RETURN pattern

                                if(pattern==REMOVE)

                                    ;//communicate with Server in REMOVE pattern

                                ReturnRemoveFrame.this.dispose();

                            }

                        });

                    }

                });

            }

        });

    }

}



class BorrowFrame extends BackgroundFrame{

    private JTextField stuIDField=new JTextField(8);

    private JTextField bookIDField=new JTextField(8);

    public BorrowFrame(){

        backgroundPanel.setLayout(new GridLayout(3, 1));

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("student ID"));

                this.add(stuIDField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("book ID   "));

                this.add(bookIDField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new Button("yes"){

                    {

                        this.addActionListener(new ActionListener() {

                            @Override

                            public void actionPerformed(ActionEvent e) {

                                String stuID=stuIDField.getText();

                                String bookID=bookIDField.getText();

                                System.out.println(stuID+" "+bookID);

                                //Client

                                BorrowFrame.this.dispose();

                            }

                        });

                    }

                });

            }

        });

    }

}



class AddFrame extends BackgroundFrame{

    private JTextField

            nameField=new JTextField(8),

    writerField=new JTextField(8),

    publisherField=new JTextField(8),

    IDField=new JTextField(8),

    locationField=new JTextField(8);

    public AddFrame(){

        backgroundPanel.setLayout(new GridLayout(6, 1));

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("   name   "));

                this.add(nameField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("   writer  "));

                this.add(writerField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("publisher"));

                this.add(publisherField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("     ID    "));

                this.add(IDField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new JLabel("location "));

                this.add(locationField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new Button("yes"){

                    {

                        this.addActionListener(new ActionListener() {

                            @Override

                            public void actionPerformed(ActionEvent e) {

                                String name=nameField.getText(),

                                        writer=writerField.getText(),

                                        publisher=publisherField.getText(),

                                        ID=IDField.getText(),

                                        location=locationField.getText();

                                //Client

                                AddFrame.this.dispose();

                            }

                        });

                    }

                });

            }

        });

    }

}



class PayFineFrame extends BackgroundFrame{

    private JTextField stuIDField=new JTextField(15);

    public PayFineFrame(){

        backgroundPanel.setLayout(new GridLayout(3, 1));

        backgroundPanel.add(new JLabel("input student ID"){

            {

                this.setHorizontalAlignment(JLabel.LEFT);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(stuIDField);

            }

        });

        backgroundPanel.add(new NonopaquePanel(){

            {

                this.add(new Button("yes"){

                    {

                        this.addActionListener(new ActionListener() {

                            @Override

                            public void actionPerformed(ActionEvent e) {

                                String stuID=stuIDField.getText();

                                //

                                PayFineFrame.this.dispose();

                            }

                        });

                    }

                });

            }

        });

    }

}


