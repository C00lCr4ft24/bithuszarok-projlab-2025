package fungorium.mycelium;

import fungorium.FungoriumEntity;
import fungorium.spore.*;
import fungorium.tecton.Tecton;

/**
 * Egy gomba entitást reprezentál, amely képes spórákat szórni és fejlődni az idő múlásával.
 */
public class Fungus implements FungoriumEntity {

    /**
     * A spóra lövéshez szükséges minimum spóraszint.
     */
    private static final int MINIMUM_SPORE_LEVEL_TO_SPREAD_SPORE = 5;
    /**
     * A gombához tartozó MyceliumJunction pozíció.
     */
    private MyceliumJunction junctionPosition;
    /**
     * A gomba fejlődési szintjét reprezentálja.
     */
    private FungusLevel fungusLevel;
    /**
     * A gomba spóraszintjét reprezentálja. Ez szükséges a spórák szétszórásához.
     */
    private int sporeLevel;
    /**
     * Tárolja, hogy tud-e már spórát lőni a gombatest.
     */
    private boolean canSpreadSpore;

    /**
     * Egy új `Fungus` példányt hoz létre.
     */
    public Fungus() {
        System.out.println("New Fungus created: " + this);
        sporeLevel = 0;
        fungusLevel = FungusLevel.SMALL;
        canSpreadSpore = false;
    }

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
     * Spórát szór szét a megadott cél Tectonra, ha a hatótávolságán belül van. Öregíti a következő fázisra a szintjét.
     *
     * @param target    A {@link Tecton}, amelyre a spórák kerülnek szétszórásra.
     * @param sporeType Megmondja, hogy milyen típusú spórát lőjön ki a Fungus. SporeTypes enum-al mondja meg.
     * @return True, ha elhalt a gombatest, egyébként False.
     */
    public boolean spreadSpores(Tecton target, SporeTypes sporeType) {
        printAction("spreadSpores");
        boolean fungusHasDied = false;
        if (canSpreadSpore) {
            boolean targetInDistance = false;
            switch (fungusLevel) {
                case SMALL, MEDIUM ->
                        targetInDistance = junctionPosition.getPosition().isThisYourNeighbourInRange(target, 1);
                case BIG, LARGE ->
                        targetInDistance = junctionPosition.getPosition().isThisYourNeighbourInRange(target, 2);
            }
            if (targetInDistance) {
                switch (fungusLevel) {
                    case SMALL -> fungusLevel = FungusLevel.MEDIUM;
                    case MEDIUM -> fungusLevel = FungusLevel.BIG;
                    case BIG -> fungusLevel = FungusLevel.LARGE;
                    case LARGE -> fungusHasDied = true;
                }
                Spore newSpore = null;
                switch (sporeType) {
                    case ANTI_CUT_SPORE -> newSpore = new AntiCutSpore(200, 3);
                    case REPLICATION_SPORE -> newSpore = new ReplicationSpore(200, 3);
                    case SLOW_DOWN_SPORE -> newSpore = new SlowDownSpore(200, 3);
                    case SPEED_UP_SPORE -> newSpore = new SpeedUpSpore(200, 3);
                    case STUN_SPORE -> newSpore = new StunSpore(200, 3);
                    case RANDOM -> newSpore = SporeFactory.createSpore();
                }
                junctionPosition.getPosition().putASpore(newSpore);
                sporeLevel = 0;
                canSpreadSpore = false;
            }
            if (fungusHasDied) {
                junctionPosition.removeFungus();
                junctionPosition = null;
            }
        }
        return fungusHasDied;
    }

    /**
     * Végrehajtja a következő játék lépést. Növeli a gomba spóraszintjét.
     */
    @Override
    public void gameStep() {
        printAction("gameStep");
        if (!canSpreadSpore) {
            sporeLevel++;
            if (sporeLevel >= MINIMUM_SPORE_LEVEL_TO_SPREAD_SPORE) {
                canSpreadSpore = true;
            }
        }
    }

    /**
     * A gombatest különböző növekvési szintjei/állapotai.
     * Az első kettőben egy Tecton távolságra tud lőni, míg a másik kettőben már két Tecton távolságra is tud.
     */
    private enum FungusLevel {SMALL, MEDIUM, BIG, LARGE}
}
