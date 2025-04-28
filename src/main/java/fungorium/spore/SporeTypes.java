package fungorium.spore;

/**
 * Különböző létrehozható spórák típusai
 */
public enum SporeTypes {
    ANTI_CUT_SPORE,
    REPLICATION_SPORE,
    SLOW_DOWN_SPORE,
    SPEED_UP_SPORE,
    STUN_SPORE,

    // Mindig RANDOM legyen a legutolso SporeFactory miatt
    RANDOM_SPORE
}
