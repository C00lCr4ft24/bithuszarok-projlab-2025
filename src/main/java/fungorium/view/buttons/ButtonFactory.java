package fungorium.view.buttons;

import javax.swing.JButton;

import fungorium.Main;
import fungorium.view.frames.MainFrame;

import java.awt.event.ActionEvent;

public class ButtonFactory {

    
    private ButtonFactory() { throw new IllegalStateException("Static class, cannot be instantiated"); }

    /**
     * Visszaad egy gombot, ami a gombatest növesztés menüjét jeleníti meg ha megnyomják
     * @return előre felparaméterezett gomb
     */
    public static JButton getGrowFungusButton() {
        JButton button = new JButton("Grow Fungus");
        
        button.addActionListener((ActionEvent e) -> {
            System.out.println("GrowFungusButton pressed");

            MainFrame.getGrowMyceliumToolBar().setVisible(false);

            MainFrame.getGrowFungusToolBar().setVisible(true);
        });
        
        return button;
    }

    /**
     * Visszaad egy gombot, ami a gombafonal növesztés menüjét jeleníti meg ha megnyomják
     * @return előre felparaméterezett gomb
     */
    public static JButton getGrowMyceliumButton() {
        JButton button = new JButton("Grow Mycelium");

        button.addActionListener((ActionEvent e) -> {
            System.out.println("GrowMyceliumButton pressed");

            MainFrame.getGrowFungusToolBar().setVisible(false);

            MainFrame.getGrowMyceliumToolBar().setVisible(true);

        });

        return button;
    }

    /**
     * Visszaad egy gombot, ami a spóra szórás menüjét jeleníti meg ha megnyomják
     * @return előre felparaméterezett gomb
     */
    public static JButton getSpreadSporesButton() {
        JButton button = new JButton("Spread Spores");

        button.addActionListener((ActionEvent e) -> {

        });
        return button;
    }

    /**
     * Visszaad egy gombot, ami a gombafonal vágás menüjét jeleníti meg ha megnyomják
     * @return előre felparaméterezett gomb
     */
    public static JButton getCutMyceliumButton() {
        JButton button = new JButton("Cut Mycelium");

        button.addActionListener((ActionEvent e) -> {

        });
        return button;
    }

    /**
     * Visszaad egy gombot, ami a spóra evés menüjét jeleníti meg ha megnyomják
     * @return előre felparaméterezett gomb
     */
    public static JButton getEatSporesButton() {
        JButton button = new JButton("Eat Spores");

        button.addActionListener((ActionEvent e) -> {

        });
        return button;
    }

    /**
     * Visszaad egy gombot, ami a rovar mozgás menüjét jeleníti meg ha megnyomják
     * @return előre felparaméterezett gomb
     */
    public static JButton getMoveInsectButton() {
        JButton button = new JButton("Move Insect");

        button.addActionListener((ActionEvent e) -> {

        });
        return button;
    }

}
