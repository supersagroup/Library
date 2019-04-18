package login;

import javax.swing.*;

public class Button extends JButton {
    public Button(String text){
        super(text, new ImageIcon("Button.png"));
        this.setHorizontalTextPosition(JButton.CENTER);
    }
}
