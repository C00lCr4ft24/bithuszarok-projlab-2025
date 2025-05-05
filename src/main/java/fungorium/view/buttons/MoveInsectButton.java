package fungorium.view.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class MoveInsectButton extends JButton {
    public MoveInsectButton() {

        this.setText("Move insect");

        this.addActionListener((ActionEvent e) -> System.out.println("MoveInsectButton pressed"));
    }
}
