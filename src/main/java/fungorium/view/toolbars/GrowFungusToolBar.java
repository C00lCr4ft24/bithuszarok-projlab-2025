package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowFungusToolBar extends JToolBar {
    
    public GrowFungusToolBar() {
        this.add(new JLabel("Select a tecton to grow a fungus to: "));
        this.add(new JComboBox<>());
        this.add(new JButton("Confirm"));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
