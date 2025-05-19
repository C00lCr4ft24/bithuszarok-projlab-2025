package fungorium.controller;

import fungorium.view.frames.MainFrame;

public class ButtonFunctions {

    private ButtonFunctions() { throw new IllegalStateException("Static class, cannot be instantiated"); }
    
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

}
