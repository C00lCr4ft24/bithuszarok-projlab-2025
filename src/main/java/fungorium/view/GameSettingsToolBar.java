package fungorium.view;

import fungorium.GameModel;
import fungorium.model.tecton.Tecton;
import fungorium.observer.Observer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameSettingsToolBar extends JToolBar {

    public static final JTextField fungusField = new JTextField();
    private final JTextField insectField = new JTextField();
    private final NewGameButton newGameButton = new NewGameButton();
    private final PlayerNameTextField playerNameTextField = new PlayerNameTextField();

    private final selectedTectonText selectedTectonText       = new selectedTectonText();
    private final selectedTectonComboBox selecedTectonComboBox = new selectedTectonComboBox();

    
    public GameSettingsToolBar() {
        setFloatable(false);
        setLayout(new GridLayout(0, 4));

        JTextArea fungusText = new JTextArea("Gombászok Száma: ");
        fungusText.setFocusable(false);
        fungusText.setEditable(false);
        add(fungusText);

        add(fungusField);

        add(newGameButton);

        playerNameTextField.setEditable(false);
        add(playerNameTextField);

        JTextArea insectText = new JTextArea("Rovarászok Száma: ");
        insectText.setEditable(false);
        insectText.setFocusable(false);
        add(insectText);

        add(insectField);

        add(selectedTectonText);
        add(selecedTectonComboBox);

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

    public int getInsectPlayersCount() { return Integer.parseInt(insectField.getText()); }

    private boolean isValidNumber(String s) {
        try {
            return !s.isEmpty() && Integer.parseInt(s) > 0 && Integer.parseInt(s) < 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }






    public PlayerNameTextField getPlayerNameTextField() { return playerNameTextField; }

    public static class PlayerNameTextField extends JTextField implements Observer {
        public PlayerNameTextField() {
            super();
            setEditable(false);
            setFocusable(false);
        }

        @Override
        public void update(GameModel gameModel) {
            setText(gameModel.getCurrentPlayer().toString());
        }
    }





    public void addNewGameButtonActionListener(ActionListener l) { newGameButton.addActionListener(l); }

    private static class NewGameButton extends JButton {

        public NewGameButton() {
            setText("New Game");
            setEnabled(false);
        }
    }





    public static class selectedTectonText extends JTextPane {
        public selectedTectonText() {
            setEditable(false);
            setFocusable(false);
            setText("Selected Tecton:");
        }
    }





    public selectedTectonComboBox getSelectedTectonComboBox() { return selecedTectonComboBox; }

    public void setSelecedTectonComboBoxListener(ActionListener listener) { selecedTectonComboBox.addActionListener(listener); }

    public static class selectedTectonComboBox extends JComboBox<Tecton> implements Observer {
        public selectedTectonComboBox() {
            setEditable(false);
        }

        @Override
        public void update(GameModel gameModel) {
            Tecton previouslySelected = (Tecton)getSelectedItem();
            removeAllItems();
            for (Tecton tecton : gameModel.tectonArrayList) {
                addItem(tecton);
            }
            if (previouslySelected != null) {
                setSelectedItem(previouslySelected);
            } else if (gameModel.getSelectedTecton() != null) {
                setSelectedItem(gameModel.getSelectedTecton());
            }
        }
    }
}