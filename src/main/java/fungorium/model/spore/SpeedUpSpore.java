package fungorium.model.spore;

import fungorium.TestFramework;
import fungorium.model.Insect;

/**
 * Egy olyan spórát reprezentál, amely felgyorsítja azt a rovart, amely felszedi.
 */
public class SpeedUpSpore extends Spore {

    /**
     * Létrehoz egy új `SpeedUpSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, amíg a spóra hatása érvényesül.
     */
    public SpeedUpSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
    }

    /**
     * Létrehoz egy új `SpeedUpSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     * @param id         A spóra ID-je.
     */
    public SpeedUpSpore(String id, int nutrient, int effectTime) {
        super(id, nutrient, effectTime);
        String log = "New SPEED_UP_SPORE " + id + " was created.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }
    /**
     * Kifejti a spóra hatását a megadott rovarra.
     * Csökkenti eggyel az effectTime-át.
     *
     * @param insect Az a {@link Insect}, amelyre a spóra hatása vonatkozik.
     * @return True-t ad vissza, ha az effectTime-ja nulla vagy annál kisebb, egyébként false-t.
     */
    @Override
    public boolean doEffect(Insect insect) {
        insect.increaseSpeed();
        effectTime--;
        return effectTime <= 0;
    }
}
