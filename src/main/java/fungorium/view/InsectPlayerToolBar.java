package fungorium.view;

import javax.swing.*;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import java.awt.event.ActionListener;

public class InsectPlayerToolBar extends JToolBar {
    private final CutMyceliumButton cutMyceliumButton = new CutMyceliumButton();
    private final MoveInsectButton   moveInsectButton = new MoveInsectButton();
    private final EatSporeButton       eatSporeButton = new EatSporeButton();

    public InsectPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        this.add(moveInsectButton);
        this.add(cutMyceliumButton);
        this.add(eatSporeButton);
    }



    public MoveInsectButton getMoveInsectButton() { return moveInsectButton; }

    public static class MoveInsectButton extends JButton implements Observer {
        public MoveInsectButton() {

            this.setEnabled(false);
            this.setText("Move insect");
        }

        @Override
        public void update(GameModel gameModel) {
            setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.ROVARASZ));
        }
    }



    public EatSporeButton getEatSporeButton() { return eatSporeButton; }

    public static class EatSporeButton extends JButton implements Observer {
        public EatSporeButton() {

            this.setEnabled(false);
            this.setText("Eat spore");
        }

        @Override
        public void update(GameModel gameModel) {
            setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.ROVARASZ));
        }
    }



    public CutMyceliumButton getCutMyceliumButton() { return cutMyceliumButton; }

    public static class CutMyceliumButton extends JButton implements Observer {
        public CutMyceliumButton() {

            this.setEnabled(false);
            this.setText("Cut mycelium");
        }

        @Override
        public void update(GameModel gameModel) {
            setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.ROVARASZ));
        }
    }
}
