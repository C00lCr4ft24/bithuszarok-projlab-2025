package fungorium.view.buttons;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class EatSporeButton extends JButton {
    public EatSporeButton() {

        this.setText("Eat spore");
        

        this.addActionListener((ActionEvent e) -> System.out.println("EatSporeButton pressed"));
    }
}
