package fungorium.view.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class GrowFungusButton extends JButton {
    public GrowFungusButton() {
        
        this.setText("Grow fungus");

        this.addActionListener((ActionEvent e) -> System.out.println("GrowFungusButton pressed"));
    }
}
