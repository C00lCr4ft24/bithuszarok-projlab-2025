package fungorium.spore;

import fungorium.Insect;

/**
 * Egy olyan spórát reprezentál, amelyet kilövés után, ha egy {@link Insect} megeszik,
 * lelassítja azt.
 */
public class SlowDownSpore extends Spore {

    /**
     * Létrehoz egy új `SlowDownSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     */
    public SlowDownSpore(int nutrient, int effectTime) { super(nutrient, effectTime); }

    /**
     * Kifejti a spóra hatását a megadott rovarra.
     *
     * @param insect A {@link Insect}, amelyre a spóra hatása vonatkozik.
     */
    @Override
    public void doEffect(Insect insect) { printAction("doEffect"); }

    /**
     * Végrehajtja a spóra következő játék lépését.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
