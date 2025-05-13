package fungorium.model.player;

public class PlayerFactory {
    private static int FungusIdCnt = 1;
    private static int InsectIdCnt = 1;

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

    public static void resetCounters() {
        FungusIdCnt = 1;
        InsectIdCnt = 1;
    }
}