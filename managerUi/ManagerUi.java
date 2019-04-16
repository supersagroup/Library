package managerUi;

import book.Book;
import book.BookLocation;
import com.alibaba.fastjson.JSONObject;
import login.Button;
import login.NonopaquePanel;
import login.TcpClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerUi extends JFrame {
    Attandent manager;
    private Image background=new ImageIcon("managerUi.jpg").getImage();
    public ManagerUi(String id){
        manager=new Attandent(id);
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
                                        run(new BorrowFrame(manager), 200, 200);
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
                                        run(new ReturnRemoveFrame(ReturnRemoveFrame.RETURN, manager), 100 ,150);
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
                                        run(new AddFrame(manager), 100, 300);
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
                                        run(new ReturnRemoveFrame(ReturnRemoveFrame.REMOVE, manager), 100 ,150);
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
                                        run(new PayFineFrame(manager), 100, 180);
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
                                        run(new PrintFrame(manager), 100, 450);
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
        //run(new PayFrame(100), 100, 150);
    }
}

class BackgroundFrame extends JFrame{
    private Image background=new ImageIcon("managerUi.jpg").getImage();
    public JPanel backgroundPanel=new JPanel(){
        protected void paintComponent(Graphics g){
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);//set login background
        }
    };
    public BackgroundFrame(){
        this.add(backgroundPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

class ReturnRemoveFrame extends BackgroundFrame{
    Attandent manager;
    static public final int RETURN=1;
    static public final int REMOVE=2;
    private JTextField IDField=new JTextField(8);
    int pattern;
    public ReturnRemoveFrame(int p, Attandent m){
        pattern=p;
        manager=m;
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
                                //
                                if(pattern==RETURN){
                                    if(m.return_book(ID)){
                                        ManagerUi.run(new BackgroundFrame(){
                                            {
                                                backgroundPanel.add(new JLabel("return success!"));
                                            }
                                        }, 100, 100);
                                        ReturnRemoveFrame.this.dispose();
                                    }else{
                                        ManagerUi.run(new BackgroundFrame(){
                                            {
                                                backgroundPanel.add(new JLabel("return fail!"));
                                            }
                                        }, 100, 100);
                                    }
                                }
                                if(pattern==REMOVE){
                                    if(m.remove_book(ID)){
                                        ManagerUi.run(new BackgroundFrame(){
                                            {
                                                backgroundPanel.add(new JLabel("remove success!"));
                                            }
                                        }, 100, 100);
                                    }else{
                                        ManagerUi.run(new BackgroundFrame(){
                                            {
                                                backgroundPanel.add(new JLabel("remove fail!"));
                                            }
                                        }, 100, 100);
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}

class BorrowFrame extends BackgroundFrame{
    Attandent manager;
    private JTextField stuIDField=new JTextField(8);
    private JTextField bookIDField=new JTextField(8);
    public BorrowFrame(Attandent m){
        manager=m;
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

                                if(manager.borrow(stuID, bookID)){
                                    ManagerUi.run(new BackgroundFrame(){
                                        {
                                            backgroundPanel.add(new JLabel("borrow success!"));
                                        }
                                    }, 100, 100);
                                }else{
                                    ManagerUi.run(new BackgroundFrame(){
                                        {
                                            backgroundPanel.add(new JLabel("borrow fails!"));
                                        }
                                    }, 100, 100);
                                }
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
    Attandent manager;
    private JTextField
            nameField=new JTextField(8),
            writerField=new JTextField(8),
            publisherField=new JTextField(8),
            IDField=new JTextField(8),
            locationField=new JTextField(8),
            lasttimeField=new JTextField(8);
    public AddFrame(Attandent m){
        manager=m;
        backgroundPanel.setLayout(new GridLayout(7, 1));
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
                this.add(new JLabel("last time"));
                this.add(lasttimeField);
            }
        });
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.add(new Button("yes"){
                    {
                        this.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String bookname=nameField.getText(),
                                        writer=writerField.getText(),
                                        publisher=publisherField.getText(),
                                        ID=IDField.getText(),
                                        location=locationField.getText();
                                String[] bookLocInfo=location.split("-");
                                int lasttime=Integer.parseInt(lasttimeField.getText());
                                if(manager.add_book(bookname, writer, publisher, ID,
                                        new BookLocation(bookLocInfo[0], bookLocInfo[1], bookLocInfo[2], bookLocInfo[3]), lasttime)){
                                    ManagerUi.run(new JFrame(){
                                        {
                                            this.add(new JLabel("add success!"));
                                        }
                                    }, 100, 100);
                                    AddFrame.this.dispose();
                                }else{
                                    ManagerUi.run(new JFrame(){
                                        {
                                            this.add(new JLabel("add fail!"));
                                        }
                                    }, 100, 100);
                                }
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
    Attandent manager;
    public PayFineFrame(Attandent m){
        manager=m;
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
                                if(manager.pay_fine(stuID)){
                                    ManagerUi.run(new JFrame(){
                                        {
                                            this.add(new JLabel("success!"));
                                        }
                                    }, 100, 100);
                                    PayFineFrame.this.dispose();
                                }else{
                                    ManagerUi.run(new JFrame(){
                                        {
                                            this.add(new JLabel("fail!"));
                                        }
                                    }, 100, 100);
                                }
                                PayFineFrame.this.dispose();
                            }
                        });
                    }
                });
            }
        });
    }
}

class PrintFrame extends BackgroundFrame{
    private JTextField bookField=new JTextField(15),
            writerField=new JTextField(15),
            publisherField=new JTextField(15),
            IDField=new JTextField(15);
    Attandent manager;
    public PrintFrame(Attandent m){
        manager=m;
        backgroundPanel.setLayout(new GridLayout(6, 1));
        backgroundPanel.add(new JLabel("input book name"){
            {
                this.setHorizontalAlignment(JLabel.LEFT);
            }
        });
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.add(bookField);
            }
        });
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.add(writerField);
            }
        });
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.add(publisherField);
            }
        });
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.add(IDField);
            }
        });
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.add(new Button("yes"){
                    {
                        this.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String bookname=bookField.getText(), writer=writerField.getText(),
                                        publisher=publisherField.getText(),ID=IDField.getText();
                                ArrayList<Book> books=manager.print_log(bookname, writer, publisher, ID);
                                ManagerUi.run(new PrintUi(books), 500, 100 + 100 * books.size());
                            }
                        });
                    }
                });
            }
        });
    }
}