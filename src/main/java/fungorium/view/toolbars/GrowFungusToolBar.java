package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowFungusToolBar extends JToolBar {
    
    public GrowFungusToolBar() {
        this.add(new JLabel("Válassz egy tektont célnak a gombatest növesztéshez: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::growFungusConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
