package fungorium.model.events;

import java.util.EventObject;

public class GameEvent extends EventObject {
    private final EventType eventType;
    private final Object eventData;

    public GameEvent(Object source, EventType type, Object data) {
        super(source);
        eventType = type;
        eventData = data;
    }

    public EventType getEventType() { return eventType; }

    public Object getEventData() { return eventData; }
}