package fungorium.spore;

import fungorium.FungoriumEntity;
import fungorium.Insect;

abstract public class Spore implements FungoriumEntity {

    private final int nutrient;
    private final int effectTime;

    public Spore(int nutrient, int effectTime) {
        this.nutrient = nutrient;
        this.effectTime = effectTime;
    }

    public int getNutrientValue()
    {
        printAction("getNutrientValue");
        return this.nutrient;
    }
    public abstract void doEffect(Insect insect);
}
