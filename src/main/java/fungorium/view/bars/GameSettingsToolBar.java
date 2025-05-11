package fungorium.view.bars;

import fungorium.GameModel;
import fungorium.view.buttons.NewGameButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsToolBar extends JToolBar {

    private final JTextField fungusField = new JTextField();
    private final JTextField insectField = new JTextField();
    private final NewGameButton newGameButton = new NewGameButton();

    public GameSettingsToolBar() {
        setFloatable(false);
        setLayout(new GridLayout(0, 2));
        JTextArea fungusText = new JTextArea("Gombászok Száma: ");
        add(fungusText);
        add(fungusField);
        JTextArea insectText = new JTextArea("Rovarászok Száma: ");
        add(insectText);
        add(insectField);
        add(newGameButton);

        DocumentListener listener = new DocumentListener() {
            private void updateButtonState() { newGameButton.setEnabled(isValidNumber(fungusField.getText()) && isValidNumber(insectField.getText())); }
            @Override public void insertUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateButtonState(); }
        };

        fungusField.getDocument().addDocumentListener(listener);
        insectField.getDocument().addDocumentListener(listener);
        newGameButton.addActionListener((ActionEvent e) -> { GameModel.resetGameModel(Integer.parseInt(fungusField.getText()), Integer.parseInt(insectField.getText())); });
    }

    private boolean isValidNumber(String s) {
        try {
            Integer.parseInt(s);
            return !s.isEmpty();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
