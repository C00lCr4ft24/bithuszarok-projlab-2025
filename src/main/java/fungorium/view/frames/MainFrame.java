package fungorium.view.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import fungorium.GameModel;
import fungorium.view.FungoriumMenuBar;
import fungorium.view.FungusPlayerToolBar;
import fungorium.view.GameSettingsToolBar;
import fungorium.view.InsectPlayerToolBar;

public class MainFrame extends JFrame {
    public MainFrame() {

        GameModel gameModel = new GameModel();

        this.setJMenuBar(new FungoriumMenuBar());
        this.setLayout(new BorderLayout());
        
        this.add(new InsectPlayerToolBar(), BorderLayout.WEST);
        this.add(new FungusPlayerToolBar(), BorderLayout.EAST);
        this.add(new GameSettingsToolBar(), BorderLayout.SOUTH);
        this.setSize(720, 720);
        this.setLocationRelativeTo(null);
        //this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
