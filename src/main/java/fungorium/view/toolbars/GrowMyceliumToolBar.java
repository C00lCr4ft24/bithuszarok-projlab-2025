package fungorium.view.toolbars;

import fungorium.controller.Controller;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.tecton.Tecton;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class GrowMyceliumToolBar extends JToolBar {
    public static final JComboBox<MyceliumJunction> availableJunctionsComboBox = new JComboBox<>();
    public static final JComboBox<Tecton> availableTectonsComboBox = new JComboBox<>();
    
    public GrowMyceliumToolBar() {
        this.add(new JLabel("Válassz egy gombafonal csomópontot: "));
        this.add(availableJunctionsComboBox);
        this.add(new JLabel(" Válassz egy tektont célnak a gombafonal növesztéshez: "));
        this.add(availableTectonsComboBox);
        this.add(ButtonFactory.getNewButton("Elfogad", Controller::growMyceliumConfirmPressed));

        this.setFloatable(false);
        this.setVisible(false);
    }
}
