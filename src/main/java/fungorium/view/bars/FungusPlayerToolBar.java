package fungorium.view.bars;

import javax.swing.JToolBar;

import fungorium.view.buttons.GrowFungusButton;
import fungorium.view.buttons.GrowMyceliumButton;
import fungorium.view.buttons.SpreadSporesButton;

public class FungusPlayerToolBar extends JToolBar {
    public FungusPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);
        
        this.add(new GrowMyceliumButton());
        this.add(new GrowFungusButton());
        this.add(new SpreadSporesButton());
    }
}
