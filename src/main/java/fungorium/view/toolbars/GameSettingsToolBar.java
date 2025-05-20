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

public class GameSettingsToolBar extends JToolBar {

    public static final JTextField fungusField = new JTextField();
    public static final JTextField insectField = new JTextField();
    public static final JButton newGameButton = ButtonFactory.getNewButton("New Game", Controller::newGameButtonPressed);
    public static final JTextField playerNameTextField = new JTextField();

    public static final selectedTectonText selectedTectonText       = new selectedTectonText();
    public static final selectedTectonComboBox selectedTectonComboBox = new selectedTectonComboBox();

    
    public GameSettingsToolBar() {
        setFloatable(false);
        setLayout(new GridLayout(0, 4));

        JTextArea fungusText = new JTextArea("Gombászok Száma: ");
        fungusText.setFocusable(false);
        fungusText.setEditable(false);
        add(fungusText);

        fungusField.setText("6");
        add(fungusField);

        add(newGameButton);

        playerNameTextField.setEditable(false);
        add(playerNameTextField);

        JTextArea insectText = new JTextArea("Rovarászok Száma: ");
        insectText.setEditable(false);
        insectText.setFocusable(false);
        add(insectText);

        insectField.setText("6");
        add(insectField);

        add(selectedTectonText);
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



    public void addNewGameButtonActionListener(ActionListener l) { newGameButton.addActionListener(l); }


    public static class selectedTectonText extends JTextPane {
        public selectedTectonText() {
            setEditable(false);
            setFocusable(false);
            setText("Selected Tecton:");
        }
    }





    public selectedTectonComboBox getSelectedTectonComboBox() { return selectedTectonComboBox; }

    public void setSelecedTectonComboBoxListener(ActionListener listener) { selectedTectonComboBox.addActionListener(listener); }

    public static class selectedTectonComboBox extends JComboBox<Tecton> {
        public selectedTectonComboBox() {
            setEditable(false);
        }

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