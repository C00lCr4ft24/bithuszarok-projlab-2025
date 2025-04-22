package fungorium.spore;

import fungorium.Insect;

/**
 * Egy olyan {@link Spore} amely egy új {@link Insect} példányt hoz létre az őt megevő mellé.
 */
public class ReplicationSpore extends Spore {

    /**
     * Létrehoz egy új `ReplicationSpore` példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagértéke.
     * @param effectTime Az időtartam, ameddig a spóra hatása érvényesül.
     */
    public ReplicationSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
        System.out.println("New ReplicationSpore created: " + this);
    }

    /**
     * Kifejti a spóra hatását a megadott rovarra.
     *
     * @param insect A {@link Insect}, amelyre a spóra hatással van.
     * @return Mindig True-t ad vissza, mert a hatását csak egyszer fejti ki és utána megszűnik.
     */
    @Override
    public boolean doEffect(Insect insect) {
        var newInsect = new Insect(insect.getPosition());
        insect.getPosition().putInsect(newInsect);
        return true;
    }
}