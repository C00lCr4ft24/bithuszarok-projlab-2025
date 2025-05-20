package fungorium.model.spore;

import java.util.Random;

/**
 * Ez az osztály felelős random spórák generálásáért.
 */
public class SporeFactory {
    /**
     * A létrehozható spórák maximális tápanyagtartalma.
     */
    private static final int MAX_NUTRIENT_AMOUNT = 300;
    /**
     * A létrehozható spórák minimális tápanyagtartalma.
     */
    private static final int MIN_NUTRIENT_AMOUNT = 100;
    /**
     * A létrehozható spórák maximális hatásideje.
     */
    private static final int MAX_EFFECT_TIME_AMOUNT = 5;
    /**
     * Egy random szám generátor, amelyet a spórák létrehozásához használunk.
     */
    private static final Random random = new Random();

    /**
     * Ez a metódus létrehoz egy új spórát a megadott azonosítóval és típussal.
     *
     * @param id   A spóra azonosítója.
     * @param type A spóra típusa.
     * @return A létrehozott spóra példány.
     */
    public static Spore createSpore(String id, SporeTypes type) {
        Spore newSpore = null;

        //Ha nem random Spore kell
        if(type != SporeTypes.RANDOM_SPORE) {
            newSpore = sporeSelectorAndCreator(id, type); //Letrehozzuk a konkret Sporet
        }
        //Ha random Spore kell
        if(type == SporeTypes.RANDOM_SPORE) {
                SporeTypes[] sporeTypeValues = SporeTypes.values();
                int randomType = random.nextInt((sporeTypeValues.length-1)); //Azert vonok le egyet, hogy az utolso RANDOM opcio ne legyen benne
                SporeTypes sporeType = sporeTypeValues[randomType];
                newSpore = sporeSelectorAndCreator(id, sporeType);
        }
        return newSpore;
    }

    /**
     * Ez a metódus létrehozza a spórát a megadott típus alapján.
     *
     * @param id   A spóra azonosítója.
     * @param type A spóra típusa.
     * @return A létrehozott spóra példány.
     */
    private static Spore sporeSelectorAndCreator(String id, SporeTypes type) {
        switch (type) {
            case ANTI_CUT_SPORE -> {
                return new AntiCutSpore(id, random.nextInt(MIN_NUTRIENT_AMOUNT, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case REPLICATION_SPORE -> {
                return  new ReplicationSpore(id, random.nextInt(MIN_NUTRIENT_AMOUNT, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case SLOW_DOWN_SPORE -> {
                return  new SlowDownSpore(id, random.nextInt(MIN_NUTRIENT_AMOUNT, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case SPEED_UP_SPORE -> {
                return  new SpeedUpSpore(id, random.nextInt(MIN_NUTRIENT_AMOUNT, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            case STUN_SPORE -> {
                return  new StunSpore(id, random.nextInt(MIN_NUTRIENT_AMOUNT, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT));
            }
            default -> throw new AssertionError();
        }
    }

    /**
     * Privát konstruktor, hogy megakadályozza a példányosítást.
     */
    private SporeFactory() {
        throw new IllegalStateException("Static class, cannot be instantiated");
    }
}
