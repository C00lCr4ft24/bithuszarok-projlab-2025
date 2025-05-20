package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class CutMyceliumToolBar extends JToolBar {

    public CutMyceliumToolBar() {
        this.add(new JLabel("Válassz egy rovart: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::cutMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
