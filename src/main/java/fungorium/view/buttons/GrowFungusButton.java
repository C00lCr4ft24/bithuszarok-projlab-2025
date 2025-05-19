package fungorium.view.buttons;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import javax.swing.JButton;

public class GrowFungusButton extends JButton implements Observer {
    public GrowFungusButton() {

        this.setEnabled(false);
        this.setText("Grow fungus");
    }

    @Override
    public void update(GameModel gameModel) {
        setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.GOMBASZ));
    }
}
