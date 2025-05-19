package fungorium.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import fungorium.GameModel;
import fungorium.model.tecton.Tecton;

public class MainFramer extends JFrame {
    public MainFramer() {

        //GAMEMODEL
        GameModel gameModel = new GameModel();

        //VIEW
        
        GameSettingsToolBar gSetToolB = new GameSettingsToolBar();
        gSetToolB.addNewGameButtonActionListener((ActionEvent e) -> { gameModel.resetGameModel(gSetToolB.getFungusPlayersCount(), gSetToolB.getInsectPlayersCount()); });
        gameModel.registerObserver(gSetToolB.getPlayerNameTextField());
        gameModel.registerObserver(gSetToolB.getSelectedTectonComboBox());
        var comboBox = gSetToolB.getSelectedTectonComboBox();
        comboBox.addActionListener((ActionEvent e) -> {
            gameModel.setSelectedTecton((Tecton) comboBox.getSelectedItem());
        });



        InsectPlayerToolBar iPToolB = new InsectPlayerToolBar();
        gameModel.registerObserver(iPToolB.getMoveInsectButton());
        gameModel.registerObserver(iPToolB.getEatSporeButton());
        gameModel.registerObserver(iPToolB.getCutMyceliumButton());



        FungusPlayerToolBar fPToolB = new FungusPlayerToolBar();
        gameModel.registerObserver(fPToolB.getSpreadSporesButton());
        gameModel.registerObserver(fPToolB.getGrowMyceliumButton());
        gameModel.registerObserver(fPToolB.getGrowFungusButton());


        GameView            gameView            = new GameView();
        //gameModel.registerObserver(gameView.getGameViewText());

gameModel.registerObserver(gameView.getMapPanel()); // Add this line
        FungoriumMenuBar    fungoriumMenuBar    = new FungoriumMenuBar();
            


        this.setJMenuBar(fungoriumMenuBar);
        this.setLayout(new BorderLayout());
        
        this.add(iPToolB, BorderLayout.WEST);
        this.add(fPToolB, BorderLayout.EAST);
        this.add(gameView, BorderLayout.CENTER);
        this.add(gSetToolB, BorderLayout.SOUTH);
        this.setSize(720, 720);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
