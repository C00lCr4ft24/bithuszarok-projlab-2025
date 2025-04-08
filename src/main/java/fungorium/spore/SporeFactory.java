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
        int type = random.nextInt(1, 6);
        Spore newSpore = null;
        switch (type) {
            case 1 -> { newSpore = new AntiCutSpore    (random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT)); }
            case 2 -> { newSpore = new ReplicationSpore(random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT)); }
            case 3 -> { newSpore = new SlowDownSpore   (random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT)); }
            case 4 -> { newSpore = new SpeedUpSpore    (random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT)); }
            case 5 -> { newSpore = new StunSpore       (random.nextInt(1, MAX_NUTRIENT_AMOUNT), random.nextInt(1, MAX_EFFECT_TIME_AMOUNT)); }

            default -> throw new AssertionError();
        }
        return newSpore;
    }
}
