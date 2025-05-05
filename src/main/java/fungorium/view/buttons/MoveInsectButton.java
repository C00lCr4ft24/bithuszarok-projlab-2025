package fungorium.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MoveInsectButton extends JButton {
    public MoveInsectButton() {

        this.setText("Move insect");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("MoveInsect pressed");
            }
        });
    }
}
