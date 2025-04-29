package fungorium.spore;

import fungorium.FungoriumEntity;
import fungorium.Insect;

/**
 * Egy absztrakt spórát reprezentáló osztály, amely létrejöhet és kilövődhet, majd egy
 * Tecton-on elhelyezkedve elfogyasztható vagy felhasználható.
 */
public abstract class Spore implements FungoriumEntity {

    /**
     * A spórához tartozó tápanyagtartalom.
     */
    protected int nutrient;
    /**
     * A spóra hatásának időtartama.
     */
    protected int effectTime;
    /**
     * A spóra id-jét tartalmazó String.
     */
    protected String id;


    public String getId() { return id; }

    public Spore(String id, int nutrient, int effectTime) {
        this.id = id;
        this.nutrient = nutrient;
        this.effectTime = effectTime;
    }
    /**
     * Létrehoz egy új spóra példányt a megadott tápanyag- és hatásidő értékekkel.
     *
     * @param nutrient   A spóra tápanyagtartalma.
     * @param effectTime A spóra hatásának időtartama.
     */
    public Spore(int nutrient, int effectTime) {
        this.nutrient = nutrient;
        this.effectTime = effectTime;
    }

    /**
     * Visszaadja a spóra tápanyagtartalmát.
     *
     * @return A spóra tápanyagtartalma.
     */
    public int getNutrientValue() {
        return this.nutrient;
    }
    /**
     * Absztrakt metódus, amely kifejti a spóra egyedi hatását a megadott rovarra.
     *
     * @param insect A {@link Insect}, amelyre a spóra hatással van.
     */
    public abstract boolean doEffect(Insect insect);

    /**
     * Végrehajtja a spóra következő játék lépését.
     * A Spore-nak a lépés során nem történik semmi.
     */
    @Override
    public void gameStep() {
    }
}
