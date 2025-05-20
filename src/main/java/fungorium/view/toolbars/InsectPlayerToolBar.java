package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JToolBar;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

public class InsectPlayerToolBar extends JToolBar {
    public static final JButton moveInsectButton = ButtonFactory.getNewButton("Rovar mozgás",
            Controller::moveInsectButtonPressed);
    public static final JButton cutMyceliumButton = ButtonFactory.getNewButton("Gombafonal vágás",
            Controller::cutMyceliumButtonPressed);
    public static final JButton eatSporeButton = ButtonFactory.getNewButton("Spóra evés",
            Controller::eatSporeButtonPressed);

    public InsectPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        moveInsectButton.setEnabled(false);
        cutMyceliumButton.setEnabled(false);
        eatSporeButton.setEnabled(false);

        this.add(moveInsectButton);
        this.add(cutMyceliumButton);
        this.add(eatSporeButton);
    }
}
