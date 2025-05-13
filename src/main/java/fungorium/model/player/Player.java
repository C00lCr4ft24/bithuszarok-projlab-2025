package fungorium.model.player;

public class Player {
    private final PlayerTypes type;
    private final String name;
    public Player(PlayerTypes type, String name) {
        this.type = type;
        this.name = name;
    }

    public PlayerTypes getType() { return type; }

    @Override
    public String toString() { return name; }
}
