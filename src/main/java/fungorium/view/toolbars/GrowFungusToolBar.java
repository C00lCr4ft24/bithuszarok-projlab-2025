package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;

/**
 * A GrowFungusToolBar osztály a gombatest növesztéséhez szükséges eszköztárat reprezentálja.
 */
public class GrowFungusToolBar extends JToolBar {
    public static final JComboBox<MyceliumJunction> availableMyceliumJunctions = new JComboBox<>();
    public static final JComboBox<String> growMode = new JComboBox<>();

    /**
     * A GrowFungusToolBar osztály konstruktora, amely beállítja a gombatest növesztéséhez szükséges eszköztárat.
     */
    public GrowFungusToolBar() {
        this.add(new JLabel("Válassz egy gombafonal csomópontot célnak a gombatest növesztéshez: "));
        this.add(availableMyceliumJunctions);
        this.add(new JLabel(" Válassz egy módszert a növesztéshez: "));
        growMode.addItem("Spórából");
        growMode.addItem("Elkábított rovarból");
        this.add(growMode);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::growFungusConfirmPressed));


        this.setFloatable(false);
        this.setVisible(false);
    }
}
