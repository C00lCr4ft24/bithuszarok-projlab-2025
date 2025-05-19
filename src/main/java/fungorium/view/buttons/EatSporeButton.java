package fungorium.view.buttons;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import javax.swing.JButton;

public class EatSporeButton extends JButton implements Observer {
    public EatSporeButton() {

        this.setEnabled(false);
        this.setText("Eat spore");

    }

    @Override
    public void update(GameModel gameModel) {
        setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.ROVARASZ));
    }
}
