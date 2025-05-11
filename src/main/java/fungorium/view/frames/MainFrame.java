package fungorium.view.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import fungorium.view.bars.FungoriumMenuBar;
import fungorium.view.bars.FungusPlayerToolBar;
import fungorium.view.bars.GameSettingsToolBar;
import fungorium.view.bars.InsectPlayerToolBar;
import fungorium.view.buttons.NewGameButton;

public class MainFrame extends JFrame {
    public MainFrame() {
        this.setJMenuBar(new FungoriumMenuBar());
        this.setLayout(new BorderLayout());
        
        this.add(new InsectPlayerToolBar(), BorderLayout.WEST);
        this.add(new FungusPlayerToolBar(), BorderLayout.EAST);
        this.add(new GameSettingsToolBar(), BorderLayout.SOUTH);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
