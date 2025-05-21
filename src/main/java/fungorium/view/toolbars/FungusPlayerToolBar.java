package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;

/**
 * A FungusPlayerToolBar osztály a gombafonal növesztéséhez szükséges eszköztárat reprezentálja.
 */
public class FungusPlayerToolBar extends JToolBar {
    public static final JButton growMyceliumButton = ButtonFactory.getNewButton("Gombafonal növesztés",
            Controller::growMyceliumButtonPressed);
    public static final JButton growFungusButton = ButtonFactory.getNewButton("Gombatest növesztés",
            Controller::growFungusButtonPressed);
    public static final JButton spreadSporesButton = ButtonFactory.getNewButton("Spóra szórás",
            Controller::spreadSporesButtonPressed);

    /**
     * A FungusPlayerToolBar osztály a gombafonal növesztéséhez szükséges eszköztárat reprezentálja.
     */
    public FungusPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        growMyceliumButton.setEnabled(false);
        growFungusButton.setEnabled(false);
        spreadSporesButton.setEnabled(false);

        this.add(growMyceliumButton);
        this.add(growFungusButton);
        this.add(spreadSporesButton);
    }
}
