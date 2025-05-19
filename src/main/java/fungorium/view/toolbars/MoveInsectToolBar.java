package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class MoveInsectToolBar extends JToolBar {

    public MoveInsectToolBar() {
        this.add(new JLabel("Select an Insect to move: "));
        this.add(new JComboBox<>());
        this.add(new JLabel("Select a Tecton to move on: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Confirm", Controller::moveInsectConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
