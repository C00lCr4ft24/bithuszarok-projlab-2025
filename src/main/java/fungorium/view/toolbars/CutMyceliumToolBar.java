package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class CutMyceliumToolBar extends JToolBar {

    public CutMyceliumToolBar() {
        this.add(new JLabel("Select a MyceliumConnetion to cut: "));
        this.add(new JComboBox<>());
        this.add(new JButton("Confirm"));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
