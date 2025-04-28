package fungorium.spore;

import java.util.Random;

/**
 * Ez az osztály felelős random spórák generálásáért.
 */
public class SporeFactory {
    private static final int MAX_NUTRIENT_AMOUNT = 300;
    private static final int MAX_EFFECT_TIME_AMOUNT = 5;
    private static final Random random = new Random();
    private static Spore newSpore;

    public static Spore createSpore(String id, SporeTypes type) {
        newSpore = null;

        //Ha nem random Spore kell
        if(type != SporeTypes.RANDOM_SPORE) {
            sporeSelecterAndCreator(id, type); //Letrehozzuk a konkret Sporet
        }
        //Ha random Spore kell
        if(type == SporeTypes.RANDOM_SPORE) {
                SporeTypes[] sporeTypeValues = SporeTypes.values();
                int randomType = random.nextInt((sporeTypeValues.length-1)); //Azert vonok le egyet, hogy az utolso RANDOM opcio ne legyen benne
                SporeTypes sporeType = sporeTypeValues[randomType];
                sporeSelecterAndCreator(id, sporeType);
        }
        return newSpore;
    }

    private static void sporeSelecterAndCreator(String id, SporeTypes type) {
        switch (type) {
            case ANTI_CUT_SPORE -> {
                newSpore = new AntiCutSpore(id, random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case REPLICATION_SPORE -> {
                newSpore = new ReplicationSpore(id, random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case SLOW_DOWN_SPORE -> {
                newSpore = new SlowDownSpore(id, random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case SPEED_UP_SPORE -> {
                newSpore = new SpeedUpSpore(id, random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case STUN_SPORE -> {
                newSpore = new StunSpore(id, random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            default -> throw new AssertionError();
        }
    }
}
