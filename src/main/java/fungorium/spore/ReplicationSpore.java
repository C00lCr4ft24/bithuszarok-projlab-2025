package fungorium.spore;

import fungorium.Insect;

public class ReplicationSpore extends Spore {

    public ReplicationSpore(int nutrient, int effectTime) {
        super(nutrient, effectTime);
    }

    @Override
    public void doEffect(Insect insect) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void gameStep() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}