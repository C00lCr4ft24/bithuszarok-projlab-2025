package fungorium.view.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class SpreadSporesButton extends JButton {
    public SpreadSporesButton() {

        this.setText("Spread spores");

        this.addActionListener((ActionEvent e) -> System.out.println("SpreadSporesButton pressed"));
    }
}
