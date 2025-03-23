package fungorium.spore;

import fungorium.Insect;

public class SpeedUpSpore extends Spore {

    public SpeedUpSpore(int nutrient, int effectTime) { super(nutrient, effectTime); }

    @Override
    public void doEffect(Insect insect) { printAction("doEffect"); }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
