package managerUi;

import login.Book;
import login.NonopaquePanel;

import javax.swing.*;
import java.awt.*;

public class PrintUi extends BackgroundFrame{
    Book[] books;
    public PrintUi(/*Book[] books*/){
        this.books=books;
        backgroundPanel.setLayout(new GridLayout(6, 1));
        backgroundPanel.add(new NonopaquePanel(){
            {
                this.setLayout(new GridLayout(1, 6));
                this.add(new JLabel("name"));
                this.add(new JLabel("writer"));
                this.add(new JLabel("publisher"));
                this.add(new JLabel("ID"));
                this.add(new JLabel("booklocation"));
                this.add(new JLabel("borrowed"));
            }
        });
        for(int i=0;i<5;i++){
            backgroundPanel.add(new NonopaquePanel(){
                {
                    this.setLayout(new GridLayout(1, 6));
                    this.add(new JLabel("name"));
                    this.add(new JLabel("writer"));
                    this.add(new JLabel("publisher"));
                    this.add(new JLabel("ID"));
                    this.add(new JLabel("booklocation"));
                    this.add(new JLabel("borrowed"));
                }
            });
        }
    }
    public static void main(String[] args){
        ManagerUi.run(new PrintUi(), 600, 300);
    }
}
