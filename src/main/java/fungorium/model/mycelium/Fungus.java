package fungorium.model.mycelium;

import fungorium.TestFramework;
import fungorium.model.FungoriumEntity;
import fungorium.model.player.Player;
import fungorium.model.spore.*;
import fungorium.model.tecton.Tecton;

import java.util.*;

/**
 * Egy gomba entitást reprezentál, amely képes spórákat szórni és fejlődni az idő múlásával.
 */
public class Fungus implements FungoriumEntity {

    /**
     * A spóra lövéshez szükséges minimum spóraszint.
     */
    private static final int MINIMUM_SPORE_LEVEL_TO_SPREAD_SPORE = 3;
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

    private Player player;

    private String id;

    public String getId() { return id; }

    public Player getPlayer() { return player; }

    public Fungus(String id, MyceliumJunction junctionPosition) {
        this.id = id;
        this.sporeLevel = 0;
        this.fungusLevel = FungusLevel.SMALL;
        this.canSpreadSpore = false;
        this.junctionPosition = junctionPosition;
        String log = "Fungus " + id + " was added to " + junctionPosition.getId() + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    public Fungus(String id, Player player, MyceliumJunction junctionPosition) {
        this.id = id;
        this.player = player;
        this.sporeLevel = 0;
        this.fungusLevel = FungusLevel.SMALL;
        this.canSpreadSpore = false;
        this.junctionPosition = junctionPosition;
        String log = "Fungus " + id + " was added to " + junctionPosition.getPosition().getId() + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }


    /**
     * Egy új `Fungus` példányt hoz létre egy megadott MyceliumJunction pozícióval.
     *
     * @param junctionPosition A MyceliumJunction pozíció, amelyhez a gomba tartozik.
     */
    public Fungus(MyceliumJunction junctionPosition) {
        this.sporeLevel = 0;
        this.fungusLevel = FungusLevel.SMALL;
        this.canSpreadSpore = false;
        this.junctionPosition = junctionPosition;
    }

    /**
     * Spórát szór szét a megadott cél Tectonra, ha a hatótávolságán belül van. Öregíti a következő fázisra a szintjét.
     *
     * @param target    A {@link Tecton}, amelyre a spórák kerülnek szétszórásra.
     * @param sporeType Megmondja, hogy milyen típusú spórát lőjön ki a Fungus. SporeTypes enum-al mondja meg.
     * @return True, ha elhalt a gombatest, egyébként False.
     */
    public boolean spreadSpores(Tecton target, SporeTypes sporeType, String id) {
        boolean fungusHasDied = false;
        if(!canSpreadSpore) {
            String log = "Fungus " + id + " can not spread spores.";
            System.out.println(log);
            TestFramework.logOutput(log);
            return false;
        }
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
                    case ANTI_CUT_SPORE -> newSpore = new AntiCutSpore(id, 200, 3);
                    case REPLICATION_SPORE -> newSpore = new ReplicationSpore(id, 200, 3);
                    case SLOW_DOWN_SPORE -> newSpore = new SlowDownSpore(id, 200, 3);
                    case SPEED_UP_SPORE -> newSpore = new SpeedUpSpore(id, 200, 3);
                    case STUN_SPORE -> newSpore = new StunSpore(id, 200, 3);
                    case RANDOM_SPORE -> newSpore = SporeFactory.createSpore(id, SporeTypes.RANDOM_SPORE);
                }
                String log = "Fungus " + id + " spread " + sporeType.toString() + " " + id + " to " + target.getId() + ".";
                System.out.println(log);
                TestFramework.logOutput(log);
                target.putASpore(newSpore);
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

    public boolean isThisConnectionConnectedTo(MyceliumConnection mc) {
        if (mc == null)
            return false;

        Set<MyceliumJunction> visited = new HashSet<>();
        Queue<MyceliumJunction> queue = new ArrayDeque<>();

        queue  .add(junctionPosition);
        visited.add(junctionPosition);

        while (!queue.isEmpty()) {
            MyceliumJunction current = queue.poll();

            for (MyceliumConnection conn : current.getConnections()) {
                if (conn == mc) return true;

                MyceliumJunction neighbor = conn.getOtherEnd(current);
                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * Végrehajtja a következő játék lépést. Növeli a gomba spóraszintjét.
     */
    @Override
    public void gameStep() {
        if (!canSpreadSpore) {
            sporeLevel++;
            if (sporeLevel >= MINIMUM_SPORE_LEVEL_TO_SPREAD_SPORE) {
                canSpreadSpore = true;
            }
        }
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * A gombatest különböző növekvési szintjei/állapotai.
     * Az első kettőben egy Tecton távolságra tud lőni, míg a másik kettőben már két Tecton távolságra is tud.
     */
    private enum FungusLevel {SMALL, MEDIUM, BIG, LARGE}
}
