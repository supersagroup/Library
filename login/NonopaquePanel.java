package login;

import javax.swing.*;

public class NonopaquePanel extends JPanel {
    public NonopaquePanel(){
        super();
        this.setOpaque(false);
        this.setBackground(null);
    }
}
