package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowMyceliumToolBar extends JToolBar {
    
    public GrowMyceliumToolBar() {
        this.add(new JLabel("Select a tecton to grow a mycelium to: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Confirm", Controller::growMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
