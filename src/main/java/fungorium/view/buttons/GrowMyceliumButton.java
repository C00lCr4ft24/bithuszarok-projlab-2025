package fungorium.view.buttons;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import javax.swing.JButton;

public class GrowMyceliumButton extends JButton implements Observer {
    public GrowMyceliumButton() {

        this.setEnabled(false);
        this.setText("Grow mycelium");
    }

    @Override
    public void update(GameModel gameModel) {
        setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.GOMBASZ));
    }
}
