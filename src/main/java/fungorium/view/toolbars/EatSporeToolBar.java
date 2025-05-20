package fungorium.view.toolbars;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import fungorium.controller.Controller;
import fungorium.model.Insect;
import fungorium.view.buttons.ButtonFactory;

/**
 * A EatSporeToolBar osztály a spóra evéshez szükséges eszköztárat reprezentálja.
 */
public class EatSporeToolBar extends JToolBar {

    public static final JComboBox<Insect> availableInsectsComboBox = new JComboBox<>();

    /**
     * A EatSporeToolBar osztály konstruktora, amely beállítja a spóra evéshez szükséges eszköztárat.
     */
    public EatSporeToolBar() {
        this.add(new JLabel("Válassz egy rovart a spóra evéshez: "));
        this.add(availableInsectsComboBox);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::eatSporeConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
