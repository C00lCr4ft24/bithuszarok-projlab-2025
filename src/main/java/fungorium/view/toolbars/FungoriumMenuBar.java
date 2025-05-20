package fungorium.view.toolbars;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FungoriumMenuBar extends JMenuBar {
    private static final JMenu helpMenu = new JMenu("Segítség");
    private static final JMenuItem startDebugCommandHandler = new JMenuItem("Debug parancskezelő indítása");
    private static final JMenuItem helper = new JMenuItem("Súgó");
    public FungoriumMenuBar() {
        helpMenu.add(helper);
        helpMenu.add(startDebugCommandHandler);
        this.add(helpMenu);
    }
}
