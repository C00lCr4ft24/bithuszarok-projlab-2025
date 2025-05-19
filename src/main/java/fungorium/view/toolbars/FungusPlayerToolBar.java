package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JToolBar;

import fungorium.controller.ButtonFunctions;
import fungorium.view.buttons.ButtonFactory;

public class FungusPlayerToolBar extends JToolBar {
    private JButton growMyceliumButton;
    private JButton growFungusButton;
    private JButton spreadSporesButton;
    
    public FungusPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        growMyceliumButton = ButtonFactory.getNewButton("Grow Mycelium", ButtonFunctions::growMyceliumButtonPressed);
        growFungusButton = ButtonFactory.getNewButton("Grow Fungus", ButtonFunctions::growFungusButtonPressed);
        spreadSporesButton = ButtonFactory.getNewButton("Spread Spores", ButtonFunctions::spreadSporesButtonPressed);
        
        this.add(growMyceliumButton);
        this.add(growFungusButton);
        this.add(spreadSporesButton);
    }

    public JButton getGrowMyceliumButton() {
        return growMyceliumButton;
    }
    public JButton getGrowFungusButton() {
        return growFungusButton;
    }
    public JButton getSpreadSporesButton() {
        return spreadSporesButton;
    }
}
