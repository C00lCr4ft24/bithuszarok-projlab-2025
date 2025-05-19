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
        GameModel.resetGameModel(Integer.parseInt(GameSettingsToolBar.fungusField.getText()),
                                 Integer.parseInt(GameSettingsToolBar.insectField.getText()));

        FungusPlayerToolBar.growMyceliumButton.setEnabled(true);
        FungusPlayerToolBar.growFungusButton.setEnabled(true);
        FungusPlayerToolBar.spreadSporesButton.setEnabled(true);

        InsectPlayerToolBar.cutMyceliumButton.setEnabled(true);
        InsectPlayerToolBar.moveInsectButton.setEnabled(true);
        InsectPlayerToolBar.eatSporeButton.setEnabled(true);
    }

    //MŰVELETI MENÜK

    private static void hideActionToolBars() {
        MainFrame.growMyceliumToolBar.setVisible(false);
        MainFrame.growFungusToolBar.setVisible(false); 
        MainFrame.spreadSporesToolBar.setVisible(false);
        MainFrame.moveInsectToolBar.setVisible(false);
        MainFrame.cutMyceliumToolBar.setVisible(false);
        MainFrame.eatSporeToolBar.setVisible(false);
    }

    /**
     * Grow Mycelium gomb lenyomásához tartozó függvény
     */
    public static void growMyceliumButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();
        
        //Gombafonal növesztéshez tartozó eszköztár megjelenítése
        MainFrame.growMyceliumToolBar.setVisible(true);
    }

    /**
     * Grow Fungus gomb lenyomásához tartozó függvény
     */
    public static void growFungusButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();
        
        //Gombafonal növesztéshez tartozó eszköztár megjelenítése
        MainFrame.growFungusToolBar.setVisible(true); 
    }

    public static void spreadSporesButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();
        
        //Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.spreadSporesToolBar.setVisible(true);
    }

    public static void cutMyceliumButtonPressed() {

        //Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();
        
        //Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.cutMyceliumToolBar.setVisible(true);
    }

    public static void moveInsectButtonPressed() {
        //Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();
        
        //Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.moveInsectToolBar.setVisible(true);
    }

    public static void eatSporeButtonPressed() {
        //Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();
        
        //Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.eatSporeToolBar.setVisible(true);
    }

    //MŰVELETEK MEGERŐSÍTÉSE
    public static void growMyceliumConfirmPressed() {
        Controller.growMycelium();
    }

    public static void growFungusConfirmPressed() {
        Controller.growFungus();
    }

    public static void spreadSporesConfirmPressed() {
        Controller.spreadSpores();
    }

    public static void moveInsectConfirmPressed() {
        Controller.moveInsect();
    }

    public static void cutMyceliumConfirmPressed() {
        Controller.cutMycelium();
    }

    public static void eatSporeConfirmPressed() {
        Controller.eatSpore();
    }
}
