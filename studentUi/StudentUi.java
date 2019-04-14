package studentUi;

import managerUi.ManagerUi;

import javax.swing.*;
import java.awt.*;

public class StudentUi extends JFrame {
    //Student stu;
    private Image background=new ImageIcon("managerUi.jpg").getImage();
    public StudentUi(/*Student s*/){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(new JPanel(){
            protected void paintComponent(Graphics g){
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        });
    }
    public static void main(String[] args){
        ManagerUi.run(new StudentUi(), 100, 200);
    }
}

