package fungorium.view.buttons;

import javax.swing.JButton;

import fungorium.view.frames.MainFrame;

import java.awt.event.ActionEvent;

public class ButtonFactory {

    private ButtonFactory() {
        throw new IllegalStateException("Static class, cannot be instantiated");
    }

    /**
     * Készít egy új gombot, mely testreszabható a konstruktorban
     * @param title A gomb neve
     * @param runOnPress A függvény amit lenyomáskor futtat
     * @return Egy új gomb
     */
    public static JButton getNewButton(String title, Runnable runOnPress) {
        JButton button = new JButton(title);
        if(runOnPress == null) {
            button.addActionListener((ActionEvent e) -> System.err.println("Nincs függvény megadva a gombhoz!"));
        } else {
            button.addActionListener((ActionEvent e) -> runOnPress.run());
        }
        return button;
    }
}
