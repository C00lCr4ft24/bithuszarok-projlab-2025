package fungorium.view.bars;

import javax.swing.JToolBar;

import fungorium.view.buttons.CutMyceliumButton;
import fungorium.view.buttons.EatSporeButton;
import fungorium.view.buttons.MoveInsectButton;

public class InsectPlayerToolBar extends JToolBar {
    public InsectPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        this.add(new CutMyceliumButton());
        this.add(new MoveInsectButton());
        this.add(new EatSporeButton());
    }
}
