package fungorium.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EatSporeButton extends JButton {
    public EatSporeButton() {

        this.setText("Eat spore");
        

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("EatSporeButton pressed");
            }
        });
    }
}
