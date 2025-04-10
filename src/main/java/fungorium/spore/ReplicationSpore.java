package fungorium.spore;

import fungorium.Insect;

/**
 * Egy olyan {@link Spore} amely egy új {@link Insect} példányt hoz létre az őt megevő mellé.
 */
public class ReplicationSpore extends Spore {

    public ReplicationSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
    }

    @Override
    public void doEffect(Insect insect) {
        var newInsect = new Insect(insect.getPosition());
        insect.getPosition().putInsect(newInsect);
    }

    @Override
    public void gameStep() {  }

}