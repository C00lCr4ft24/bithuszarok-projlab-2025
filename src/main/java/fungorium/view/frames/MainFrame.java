package fungorium.view.frames;

import fungorium.view.MapPanel;
import fungorium.view.toolbars.*;

import javax.swing.*;
import java.awt.*;

/**
 * A MainFrame osztály a fő ablakot reprezentálja, amely tartalmazza a menüt.
 */
public class MainFrame extends JFrame {
    public static final InsectPlayerToolBar insectPlayerToolBar = new InsectPlayerToolBar();

    public static final FungusPlayerToolBar fungusPlayerToolBar = new FungusPlayerToolBar();

    public static final GameSettingsToolBar gameSettingsToolBar = new GameSettingsToolBar();

    public static final GrowFungusToolBar growFungusToolBar = new GrowFungusToolBar();
    public static final GrowMyceliumToolBar growMyceliumToolBar = new GrowMyceliumToolBar();
    public static final SpreadSporesToolBar spreadSporesToolBar = new SpreadSporesToolBar();

    public static final MoveInsectToolBar moveInsectToolBar = new MoveInsectToolBar();
    public static final CutMyceliumToolBar cutMyceliumToolBar = new CutMyceliumToolBar();
    public static final EatSporeToolBar eatSporeToolBar = new EatSporeToolBar();

    public static final MapPanel mapPanel = new MapPanel();

    /**
     * A MainFrame osztály konstruktora, amely beállítja a menüt.
     */
    public MainFrame() {
        this.setJMenuBar(new FungoriumMenuBar());
        this.setLayout(new BorderLayout());

        JPanel container = new JPanel(new FlowLayout());

        this.add(insectPlayerToolBar, BorderLayout.WEST);
        this.add(fungusPlayerToolBar, BorderLayout.EAST);
        this.add(gameSettingsToolBar, BorderLayout.SOUTH);

        container.add(growFungusToolBar);
        container.add(growMyceliumToolBar);
        container.add(spreadSporesToolBar);

        container.add(moveInsectToolBar);
        container.add(cutMyceliumToolBar);
        container.add(eatSporeToolBar);

        this.add(container, BorderLayout.NORTH);

        this.add(mapPanel, BorderLayout.CENTER);

        this.setTitle("Bithuszárok - Fungorium");
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
