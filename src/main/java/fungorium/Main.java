package fungorium;

import fungorium.view.frames.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
        //GameModel game = new GameModel();
        //TestFramework.testMenu(game);
        //TestFramework.runTest("test_1", game);
    }
}