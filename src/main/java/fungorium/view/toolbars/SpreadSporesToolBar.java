package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.model.mycelium.Fungus;
import fungorium.model.tecton.Tecton;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 * A SpreadSporesToolBar osztály a spóra szórásához szükséges eszköztárat reprezentálja.
 */
public class SpreadSporesToolBar extends JToolBar {
    public static final JComboBox<Fungus> availableFungiComboBox = new JComboBox<>();
    public static final JComboBox<Tecton> availableTectonsComboBox = new JComboBox<>();

    /**
     * A SpreadSporesToolBar osztály konstruktora, amely beállítja a spóra szórásához szükséges eszköztárat.
     */
    public SpreadSporesToolBar() {
        this.add(new JLabel("Válassz egy gombatestet: "));
        this.add(availableFungiComboBox);
        this.add(new JLabel(" Válassz egy tektont célnak a spóra szóráshoz: "));
        this.add(availableTectonsComboBox);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::spreadSporesConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
