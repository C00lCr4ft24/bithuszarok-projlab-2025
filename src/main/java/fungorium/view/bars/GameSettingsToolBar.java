package fungorium.view.bars;

import fungorium.GameModel;
import fungorium.model.events.EventType;
import fungorium.model.events.GameEvent;
import fungorium.model.events.GameEventController;
import fungorium.model.events.GameEventListener;
import fungorium.view.buttons.NewGameButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameSettingsToolBar extends JToolBar {

    private final JTextField fungusField = new JTextField();
    private final JTextField insectField = new JTextField();
    private final NewGameButton newGameButton = new NewGameButton();
    private final PlayerName currentPlayerName = new PlayerName();

    public GameSettingsToolBar() {
        setFloatable(false);
        setLayout(new GridLayout(0, 2));
        JTextArea fungusText = new JTextArea("Gombászok Száma: ");
        fungusText.setEditable(false);
        add(fungusText);
        add(fungusField);
        JTextArea insectText = new JTextArea("Rovarászok Száma: ");
        insectText.setEditable(false);
        add(insectText);
        add(insectField);
        add(newGameButton);
        currentPlayerName.setEditable(false);
        add(currentPlayerName);

        DocumentListener listener = new DocumentListener() {
            private void updateButtonState() { newGameButton.setEnabled(isValidNumber(fungusField.getText()) && isValidNumber(insectField.getText())); }
            @Override public void insertUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateButtonState(); }
        };

        fungusField.getDocument().addDocumentListener(listener);
        insectField.getDocument().addDocumentListener(listener);
        newGameButton.addActionListener((ActionEvent e) -> {
            ArrayList<Integer> data = new ArrayList<>();
            data.add(Integer.valueOf(fungusField.getText()));
            data.add(Integer.valueOf(insectField.getText()));
            GameEvent event = new GameEvent(this, EventType.NEW_GAME_STARTED, data);
            GameEventController.dispatchEvent(event);
        });
    }

    private boolean isValidNumber(String s) {
        try {
            return !s.isEmpty() && Integer.parseInt(s) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private class PlayerName extends JTextField implements GameEventListener {
        public PlayerName() {
            super();
            setEditable(false);
            GameEventController.addEventListener(this);
        }

        @Override
        public void onEvent(GameEvent event) {
            if(event.getEventType() == EventType.INSECT_PLAYERS_TURN) { setText((String) event.getEventData()); }
            if(event.getEventType() == EventType.FUNGUS_PLAYERS_TURN) { setText((String) event.getEventData()); }
        }
    }
}