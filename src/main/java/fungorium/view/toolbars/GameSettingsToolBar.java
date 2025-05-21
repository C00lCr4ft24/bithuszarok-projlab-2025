package fungorium.view.toolbars;

import fungorium.GameModel;
import fungorium.controller.Controller;
import fungorium.model.tecton.Tecton;
import fungorium.view.buttons.ButtonFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A GameSettingsToolBar osztály a játék beállításait tartalmazó eszköztárat reprezentálja.
 */
public class GameSettingsToolBar extends JToolBar {

    public static final JTextField fungusField = new JTextField();
    public static final JTextField insectField = new JTextField();
    public static final JButton newGameButton = ButtonFactory.getNewButton("Új játék", Controller::newGameButtonPressed);
    public static final JTextField playerNameTextField = new JTextField();
    public static final JButton skipStepButton = ButtonFactory.getNewButton("Lépés kihagyása", Controller::initForNextPlayer);
    public static final JComboBox<Tecton> selectedTectonComboBox = new JComboBox<>();

    /**
     * A GameSettingsToolBar osztály konstruktora, amely beállítja a játék beállításait tartalmazó eszköztárat.
     */
    public GameSettingsToolBar() {
        setFloatable(false);
        setLayout(new GridLayout(0, 5));

        JLabel fungusText = new JLabel("Gombászok Száma: ");
        fungusText.setFocusable(false);
        add(fungusText);

        fungusField.setText("2");
        add(fungusField);

        add(newGameButton);

        playerNameTextField.setEditable(false);
        add(playerNameTextField);

        add(skipStepButton);
        skipStepButton.setEnabled(false);

        JLabel insectText = new JLabel("Rovarászok Száma: ");
        insectText.setFocusable(false);
        add(insectText);

        insectField.setText("2");
        add(insectField);

        add(new JLabel("Kiválasztott tekton: "));
        add(selectedTectonComboBox);

        DocumentListener listener = new DocumentListener() {
            private void updateButtonState() { newGameButton.setEnabled(isValidNumber(fungusField.getText()) && isValidNumber(insectField.getText())); }
            @Override public void insertUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateButtonState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateButtonState(); }
        };

        fungusField.getDocument().addDocumentListener(listener);
        insectField.getDocument().addDocumentListener(listener);
    }

    public static int getFungusPlayersCount() { return Integer.parseInt(fungusField.getText()); }

    public static int getInsectPlayersCount() { return Integer.parseInt(insectField.getText()); }

    private boolean isValidNumber(String s) {
        try {
            return !s.isEmpty() && Integer.parseInt(s) > 0 && Integer.parseInt(s) < 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}