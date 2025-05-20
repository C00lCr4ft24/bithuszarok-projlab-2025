package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.model.Insect;
import fungorium.model.tecton.Tecton;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class MoveInsectToolBar extends JToolBar {
    public static final JComboBox<Insect> availableInsectsComboBox = new JComboBox<>();
    public static final JComboBox<Tecton> availableTectonsComboBox = new JComboBox<>();

    public MoveInsectToolBar() {
        this.add(new JLabel("Válassz egy rovart: "));
        this.add(availableInsectsComboBox);
        this.add(new JLabel("Válassz egy tektont célnak: "));
        this.add(availableTectonsComboBox);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::moveInsectConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
