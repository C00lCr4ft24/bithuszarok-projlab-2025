package fungorium.observer;

import fungorium.GameModel;

import java.util.Observable;

public interface Observer {
    public void update(GameModel gameModel);
}