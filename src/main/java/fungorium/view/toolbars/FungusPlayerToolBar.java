package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JToolBar;

import fungorium.view.buttons.ButtonFactory;

public class FungusPlayerToolBar extends JToolBar {
    private JButton growMyceliumButton;
    private JButton growFungusButton;
    private JButton spreadSporesButton;
    
    public FungusPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        growMyceliumButton = ButtonFactory.getNewButton("Grow Mycelium", null);
        growFungusButton = ButtonFactory.getNewButton("Grow Fungus", null);
        spreadSporesButton = ButtonFactory.getNewButton("Spread Spores", null);
        
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
