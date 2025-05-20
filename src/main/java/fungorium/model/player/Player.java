package fungorium.model.player;

public class Player {
    /**
     * PlayerTypes enum, mely megmodja, hogy a jatekos milyen tipusu
     */
    private final PlayerTypes type;

    /**
     * A jatekos neve
     */
    private final String name;

    /**
     * A jatekos entitasainak az id-je, ami már használatban van
     */
    private int nextEntityId = 0;

    /**
     * A jatekos pontszama
     */
    private int score = 0;

    /**
     * A jatekos tipusa
     * @param type a jatekos tipusa
     * @param name a jatekos neve
     */
    public Player(PlayerTypes type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Hozzáadja a játékos pontszámához a megadott értéket.
     * @param score a hozzáadandó pontszám
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Visszaadja a játékos pontszámát.
     * @return a játékos pontszáma
     */
    public int getScore() { return score; }

    /**
     * Visszaadja a játékos típusát.
     * @return a játékos típusa
     */
    public PlayerTypes getType() { return type; }

    /**
     * Visszaadja a játékos Entity-ásának következő index számát.
     * @return következő Entity id
     */
    public int getNextEntityId() {
        nextEntityId++;
        return nextEntityId;
    }

    /**
     * Visszaadja a játékos nevét.
     * @return a játékos neve
     */
    @Override
    public String toString() { return name; }
}
