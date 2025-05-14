package fungorium.model.events;

import java.util.*;

public class GameEventController {
    private static final HashSet<GameEventListener> listeners = new HashSet<GameEventListener>();

    public static void addEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public static void dispatchEvent(GameEvent event) {
        for (GameEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}