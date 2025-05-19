package fungorium.view.buttons;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import javax.swing.JButton;

public class MoveInsectButton extends JButton implements Observer {
    public MoveInsectButton() {

        this.setEnabled(false);
        this.setText("Move insect");
    }

    @Override
    public void update(GameModel gameModel) {
        setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.ROVARASZ));
    }
}
