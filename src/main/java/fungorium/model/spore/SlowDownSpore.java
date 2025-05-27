package fungorium.model.spore;

import fungorium.TestFramework;
import fungorium.model.Insect;

/**
 * Egy olyan spórát reprezentál, amelyet kilövés után, ha egy {@link Insect} megeszik,
 * lelassítja azt.
 */
public class SlowDownSpore extends Spore {

    /**
     * Létrehoz egy új `SlowDownSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     */
    public SlowDownSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
    }

    /**
     * Létrehoz egy új `SlowDownSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     * @param id         A spóra ID-je.
     */
    public SlowDownSpore(String id, int nutrient, int effectTime) {
        super(id, nutrient, effectTime);
        String log = "New SLOW_DOWN_SPORE " + id + " was created.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Kifejti a spóra hatását a megadott rovarra.
     * Csökkenti eggyel az effectTime-át.
     *
     * @param insect A {@link Insect}, amelyre a spóra hatása vonatkozik.
     * @return True-t ad vissza, ha az effectTime-ja nulla vagy annál kisebb, egyébként false-t.
     */
    @Override
    public boolean doEffect(Insect insect) {
        insect.decreaseSpeed();
        effectTime--;
        return effectTime <= 0;
    }
}
