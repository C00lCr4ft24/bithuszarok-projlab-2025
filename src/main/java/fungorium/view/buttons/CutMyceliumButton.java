package fungorium.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CutMyceliumButton extends JButton {
    public CutMyceliumButton() {
        
        this.setText("Cut mycelium");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CutMyceliumButton pressed");
            }
        });
    }
}
