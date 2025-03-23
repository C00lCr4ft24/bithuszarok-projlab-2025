package fungorium.spore;

import fungorium.Insect;

public class AntiCutSpore extends Spore {

    public AntiCutSpore(int nutrient, int effectTime) { super(nutrient, effectTime); }

    @Override
    public void doEffect(Insect insect) { printAction("doEffect"); }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
