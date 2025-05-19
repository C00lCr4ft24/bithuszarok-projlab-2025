package fungorium.view.buttons;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import javax.swing.JButton;

public class SpreadSporesButton extends JButton implements Observer {
    public SpreadSporesButton() {
        this.setEnabled(false);
        this.setText("Spread spores");
    }

    @Override
    public void update(GameModel gameModel) {
        setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.GOMBASZ));
    }
}
