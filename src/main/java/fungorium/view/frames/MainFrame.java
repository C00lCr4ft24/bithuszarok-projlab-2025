package fungorium.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fungorium.view.buttons.NewGameButton;
import fungorium.view.toolbars.FungoriumMenuBar;
import fungorium.view.toolbars.FungusPlayerToolBar;
import fungorium.view.toolbars.GameSettingsToolBar;
import fungorium.view.toolbars.GrowFungusToolBar;
import fungorium.view.toolbars.GrowMyceliumToolBar;
import fungorium.view.toolbars.InsectPlayerToolBar;

public class MainFrame extends JFrame {
    private static InsectPlayerToolBar insectPlayerToolBar = new InsectPlayerToolBar();
    private static FungusPlayerToolBar fungusPlayerToolBar = new FungusPlayerToolBar();
    private static GameSettingsToolBar gameSettingsToolBar = new GameSettingsToolBar();
    private static GrowFungusToolBar growFungusToolBar = new GrowFungusToolBar();
    private static GrowMyceliumToolBar growMyceliumToolBar = new GrowMyceliumToolBar();

    public MainFrame() {
        this.setJMenuBar(new FungoriumMenuBar());
        this.setLayout(new BorderLayout());

        JPanel container = new JPanel(new FlowLayout());
        
        this.add(insectPlayerToolBar, BorderLayout.WEST);
        this.add(fungusPlayerToolBar, BorderLayout.EAST);
        this.add(gameSettingsToolBar, BorderLayout.SOUTH);
        container.add(growFungusToolBar);
        container.add(growMyceliumToolBar);
        growFungusToolBar.setVisible(false);
        growMyceliumToolBar.setVisible(false);
        this.add(container, BorderLayout.NORTH);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static GrowFungusToolBar getGrowFungusToolBar() {
        return growFungusToolBar;
    }

    public static GrowMyceliumToolBar getGrowMyceliumToolBar() {
        return growMyceliumToolBar;
    }
}
