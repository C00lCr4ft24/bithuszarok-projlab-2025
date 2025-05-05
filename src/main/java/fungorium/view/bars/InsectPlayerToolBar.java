package fungorium.view.bars;

import javax.swing.JToolBar;

import fungorium.view.buttons.CutMyceliumButton;
import fungorium.view.buttons.EatSporeButton;
import fungorium.view.buttons.MoveInsectButton;

public class InsectPlayerToolBar extends JToolBar {
    private CutMyceliumButton cutMyceliumButton;
    private MoveInsectButton moveInsectButton;
    private EatSporeButton eatSporeButton;

    public InsectPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        cutMyceliumButton = new CutMyceliumButton();
        moveInsectButton = new MoveInsectButton();
        eatSporeButton = new EatSporeButton();
        
        this.add(cutMyceliumButton);
        this.add(moveInsectButton);
        this.add(eatSporeButton);
    }
    
    public CutMyceliumButton getCutMyceliumButton() {
        return cutMyceliumButton;
    }
    public MoveInsectButton getMoveInsectButton() {
        return moveInsectButton;
    }
    public EatSporeButton getEatSporeButton() {
        return eatSporeButton;
    }
}
