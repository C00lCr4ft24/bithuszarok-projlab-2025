package fungorium.view.toolbars;

import fungorium.GameModel;
import fungorium.controller.ButtonFunctions;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameSettingsToolBar extends JToolBar {

    private static final JTextField fungusField = new JTextField();
    private static final JTextField insectField = new JTextField();
    private static final JButton newGameButton = ButtonFactory.getNewButton("New Game", ButtonFunctions::newGameButtonPressed);

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
        newGameButton.setEnabled(false);
        add(newGameButton);

        DocumentListener listener = new DocumentListener() {
            private void updateButtonState() { newGameButton.setEnabled(isValidNumber(fungusField.getText()) && isValidNumber(insectField.getText())); }
            @Override public void insertUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateButtonState(); }
        };

        fungusField.getDocument().addDocumentListener(listener);
        insectField.getDocument().addDocumentListener(listener);
        //newGameButton.addActionListener((ActionEvent e) -> { GameModel.resetGameModel(Integer.parseInt(fungusField.getText()), Integer.parseInt(insectField.getText())); });
    }

    private boolean isValidNumber(String s) {
        try {
            Integer.parseInt(s);
            return !s.isEmpty();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static JTextField getFungusField() {
        return fungusField;
    }

    public static JTextField getInsectField() {
        return insectField;
    }
}
