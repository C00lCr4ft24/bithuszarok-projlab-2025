package fungorium.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fungorium.view.toolbars.CutMyceliumToolBar;
import fungorium.view.toolbars.EatSporeToolBar;
import fungorium.view.toolbars.FungoriumMenuBar;
import fungorium.view.toolbars.FungusPlayerToolBar;
import fungorium.view.toolbars.GameSettingsToolBar;
import fungorium.view.toolbars.GrowFungusToolBar;
import fungorium.view.toolbars.GrowMyceliumToolBar;
import fungorium.view.toolbars.InsectPlayerToolBar;
import fungorium.view.toolbars.MoveInsectToolBar;
import fungorium.view.toolbars.SpreadSporesToolBar;

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
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
