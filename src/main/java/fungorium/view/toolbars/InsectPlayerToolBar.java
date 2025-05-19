package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JToolBar;

import fungorium.view.buttons.ButtonFactory;

public class InsectPlayerToolBar extends JToolBar {
    private JButton cutMyceliumButton;
    private JButton moveInsectButton;
    private JButton eatSporeButton;

    public InsectPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        cutMyceliumButton = ButtonFactory.getCutMyceliumButton();
        moveInsectButton = ButtonFactory.getMoveInsectButton();
        eatSporeButton = ButtonFactory.getEatSporesButton();
        
        this.add(cutMyceliumButton);
        this.add(moveInsectButton);
        this.add(eatSporeButton);
    }
    
    public JButton getCutMyceliumButton() {
        return cutMyceliumButton;
    }
    public JButton getMoveInsectButton() {
        return moveInsectButton;
    }
    public JButton getEatSporeButton() {
        return eatSporeButton;
    }
}
