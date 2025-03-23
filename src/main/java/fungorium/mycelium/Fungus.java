package fungorium.mycelium;

import fungorium.FungoriumEntity;
import fungorium.tecton.Tecton;

/**
 * Egy gomba entitást reprezentál, amely képes spórákat szórni és fejlődni az idő múlásával.
 */
public class Fungus implements FungoriumEntity {

    /**
     * A gomba fejlődési szintjét reprezentálja.
     */
    private int fungusLevel;

    /**
     * A gomba spóraszintjét reprezentálja. Ez szükséges a spórák szétszórásához.
     */
    private int sporeLevel;

    /**
     * Egy új `Fungus` példányt hoz létre.
     */
    public Fungus() { System.out.println("New Fungus created: " + this); }

    /**
     * Spórákat szór szét a megadott cél Tectonra.
     *
     * @param target A {@link Tecton}, amelyre a spórák kerülnek szétszórásra.
     */
    public void spreadSpores(Tecton target) { printAction("spreadSpores"); }

    /**
     * Végrehajtja a következő játék lépést. Növeli a gomba spóraszintjét.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
