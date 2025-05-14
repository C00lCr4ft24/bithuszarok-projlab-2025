package fungorium.view.buttons;

import fungorium.model.events.EventType;
import fungorium.model.events.GameEvent;
import fungorium.model.events.GameEventController;
import fungorium.model.events.GameEventListener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class CutMyceliumButton extends JButton implements GameEventListener {
    public CutMyceliumButton() {

        GameEventController.addEventListener(this);

        this.setEnabled(false);
        this.setText("Cut mycelium");
        this.addActionListener((ActionEvent e) -> GameEventController.dispatchEvent(new GameEvent(this, EventType.NEXT_PLAYER_IN_ROUND, null)));
    }

    @Override
    public void onEvent(GameEvent event) {
        if(event.getEventType().equals(EventType.INSECT_PLAYERS_TURN)) {
            setEnabled(true);
        }
        if(event.getEventType().equals(EventType.FUNGUS_PLAYERS_TURN)) {
            setEnabled(false);
        }
    }
}
