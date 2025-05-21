package fungorium.view.toolbars;

import fungorium.view.frames.HelperFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A FungoriumMenuBar osztály a menüsor megjelenítéséért felelős.
 */
public class FungoriumMenuBar extends JMenuBar {
    private static final JMenu helpMenu = new JMenu("Segítség");

    private static final JMenuItem helper = new JMenuItem("Súgó");
    private static final HelperFrame helperFrame = new HelperFrame();

    /**
     * A FungoriumMenuBar osztály konstruktora, amely beállítja a menüsor elemeit.
     */
    public FungoriumMenuBar() {
        helper.addActionListener((ActionEvent e) -> helperFrame.setVisible(true));
        helpMenu.add(helper);

        this.add(helpMenu);
    }
}
