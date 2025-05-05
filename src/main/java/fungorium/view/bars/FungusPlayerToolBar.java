package fungorium.view.bars;

import javax.swing.JToolBar;

import fungorium.view.buttons.GrowFungusButton;
import fungorium.view.buttons.GrowMyceliumButton;
import fungorium.view.buttons.SpreadSporesButton;

public class FungusPlayerToolBar extends JToolBar {
    private GrowMyceliumButton growMyceliumButton;
    private GrowFungusButton growFungusButton;
    private SpreadSporesButton spreadSporesButton;
    
    public FungusPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        growMyceliumButton = new GrowMyceliumButton();
        growFungusButton = new GrowFungusButton();
        spreadSporesButton = new SpreadSporesButton();
        
        this.add(growMyceliumButton);
        this.add(growFungusButton);
        this.add(spreadSporesButton);
    }

    public GrowMyceliumButton getGrowMyceliumButton() {
        return growMyceliumButton;
    }
    public GrowFungusButton getGrowFungusButton() {
        return growFungusButton;
    }
    public SpreadSporesButton getSpreadSporesButton() {
        return spreadSporesButton;
    }
}
