package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class SpreadSporesToolBar extends JToolBar {

    public SpreadSporesToolBar() {
        this.add(new JLabel("Select a Tecton to spread spores on: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Confirm", Controller::spreadSporesConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
