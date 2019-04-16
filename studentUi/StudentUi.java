package studentUi;

import book.Book;
import login.Button;
import login.NonopaquePanel;
import login.Student;
import managerUi.ManagerUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


public class StudentUi extends JFrame {
    Student stu;
    private Image background=new ImageIcon("managerUi.jpg").getImage();
    public StudentUi(String id){
        stu=new Student(id);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(new JPanel(){
            protected void paintComponent(Graphics g){
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            {
                this.setLayout(new GridLayout(3, 1));
                this.add(new NonopaquePanel(){
                    {
                        this.setLayout(new GridLayout(1, 2));
                        this.add(new LeftLabel("name: "+stu.getName()));
                        this.add(new LeftLabel("ID: "+stu.getId()));
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("show info"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ManagerUi.run(new ShowInfoFrame(), 600, 500);
                                    }
                                });
                            }
                        });
                    }
                });
                this.add(new NonopaquePanel(){
                    {
                        this.add(new Button("search book"){
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ManagerUi.run(new PrintFrame(), 100, 150);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
    public class ShowInfoFrame extends BackgroundFrame{
        NonopaquePanel bookPanels[]=new NonopaquePanel[stu.books.size()];
        public ShowInfoFrame(){
            backgroundPanel.setLayout(new GridLayout(12, 1));
            backgroundPanel.add(new NonopaquePanel(){
                {
                    this.setLayout(new GridLayout(1, 4));
                    this.add(new LeftLabel("name: "+stu.getName()));
                    this.add(new LeftLabel("ID: "+stu.getId()));
                    this.add(new LeftLabel("number: "));
                    this.add(new LeftLabel("fine: X"));
                }
            });
            backgroundPanel.add(new NonopaquePanel(){
                {
                    this.setLayout(new GridLayout(1, 5));
                    this.add(new LeftLabel("name"));
                    this.add(new LeftLabel("writer"));
                    this.add(new LeftLabel("publisher"));
                    this.add(new LeftLabel("ID"));
                    this.add(new LeftLabel("lasttime"));
                }
            });
            Iterator<Book> itr=stu.books.iterator();
            for(int i=0;i<bookPanels.length;i++){
                bookPanels[i]=new NonopaquePanel(){
                    {
                        Book b=itr.next();
                        this.setLayout(new GridLayout(1, 5));
                        this.add(new LeftLabel(b.name));
                        this.add(new LeftLabel(b.writer));
                        this.add(new LeftLabel(b.publisher));
                        this.add(new LeftLabel(b.ID));
                        this.add(new LeftLabel((""+b.lastTime)));
                    }
                };
                backgroundPanel.add(bookPanels[i]);
            }
        }
    }
    public static void main(String[] args){
        ManagerUi.run(new StudentUi("111"), 100, 200);
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

class LeftLabel extends JLabel{
    public LeftLabel(String s){
        super(s);
        this.setHorizontalAlignment(JLabel.LEFT);
    }
}

class PrintFrame extends BackgroundFrame {
    private JTextField bookField=new JTextField(15);
    public PrintFrame(){
        backgroundPanel.setLayout(new GridLayout(3, 1));
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
                this.add(new Button("yes"){
                    {
                        this.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String bookname=bookField.getText();
                                //Client
                            }
                        });
                    }
                });
            }
        });
    }
}