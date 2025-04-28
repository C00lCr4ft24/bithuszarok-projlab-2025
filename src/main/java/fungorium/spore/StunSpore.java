package fungorium.spore;

import fungorium.Insect;
import fungorium.TestFramework;

/**
 * Egy olyan spórát reprezentál, amely megbénítja azt a rovart, amely felszedi.
 */
public class StunSpore extends Spore {

    /**
     * Létrehoz egy új `StunSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagtartalma.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     */
    public StunSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
        System.out.println("New StunSpore created: " + this);
    }
    /**
     * Létrehoz egy új `StunSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     * @param id         A spóra ID-je.
     */
    public StunSpore(String id, int nutrient, int effectTime) {
        super(id, nutrient, effectTime);
        String log = "New StunSpore " + id + " was created with " + nutrient + " nutrient and " + effectTime + " effectTime.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }
    /**
     * Kifejti a spóra hatását a megadott rovarra, megbénítva azt.
     * Csökkenti eggyel az effectTime-át.
     *
     * @param insect Az a {@link Insect}, amelyre a spóra hatása vonatkozik.
     * @return True-t ad vissza, ha az effectTime-ja nulla vagy annál kisebb, egyébként false-t.
     */
    @Override
    public boolean doEffect(Insect insect) {
        printAction("doEffect");
        insect.setStunned();
        effectTime--;
        return effectTime <= 0;
    }
}
