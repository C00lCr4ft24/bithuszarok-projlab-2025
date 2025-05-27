package fungorium.model.spore;

import fungorium.TestFramework;
import fungorium.model.Insect;

/**
 * Az AntiCutSpore osztály egy speciális spórát reprezentál, amely megakadályozza,
 * hogy egy rovar, amely felvette ezt a spórát, meghatározott körig fonalat vágjon.
 */
public class AntiCutSpore extends Spore {

    /**
     * Létrehoz egy új AntiCutSpore példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spórához tartozó tápanyag értéke.
     * @param effectTime Az időtartam, amíg a spóra hatása érvényesül.
     */
    public AntiCutSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
    }

    /**
     * Létrehoz egy új `AntiCutSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     * @param id         A spóra ID-je.
     */
    public AntiCutSpore(String id, int nutrient, int effectTime) {
        super(id, nutrient, effectTime);
        String log = "New ANTI_CUT_SPORE " + id + " was created.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Kifejti a spóra hatását a megadott rovarra.
     * Csökkenti eggyel az effectTime-át.
     *
     * @param insect A {@link Insect} objektum, amelyre a spóra hatása érvényesül.
     * @return True-t ad vissza, ha az effectTime-ja nulla vagy annál kisebb, egyébként false-t.
     */
    @Override
    public boolean doEffect(Insect insect) {
        insect.blockMyceliumCut();
        effectTime--;
        return effectTime <= 0;
    }
}
