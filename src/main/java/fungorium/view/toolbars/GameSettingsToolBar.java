package fungorium.view.toolbars;

import fungorium.controller.ButtonFunctions;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GameSettingsToolBar extends JToolBar {

    public static final JTextField fungusField = new JTextField();
    public static final JTextField insectField = new JTextField();
    public static final JButton newGameButton = ButtonFactory.getNewButton("New Game", ButtonFunctions::newGameButtonPressed);

    public GameSettingsToolBar() {
        setFloatable(false);
        setLayout(new GridLayout(0, 2));
        add(new JLabel("Gombászok Száma: "));
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
    }

    private static boolean isValidNumber(String s) {
        try {
            Integer.parseInt(s);
            return !s.isEmpty();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
