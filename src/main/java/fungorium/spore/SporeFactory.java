package fungorium.spore;

import java.util.Random;

/**
 * Ez az osztály felelős random spórák generálásáért.
 */
public class SporeFactory {
    private static final int MAX_NUTRIENT_AMOUNT = 300;
    private static final int MAX_EFFECT_TIME_AMOUNT = 5;
    private static final Random random = new Random();

    public static Spore createSpore() {
        SporeTypes[] sporeTypeValues = SporeTypes.values();
        int type = random.nextInt((sporeTypeValues.length-1)); //Azert vonok le egyet, hogy az utolso RANDOM opcio ne legyen benne
        SporeTypes sporeType = sporeTypeValues[type];
        Spore newSpore = null;
        switch (sporeType) {
            case ANTI_CUT_SPORE -> {
                newSpore = new AntiCutSpore(random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case REPLICATION_SPORE -> {
                newSpore = new ReplicationSpore(random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case SLOW_DOWN_SPORE -> {
                newSpore = new SlowDownSpore(random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case SPEED_UP_SPORE -> {
                newSpore = new SpeedUpSpore(random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case STUN_SPORE -> {
                newSpore = new StunSpore(random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            default -> throw new AssertionError();
        }
        return newSpore;
    }
}
