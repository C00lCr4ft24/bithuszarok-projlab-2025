package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.tecton.Tecton;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;

/**
 * A GrowMyceliumToolBar osztály a gombafonal növesztéséhez szükséges eszköztárat reprezentálja.
 */
public class GrowMyceliumToolBar extends JToolBar {
    public static final JComboBox<MyceliumJunction> availableJunctionsComboBox = new JComboBox<>();
    public static final JComboBox<Tecton> availableTectonsComboBox = new JComboBox<>();

    /**
     * A GrowMyceliumToolBar osztály konstruktora, amely beállítja a gombafonal növesztéséhez szükséges eszköztárat.
     */
    public GrowMyceliumToolBar() {
        this.add(new JLabel("Válassz egy gombafonal csomópontot: "));
        this.add(availableJunctionsComboBox);
        this.add(new JLabel(" Válassz egy tektont célnak a gombafonal növesztéshez: "));
        this.add(availableTectonsComboBox);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::growMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
