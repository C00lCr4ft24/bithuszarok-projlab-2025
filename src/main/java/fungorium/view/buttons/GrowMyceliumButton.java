package fungorium.view.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class GrowMyceliumButton extends JButton {
    public GrowMyceliumButton() {

        this.setText("Grow mycelium");
        
        this.addActionListener((ActionEvent e) -> System.out.println("GrowMyceliumButton pressed"));
    }
}
