package fungorium.view.toolbars;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import fungorium.controller.ButtonFunctions;
import fungorium.model.Insect;
import fungorium.view.buttons.ButtonFactory;

public class EatSporeToolBar extends JToolBar {

    public static final JComboBox<Insect> availableInsects = new JComboBox<>();
    public static final JButton confirmButton = ButtonFactory.getNewButton("Confirm",
    ButtonFunctions::eatSporeConfirmPressed);

    public EatSporeToolBar() {
        this.add(new JLabel("Select an Insect to eat a spore with: "));
        this.add(availableInsects);
        this.add(confirmButton);

        this.setFloatable(false);
        this.setVisible(false);
    }
}
