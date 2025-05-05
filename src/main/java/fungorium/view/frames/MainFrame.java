package fungorium.view.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import fungorium.view.bars.FungoriumMenuBar;
import fungorium.view.bars.InsectPlayerToolBar;

public class MainFrame extends JFrame {
    public MainFrame() {
        this.setJMenuBar(new FungoriumMenuBar());
        this.setLayout(new BorderLayout());
        
        this.add(new InsectPlayerToolBar(), BorderLayout.WEST);

        this.setSize(1280, 720);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
