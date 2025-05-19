package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowFungusToolBar extends JToolBar {
    
    public GrowFungusToolBar() {
        this.add(new JLabel("Select a tecton to grow a fungus to: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Confirm", Controller::growFungusConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
