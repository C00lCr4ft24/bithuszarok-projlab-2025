package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class CutMyceliumToolBar extends JToolBar {

    public CutMyceliumToolBar() {
        this.add(new JLabel("Select a MyceliumConnetion to cut: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Confirm", Controller::cutMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
