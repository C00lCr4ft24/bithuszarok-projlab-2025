package fungorium.spore;

import fungorium.Insect;

/**
 * Egy olyan spórát reprezentál, amely felgyorsítja azt a rovart, amely felszedi.
 */
public class SpeedUpSpore extends Spore {

    /**
     * Létrehoz egy új `SpeedUpSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient A spóra tápanyagértéke.
     * @param effectTime Az időtartam, amíg a spóra hatása érvényesül.
     */
    public SpeedUpSpore(int nutrient, int effectTime) { super(nutrient, effectTime); }

    /**
     * Kifejti a spóra hatását a megadott rovarra.
     *
     * @param insect Az a {@link Insect}, amelyre a spóra hatása vonatkozik.
     */
    @Override
    public void doEffect(Insect insect) { printAction("doEffect"); }


    /**
     * Végrehajtja a spóra következő játék lépését.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
