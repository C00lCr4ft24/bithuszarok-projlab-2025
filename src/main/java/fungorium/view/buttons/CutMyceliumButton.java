package fungorium.view.buttons;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import javax.swing.JButton;

public class CutMyceliumButton extends JButton implements Observer {
    public CutMyceliumButton() {

        this.setEnabled(false);
        this.setText("Cut mycelium");
    }

    @Override
    public void update(GameModel gameModel) {
        setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.ROVARASZ));
    }
}
