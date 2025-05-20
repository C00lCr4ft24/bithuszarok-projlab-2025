package fungorium.view.toolbars;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fungorium.view.frames.HelperFrame;

import java.awt.event.ActionEvent;

public class FungoriumMenuBar extends JMenuBar {
    private static final JMenu helpMenu = new JMenu("Segítség");
    
    private static final JMenuItem helper = new JMenuItem("Súgó");
    private static HelperFrame helperFrame = new HelperFrame();
    public FungoriumMenuBar() {
        helper.addActionListener((ActionEvent e) -> helperFrame.setVisible(true));
        helpMenu.add(helper);

        this.add(helpMenu);
    }
}
