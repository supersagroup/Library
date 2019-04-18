package managerUi;


import book.Book;
import login.NonopaquePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PrintUi extends BackgroundFrame {
    ArrayList<Book> books;

    public PrintUi(ArrayList<Book> books) {
        this.books = books;
        backgroundPanel.setLayout(new GridLayout(2 + books.size(), 1));
        backgroundPanel.add(new NonopaquePanel() {
            {
                this.setLayout(new GridLayout(1, 6));
                this.add(new JLabel("name"));
                this.add(new JLabel("writer"));
                this.add(new JLabel("publisher"));
                this.add(new JLabel("ID"));
                this.add(new JLabel("booklocation"));
                this.add(new JLabel("borrow time"));
            }
        });
        if (books.size() == 0) {
            backgroundPanel.add(new NonopaquePanel() {
                {
                    this.setLayout(new GridLayout(1, 6));
                    this.add(new JLabel("null"));
                    this.add(new JLabel("null"));
                    this.add(new JLabel("null"));
                    this.add(new JLabel("null"));
                    this.add(new JLabel("null"));
                    this.add(new JLabel("null"));
                }
            });
            for(int i=0;i<9;i++)
                backgroundPanel.add(new NonopaquePanel());
        } else {
            Iterator<Book> itr = books.iterator();
            while (itr.hasNext()) {
                backgroundPanel.add(new NonopaquePanel() {
                    {
                        this.setLayout(new GridLayout(1, 6));
                        Book b = itr.next();
                        this.add(new JLabel(b.name));
                        this.add(new JLabel(b.writer));
                        this.add(new JLabel(b.publisher));
                        this.add(new JLabel(b.ID));
                        this.add(new JLabel(b.location.toString()));
                        this.add(new JLabel(b.borrowed));
                    }
                });
            }
        }
    }

    public static void main(String[] args) {
        //ManagerUi.run(new PrintUi(), 600, 300);
    }
}
