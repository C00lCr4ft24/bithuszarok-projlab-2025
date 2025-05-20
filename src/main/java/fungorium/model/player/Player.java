package fungorium.model.player;

public class Player {
    private final PlayerTypes type;
    private final String name;
    private int nextEntityId = 0;
    public Player(PlayerTypes type, String name) {
        this.type = type;
        this.name = name;
    }

    public PlayerTypes getType() { return type; }

    public int getNextEntityId() {
        nextEntityId++;
        return nextEntityId;
    }
    @Override
    public String toString() { return name; }
}
