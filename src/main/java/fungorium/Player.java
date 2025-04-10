package fungorium;

public class Player {
    private final String Name;
    private static enum Type { FUNGUSPLAYER, INSECTPLAYER }
    private Type type;

    public Player(String name, int type) {
        this.Name = name;
        switch (type) {
            case 0 -> this.type = Type.FUNGUSPLAYER;
            case 1 -> this.type = Type.INSECTPLAYER;
        }
    }
}
