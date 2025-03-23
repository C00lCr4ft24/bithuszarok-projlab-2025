package fungorium.spore;

import fungorium.Insect;

public class StunSpore extends Spore {

    public StunSpore(int nutrient, int effectTime) { super(nutrient, effectTime); }

    @Override
    public void doEffect(Insect insect) { printAction("doEffect"); }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
