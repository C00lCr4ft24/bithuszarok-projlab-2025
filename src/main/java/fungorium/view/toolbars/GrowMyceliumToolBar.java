package fungorium.view.toolbars;

import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowMyceliumToolBar extends JToolBar {
    
    public GrowMyceliumToolBar() {
        this.add(new JLabel("Grow Mycelium"));

        this.setFloatable(false);
    }
}
