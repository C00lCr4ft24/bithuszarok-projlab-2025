package fungorium.view.buttons;

import fungorium.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewGameButton extends JButton {

    public NewGameButton() {
        setText("New Game");
        setEnabled(false);
    }
}