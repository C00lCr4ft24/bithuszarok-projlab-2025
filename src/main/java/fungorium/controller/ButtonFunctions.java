package fungorium.controller;

import fungorium.view.frames.MainFrame;

public class ButtonFunctions {

    public static void growFungusButtonPressed() {
        System.out.println("GrowFungusButton pressed");

        MainFrame.getGrowMyceliumToolBar().setVisible(false);
        MainFrame.getSpreadSporesToolBar().setVisible(false);

        MainFrame.getGrowFungusToolBar().setVisible(true);
    }
}
