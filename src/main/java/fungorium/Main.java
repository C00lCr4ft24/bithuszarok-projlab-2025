package fungorium;

import fungorium.view.MainFramer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFramer::new);
        //GameModel game = new GameModel();
        //TestFramework.testMenu(game);
        //TestFramework.runTest("test_1", game);
    }
}