package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowMyceliumToolBar extends JToolBar {
    
    public GrowMyceliumToolBar() {
        this.add(new JLabel("Válassz egy tektont célnak a gombafonal növesztéshez: "));
        this.add(new JComboBox<>());
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::growMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
