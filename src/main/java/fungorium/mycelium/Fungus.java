package fungorium.mycelium;

import fungorium.FungoriumEntity;
import fungorium.tecton.Tecton;

/**
 * Egy gomba entitást reprezentál, amely képes spórákat szórni és fejlődni az idő múlásával.
 */
public class Fungus implements FungoriumEntity {

    /**
     * A gombához tartozó MyceliumJunction pozíció.
     */
    private MyceliumJunction junctionPosition;
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
     * Egy új `Fungus` példányt hoz létre egy megadott MyceliumJunction pozícióval.
     *
     * @param junctionPosition A MyceliumJunction pozíció, amelyhez a gomba tartozik.
     */
    public Fungus(MyceliumJunction junctionPosition) {
        this();
        this.junctionPosition = junctionPosition;
    }

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
