package fungorium.view;

import javax.swing.*;

import fungorium.GameModel;
import fungorium.model.player.PlayerTypes;
import fungorium.observer.Observer;

import java.awt.event.ActionListener;

public class FungusPlayerToolBar extends JToolBar {
    private final GrowMyceliumButton growMyceliumButton;
    private final GrowFungusButton growFungusButton;
    private final SpreadSporesButton spreadSporesButton;
    
    public FungusPlayerToolBar() {
        this.setFloatable(false);
        this.setOrientation(VERTICAL);

        growMyceliumButton = new GrowMyceliumButton();
        growFungusButton = new GrowFungusButton();
        spreadSporesButton = new SpreadSporesButton();
        
        this.add(growMyceliumButton);
        this.add(growFungusButton);
        this.add(spreadSporesButton);
    }





    public SpreadSporesButton getSpreadSporesButton() { return spreadSporesButton; }

    public static class SpreadSporesButton extends JButton implements Observer {
        public SpreadSporesButton() {

            this.setEnabled(false);
            this.setText("Spread Spores");
        }

        @Override
        public void update(GameModel gameModel) {
            setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.GOMBASZ));
        }
    }





    public GrowMyceliumButton getGrowMyceliumButton() { return growMyceliumButton; }

    public static class GrowMyceliumButton extends JButton implements Observer {
        public GrowMyceliumButton() {

            this.setEnabled(false);
            this.setText("Grow mycelium");
        }

        @Override
        public void update(GameModel gameModel) {
            setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.GOMBASZ));

        }
    }





    public GrowFungusButton getGrowFungusButton() { return growFungusButton; }

    public static class GrowFungusButton extends JButton implements Observer {
        public GrowFungusButton() {

            this.setEnabled(false);
            this.setText("Grow fungus");
        }

        @Override
        public void update(GameModel gameModel) {
            setEnabled(gameModel.getCurrentPlayer().getType().equals(PlayerTypes.GOMBASZ));
        }
    }
}
