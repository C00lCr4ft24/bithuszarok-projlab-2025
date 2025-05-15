package fungorium.view;

import fungorium.GameModel;
import fungorium.model.tecton.Tecton;
import fungorium.observer.Observer;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private final gameViewText gameViewText = new gameViewText();

    public GameView() {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(0, 2));
        add(gameViewText);
    }

    public gameViewText getGameViewText() { return gameViewText; }

    private static class gameViewText extends JTextPane implements Observer {
        public gameViewText() {
            setEditable(false);
            setFocusable(false);
        }

        @Override
        public void update(GameModel gameModel) {
            if(gameModel == null || gameModel.getSelectedTecton() == null) return;
            setText("Current Tecton: " + gameModel.getSelectedTecton() + "\n" +
                    "Neighbor Tectons: " + gameModel.getSelectedTecton().getNeighborTectons() + "\n" +
                    "Insects: " + gameModel.getSelectedTecton().getInsects() + "\n" +
                    "Spores: " + gameModel.getSelectedTecton().getAllSpores() + "\n" +
                    "Fungus: " + gameModel.getSelectedTecton().getMyceliumJunctionOfFungus().getFungus() + "\n" +
                    "Connections: "
                    );
        }
    }
}
