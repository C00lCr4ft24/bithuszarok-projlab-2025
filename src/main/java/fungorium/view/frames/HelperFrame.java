package fungorium.view.frames;

import javax.swing.JFrame;
import javax.swing.JTextArea;


/**
 * A HelperFrame osztály egy új ablakot reprezentál.
 */
public class HelperFrame extends JFrame {
    private static final String rulesDescription = "Szabályok";
    
    public HelperFrame() {
        this.add(new JTextArea(rulesDescription));

        this.setTitle("Súgó");
        this.setSize(480,640);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
}
