package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JToolBar;

import fungorium.controller.ButtonFunctions;
import fungorium.view.buttons.ButtonFactory;

public class FungusPlayerToolBar extends JToolBar {
    private static final JButton growMyceliumButton = ButtonFactory.getNewButton("Grow Mycelium",
            ButtonFunctions::growMyceliumButtonPressed);
    private static final JButton growFungusButton = ButtonFactory.getNewButton("Grow Fungus",
            ButtonFunctions::growFungusButtonPressed);
    private static final JButton spreadSporesButton = ButtonFactory.getNewButton("Spread Spores",
            ButtonFunctions::spreadSporesButtonPressed);

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

    public static JButton getGrowMyceliumButton() {
        return growMyceliumButton;
    }

    public static JButton getGrowFungusButton() {
        return growFungusButton;
    }

    public static JButton getSpreadSporesButton() {
        return spreadSporesButton;
    }
}
