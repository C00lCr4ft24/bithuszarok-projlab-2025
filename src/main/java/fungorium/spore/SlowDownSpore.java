package fungorium.spore;

import fungorium.Insect;

public class SlowDownSpore extends Spore {

    public SlowDownSpore(int nutrient, int effectTime) { super(nutrient, effectTime); }

    @Override
    public void doEffect(Insect insect) { printAction("doEffect"); }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
