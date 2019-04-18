package studentUi;

import book.Book;
import login.Button;
import login.NonopaquePanel;
import login.Student;
import managerUi.ManagerUi;
import managerUi.PrintUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;


public class StudentUi extends JFrame {
    Student stu;
    private Image background = new ImageIcon("Background3.jpg").getImage();

    public StudentUi(String id) {
        stu = new Student(id);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(new JPanel() {
            protected void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
            }

            {
                this.setLayout(new GridLayout(3, 1));
                this.add(new NonopaquePanel() {
                    {
                        this.setLayout(new GridLayout(1, 2));
                        try{
                        //this.add(new LeftLabel("name: "+new String(stu.getName().getBytes(), "UTF-8")));
                        //this.add(new LeftLabel("ID: " + new String(stu.getId().getBytes(), "UTF-8")));
                        this.add(new LeftLabel("name: " + stu.getName()));
                        this.add(new LeftLabel("ID: " + stu.getId()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                this.add(new NonopaquePanel() {
                    {
                        this.add(new Button("show info") {
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
                this.add(new NonopaquePanel() {
                    {
                        this.add(new Button("search book") {
                            {
                                this.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ManagerUi.run(new PrintFrame(stu), 100, 300);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public class ShowInfoFrame extends BackgroundFrame {
        NonopaquePanel bookPanels[];
        public ShowInfoFrame() {
            backgroundPanel.setLayout(new GridLayout(12, 1));
            backgroundPanel.add(new NonopaquePanel() {
                {
                    this.setLayout(new GridLayout(1, 4));
                    this.add(new LeftLabel("name: " + stu.getName()));
                    this.add(new LeftLabel("ID: " + stu.getId()));
                    this.add(new LeftLabel("number: " + stu.already_borrowed));
                    this.add(new LeftLabel("fine: " + stu.loan));
                }
            });
            backgroundPanel.add(new NonopaquePanel() {
                {
                    this.setLayout(new GridLayout(1, 5));
                    this.add(new LeftLabel("name"));
                    this.add(new LeftLabel("writer"));
                    this.add(new LeftLabel("publisher"));
                    this.add(new LeftLabel("ID"));
                    this.add(new LeftLabel("lasttime"));
                }
            });
            if (stu.books==null) {
                backgroundPanel.add(new NonopaquePanel(){
                    {
                        this.setLayout(new GridLayout(1, 5));
                        for(int i=0;i<5;i++)
                            this.add(new LeftLabel("null"));
                    }
                });
            } else {
                bookPanels = new NonopaquePanel[stu.books.size()];
                Iterator<Book> itr = stu.books.iterator();
                for (int i = 0; i < bookPanels.length; i++) {
                    bookPanels[i] = new NonopaquePanel() {
                        {
                            Book b = itr.next();
                            this.setLayout(new GridLayout(1, 5));
                            this.add(new LeftLabel(b.name));
                            this.add(new LeftLabel(b.writer));
                            this.add(new LeftLabel(b.publisher));
                            this.add(new LeftLabel(b.ID));
                            this.add(new LeftLabel(("" + b.lastTime)));
                        }
                    };
                    backgroundPanel.add(bookPanels[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        ManagerUi.run(new StudentUi("111"), 100, 200);
    }
}

class BackgroundFrame extends JFrame {
    private Image background = new ImageIcon("managerUi.jpg").getImage();
    public JPanel backgroundPanel = new JPanel() {
        protected void paintComponent(Graphics g) {
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);//set login background
        }
    };

    public BackgroundFrame() {
        this.add(backgroundPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

class LeftLabel extends JLabel {
    public LeftLabel(String s) {
        super(s);
        this.setHorizontalAlignment(JLabel.LEFT);
    }
}

class PrintFrame extends BackgroundFrame {
    Student stu;
    private JTextField bookField = new JTextField(15),
            writerField = new JTextField(15),
            pubField = new JTextField(15);

    public PrintFrame(Student s) {
        stu = s;
        backgroundPanel.setLayout(new GridLayout(8, 1));
        backgroundPanel.add(new JLabel("input book name"));
        backgroundPanel.add(new NonopaquePanel() {
            {
                this.add(bookField);
            }
        });
        backgroundPanel.add(new JLabel("input writer"));
        backgroundPanel.add(new NonopaquePanel() {
            {
                this.add(writerField);
            }
        });
        backgroundPanel.add(new JLabel("input publisher"));
        backgroundPanel.add(new NonopaquePanel() {
            {
                this.add(pubField);
            }
        });
        backgroundPanel.add(new NonopaquePanel() {
            {
                this.add(new Button("yes") {
                    {
                        this.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String bookname = bookField.getText(), wr = writerField.getText(), pub = pubField.getText();
                                try{
                                ArrayList<Book> arr = stu.search(bookname, wr, pub);
                                ManagerUi.run(new PrintUi(arr), 500, 500);
                                }catch (Exception e1){
                                    e1.printStackTrace();
                                    JOptionPane.showMessageDialog(null,"error","no such book", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}