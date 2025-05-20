package fungorium.model.player;

/**
 * Különböző játékos létrehozására szolgáló osztály.
 */
public class PlayerFactory {
    /**
     * A gombász játékosokhoz tartozó Név id-k számlálója.
     */
    private static int FungusIdCnt = 1;
    /**
     * A rovarász játékosokhoz tartozó Név id-k számlálója.
     */
    private static int InsectIdCnt = 1;

    /**
     * Létrehoz egy új játékost a megadott típus alapján.
     *
     * @param type a játékos típusa
     * @return a létrehozott játékos
     */
    public static Player createPlayer(PlayerTypes type) {
        String playerName;
        switch (type) {
            case GOMBASZ  -> {
                playerName = "Gombász "  + FungusIdCnt;
                FungusIdCnt++;
            }
            case ROVARASZ -> {
                playerName = "Rovarász " + InsectIdCnt;
                InsectIdCnt++;
            }
            default -> playerName = "Not Valid Player";
        }
        return new Player(type, playerName);
    }

    /**
     * Privát konstruktor, hogy megakadályozza a példányosítást.
     */
    private PlayerFactory() {
        throw new IllegalStateException("Static class, cannot be instantiated");
    }

    /**
     * Alapértékre állítja a Név id számlálókat.
     */
    public static void resetCounters() {
        FungusIdCnt = 1;
        InsectIdCnt = 1;
    }
}