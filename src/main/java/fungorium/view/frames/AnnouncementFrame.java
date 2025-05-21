package fungorium.view.frames;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class AnnouncementFrame extends JFrame {
    public static final JTextArea textArea = new JTextArea();
    public AnnouncementFrame(String message) {
        textArea.setText(message);
        this.add(textArea);
        this.setSize(480,640);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
