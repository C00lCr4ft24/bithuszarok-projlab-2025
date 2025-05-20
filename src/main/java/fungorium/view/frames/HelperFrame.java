package fungorium.view.frames;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelperFrame extends JFrame {
    private static final String rulesDescription = "Szabályok";
    public HelperFrame() {
        this.add(new JTextArea(rulesDescription));
        this.setSize(480,640);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
}
