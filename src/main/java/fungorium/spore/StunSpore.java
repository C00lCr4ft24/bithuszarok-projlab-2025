package fungorium.spore;

import fungorium.Insect;

/**
 * Egy olyan spórát reprezentál, amely megbénítja azt a rovart, amely felszedi.
 */
public class StunSpore extends Spore {

    /**
     * Létrehoz egy új `StunSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient A spóra tápanyagtartalma.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     */
    public StunSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
        System.out.println("New StunSpore created: " + this);
    }

    /**
     * Kifejti a spóra hatását a megadott rovarra, megbénítva azt.
     *
     * @param insect Az a {@link Insect}, amelyre a spóra hatása vonatkozik.
     */
    @Override
    public void doEffect(Insect insect) {
        printAction("doEffect");
        insect.setStunned(effectTime);
    }

    /**
     * Végrehajtja a spóra következő játék lépését.
     */
    @Override
    public void gameStep() {  }
}
