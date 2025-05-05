package fungorium.view.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class CutMyceliumButton extends JButton {
    public CutMyceliumButton() {
        
        this.setText("Cut mycelium");

        this.addActionListener((ActionEvent e) -> System.out.println("CutMyceliumButton pressed"));
    }
}
