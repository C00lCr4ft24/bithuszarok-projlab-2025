package fungorium.controller;

import javax.swing.ComboBoxModel;

import fungorium.GameModel;
import fungorium.model.Insect;
import fungorium.model.mycelium.Fungus;
import fungorium.model.player.PlayerTypes;
import fungorium.model.spore.SporeTypes;
import fungorium.model.tecton.Tecton;
import fungorium.view.MapPanel;
import fungorium.view.frames.MainFrame;
import fungorium.view.toolbars.FungusPlayerToolBar;
import fungorium.view.toolbars.GameSettingsToolBar;
import fungorium.view.toolbars.InsectPlayerToolBar;
import fungorium.view.toolbars.MoveInsectToolBar;
import fungorium.view.toolbars.SpreadSporesToolBar;

public class Controller {

    private Controller() {
        throw new IllegalStateException("Static class, cannot be instantiated");
    }

    // JÁTÉK INDÍTÁSA

    public static void newGameButtonPressed() {
        GameModel.resetGameModel(Integer.parseInt(GameSettingsToolBar.fungusField.getText()),
                Integer.parseInt(GameSettingsToolBar.insectField.getText()));
        MapPanel.setGameModel(new GameModel());
        GameSettingsToolBar.playerNameTextField.setText(GameModel.getCurrentPlayer().toString());
        for (Tecton tecton : GameModel.getAllTectonsForCurrentPlayer()) {
            GameSettingsToolBar.selectedTectonComboBox.addItem(tecton);
        }
        MainFrame.mapPanel.repaint();
        enableCurrentPlayerTools();
    }

    // MŰVELETI MENÜK

    /**
     * Grow Mycelium gomb lenyomásához tartozó függvény
     */
    public static void growMyceliumButtonPressed() {

        // Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();

        // Gombafonal növesztéshez tartozó eszköztár megjelenítése
        MainFrame.growMyceliumToolBar.setVisible(true);
    }

    /**
     * Grow Fungus gomb lenyomásához tartozó függvény
     */
    public static void growFungusButtonPressed() {

        // Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();

        // Gombafonal növesztéshez tartozó eszköztár megjelenítése
        MainFrame.growFungusToolBar.setVisible(true);
    }

    public static void spreadSporesButtonPressed() {

        // Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();

        //ComboBox-ok feltöltése az aktuális adatokkal
        for (Fungus fungus : GameModel.fungusArrayList) {
            if (fungus.getPlayer().equals(GameModel.getCurrentPlayer())) {
                SpreadSporesToolBar.availableFungiComboBox.addItem(fungus);
            }
        }
        for (Tecton tecton : GameModel.tectonArrayList) {
            SpreadSporesToolBar.availableTectonsComboBox.addItem(tecton);
        }

        // Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.spreadSporesToolBar.setVisible(true);
    }

    public static void cutMyceliumButtonPressed() {

        // Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();

        // Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.cutMyceliumToolBar.setVisible(true);
    }

    public static void moveInsectButtonPressed() {
        // Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();

        for (Insect insect : GameModel.insectArrayList) {
            if (insect.getPlayer().equals(GameModel.getCurrentPlayer())) {
                MoveInsectToolBar.availableInsectsComboBox.addItem(insect);
            }
        }

        for (Tecton tecton : GameModel.tectonArrayList) {
            MoveInsectToolBar.availableTectonsComboBox.addItem(tecton);
        }

        // Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.moveInsectToolBar.setVisible(true);
    }

    public static void eatSporeButtonPressed() {
        // Többi művelethez tartozó eszköztár elrejtése
        hideActionToolBars();

        // Spóra szóráshoz tartozó eszköztár megjelenítése
        MainFrame.eatSporeToolBar.setVisible(true);
    }

    // MŰVELETEK MEGERŐSÍTÉSE
    public static void growMyceliumConfirmPressed() {
        //TODO
        initForNextPlayer();
    }

    public static void growFungusConfirmPressed() {
        //TODO
        initForNextPlayer();
    }

    public static void spreadSporesConfirmPressed() {
        Fungus selectedFungus = (Fungus)SpreadSporesToolBar.availableFungiComboBox.getSelectedItem();
        Tecton selectedTecton = (Tecton)MoveInsectToolBar.availableTectonsComboBox.getSelectedItem();
        selectedFungus.spreadSpores(selectedTecton, SporeTypes.RANDOM_SPORE, "TESZT!!!");
        initForNextPlayer();
    }

    public static void moveInsectConfirmPressed() {
        Insect selectedInsect = (Insect)MoveInsectToolBar.availableInsectsComboBox.getSelectedItem();
        Tecton selectedTecton = (Tecton)MoveInsectToolBar.availableTectonsComboBox.getSelectedItem();
        selectedInsect.move(selectedTecton);
        initForNextPlayer();
    }

    public static void cutMyceliumConfirmPressed() {
        //TODO
        initForNextPlayer();
    }

    public static void eatSporeConfirmPressed() {
        //TODO
        initForNextPlayer();
    }

    private static void hideActionToolBars() {
        MainFrame.growMyceliumToolBar.setVisible(false);
        MainFrame.growFungusToolBar.setVisible(false);
        MainFrame.spreadSporesToolBar.setVisible(false);
        MainFrame.moveInsectToolBar.setVisible(false);
        MainFrame.cutMyceliumToolBar.setVisible(false);
        MainFrame.eatSporeToolBar.setVisible(false);
    }

    private static void enableCurrentPlayerTools() {
        if (GameModel.getCurrentPlayer().getType() == PlayerTypes.GOMBASZ) {
            InsectPlayerToolBar.moveInsectButton.setEnabled(false);
            InsectPlayerToolBar.cutMyceliumButton.setEnabled(false);
            InsectPlayerToolBar.eatSporeButton.setEnabled(false);

            FungusPlayerToolBar.growMyceliumButton.setEnabled(true);
            FungusPlayerToolBar.growFungusButton.setEnabled(true);
            FungusPlayerToolBar.spreadSporesButton.setEnabled(true);
        } else {
            FungusPlayerToolBar.growMyceliumButton.setEnabled(false);
            FungusPlayerToolBar.growFungusButton.setEnabled(false);
            FungusPlayerToolBar.spreadSporesButton.setEnabled(false);

            InsectPlayerToolBar.moveInsectButton.setEnabled(true);
            InsectPlayerToolBar.cutMyceliumButton.setEnabled(true);
            InsectPlayerToolBar.eatSporeButton.setEnabled(true);
        }
    }

    private static void initForNextPlayer() {
        GameModel.executeAPlayerRound();
        updateSelectedTectonComboBox();
        GameSettingsToolBar.playerNameTextField.setText(GameModel.getCurrentPlayer().toString());
        hideActionToolBars();
        enableCurrentPlayerTools();
    }

    private static void updateSelectedTectonComboBox() {
        Tecton previouslySelected = (Tecton)GameSettingsToolBar.selectedTectonComboBox.getSelectedItem();
        GameSettingsToolBar.selectedTectonComboBox.removeAllItems();
        for (Tecton tecton : GameModel.getAllTectonsForCurrentPlayer()) {
            GameSettingsToolBar.selectedTectonComboBox.addItem(tecton);
        }
        if (previouslySelected != null) {
            GameSettingsToolBar.selectedTectonComboBox.setSelectedItem(previouslySelected);
        } else if (GameModel.getSelectedTecton() != null) {
            GameSettingsToolBar.selectedTectonComboBox.setSelectedItem(GameModel.getSelectedTecton());
        }
    }
}
