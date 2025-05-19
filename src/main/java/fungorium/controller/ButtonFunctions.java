package fungorium.controller;

import fungorium.GameModel;
import fungorium.view.frames.MainFrame;
import fungorium.view.toolbars.FungusPlayerToolBar;
import fungorium.view.toolbars.GameSettingsToolBar;
import fungorium.view.toolbars.InsectPlayerToolBar;

public class ButtonFunctions {

    private ButtonFunctions() { throw new IllegalStateException("Static class, cannot be instantiated"); }
    
    //JÁTÉK INDÍTÁSA

    public static void newGameButtonPressed() {
        GameModel.resetGameModel(Integer.parseInt(GameSettingsToolBar.getFungusField().getText()),
                                 Integer.parseInt(GameSettingsToolBar.getInsectField().getText()));

        FungusPlayerToolBar.getGrowMyceliumButton().setEnabled(true);
        FungusPlayerToolBar.getGrowFungusButton().setEnabled(true);
        FungusPlayerToolBar.getSpreadSporesButton().setEnabled(true);

        InsectPlayerToolBar.getCutMyceliumButton().setEnabled(true);
        InsectPlayerToolBar.getMoveInsectButton().setEnabled(true);
        InsectPlayerToolBar.getEatSporeButton().setEnabled(true);
    }

    //MŰVELETI MENÜK

    /**
     * Grow Mycelium gomb lenyomásához tartozó függvény
     */
    public static void growMyceliumButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        MainFrame.getGrowFungusToolBar().setVisible(false); 
        MainFrame.getSpreadSporesToolBar().setVisible(false);
        
        //Gombafonal növesztéshez tartozó eszköztár megjelenítése
        MainFrame.getGrowMyceliumToolBar().setVisible(true);
    }

    /**
     * Grow Fungus gomb lenyomásához tartozó függvény
     */
    public static void growFungusButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        MainFrame.getGrowMyceliumToolBar().setVisible(false);
        MainFrame.getSpreadSporesToolBar().setVisible(false);

        //Gombatest növesztéshez tartozó eszköztár megjelenítése
        MainFrame.getGrowFungusToolBar().setVisible(true);
    }

    public static void spreadSporesButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        MainFrame.getGrowFungusToolBar().setVisible(false); 
        MainFrame.getGrowMyceliumToolBar().setVisible(false);
        
        //Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.getSpreadSporesToolBar().setVisible(true);
    }

    public static void cutMyceliumButtonPressed() {
        //TODO
    }

    public static void moveInsectButtonPressed() {
        //TODO
    }

    public static void eatSporeButtonPressed() {
        //TODO
    }

    //MŰVELETEK MEGERŐSÍTÉSE
    public static void growMyceliumConfirmPressed() {
        //TODO
    }

    public static void growFungusConfirmPressed() {
        //TODO
    }

    public static void spreadSporesConfirmPressed() {
        //TODO
    }
}
