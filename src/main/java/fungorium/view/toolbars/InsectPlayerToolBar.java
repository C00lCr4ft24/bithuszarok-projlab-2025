package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JToolBar;

import fungorium.controller.ButtonFunctions;
import fungorium.view.buttons.ButtonFactory;

public class InsectPlayerToolBar extends JToolBar {
    private static final JButton cutMyceliumButton = ButtonFactory.getNewButton("Cut Mycelium",
            ButtonFunctions::cutMyceliumButtonPressed);
    private static final JButton moveInsectButton = ButtonFactory.getNewButton("Move Insect",
            ButtonFunctions::moveInsectButtonPressed);
    private static final JButton eatSporeButton = ButtonFactory.getNewButton("Eat Spore",
            ButtonFunctions::eatSporeButtonPressed);

    public InsectPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        cutMyceliumButton.setEnabled(false);
        moveInsectButton.setEnabled(false);
        eatSporeButton.setEnabled(false);

        this.add(cutMyceliumButton);
        this.add(moveInsectButton);
        this.add(eatSporeButton);
    }

    public static JButton getCutMyceliumButton() {
        return cutMyceliumButton;
    }

    public static JButton getMoveInsectButton() {
        return moveInsectButton;
    }

    public static JButton getEatSporeButton() {
        return eatSporeButton;
    }
}
