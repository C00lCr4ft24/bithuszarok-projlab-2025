package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.model.Insect;
import fungorium.model.mycelium.MyceliumConnection;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;

/**
 * A CutMyceliumToolBar osztály a MyceliumConnection elvágásához szükséges eszköztárat reprezentálja.
 */
public class CutMyceliumToolBar extends JToolBar {
    public static final JComboBox<Insect> availableInsectsComboBox = new JComboBox<>();
    public static final JComboBox<MyceliumConnection> availableConnectionsComboBox = new JComboBox<>();

    /**
     * A CutMyceliumToolBar osztály konstruktora, amely beállítja a MyceliumConnection elvágásához szükséges eszköztárat.
     */
    public CutMyceliumToolBar() {
        this.add(new JLabel("Válassz egy rovart: "));
        this.add(availableInsectsComboBox);
        this.add(new JLabel(" Válassz egy elvágandó fonalat: "));
        this.add(availableConnectionsComboBox);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::cutMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
