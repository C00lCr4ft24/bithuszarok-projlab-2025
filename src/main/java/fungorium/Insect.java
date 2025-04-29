package fungorium;

import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.Spore;
import fungorium.tecton.Tecton;

import java.lang.runtime.ObjectMethods;
import java.util.*;

/**
 * Az Insect osztály a rovarokat reprezentálja, amelyek a benőtt Tecton-okon tudnak közlekedni,
 * tápanyagokat gyűjtenek és mycelium fonalakat vágnak el.
 */
public class Insect implements FungoriumEntity {

    /**
     * A rovar által megevett spórák, melyek hatásukat kifejtik rá.
     */
    private final LinkedList<Spore> eatenAffectingSpores = new LinkedList<>();
    /**
     * Az eddig összesen begyűjtött tápanyag mennyisége.
     */
    private int eatenNutrient;
    /**
     * A rovar jelenlegi mozgási sebessége.
     */
    private Speed speed;
    /**
     * Jelzi, hogy a rovar képes-e mycelium fonalat vágni két Tecton között.
     */
    private boolean canCutMycelium;
    /**
     * Jelzi, hogy a rovar le van-e bénítva.
     */
    private boolean isStunned;
    /**
     * A rovar aktuális pozícióját jelző Tecton.
     */
    private Tecton position;
    /**
     * Az Insect id-jét tartalmazó String.
     */
    private String id;
    /**
     * Visszaadja az ID-t.
     * @return ID String.
     */
    public String getId() { return id; }

    public Insect(String id, Tecton position) {
        this.id = id;
        this.position = position;
        eatenNutrient = 0;
        resetEffectValues();
        String log = "Insect " + id + " was added to " + position.getId() + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Létrehoz egy új Insect példányt alapértelmezett értékekkel.
     *
     * @param position Az Insect kezdő pozíciója.
     */
    public Insect(Tecton position) {
        this.position = position;
        eatenNutrient = 0;
        resetEffectValues();
    }

    /**
     * Alapértelmezett hatások értékeinek visszaállítása.
     */
    private void resetEffectValues() {
        speed = Speed.MEDIUM;
        canCutMycelium = true;
        isStunned = false;
    }

    /**
     * Visszaadja a {@link Tecton} objektumot, amelyen az Insect a metódus hívásakor állt.
     *
     * @return az a Tecton amelyen az Insect a metódus hívásakor állt.
     */
    public Tecton getPosition() {
        return position;
    }

    /**
     * Visszaadja az Insect mozgásképességi állapotát
     *
     * @return true ha bénult, false egyébként.
     */
    public boolean isStunned() {
        return isStunned;
    }

    /**
     * Elvágja a megadott Mycelium kapcsolatot, ha tud vágni.
     *
     * @param mc A {@link MyceliumConnection}, amelyet el kell vágni.
     */
    public void cutMyceliumConnection(MyceliumConnection mc) {
        if (canCutMycelium) {
            //printAction("cutMyceliumConnection");
            boolean isMCConnectedToCurrentPos = false;
            for(var mj1 : position.getMyceliumJunctions()) {
                for(var mj2 : mc.getBothEnds()) {
                    if (Objects.equals(mj1, mj2)) {
                        isMCConnectedToCurrentPos = true;
                        break;
                    }
                }
            }
            if(isMCConnectedToCurrentPos) {
                mc.cutMe();
                String log = "Insect " + id + " cut " + mc.getId() + " successfully.";
                System.out.println(log);
                TestFramework.logOutput(log);
                return;
            }
        }
        String log = "Insect " + id + " tried cutting " + mc.getId() + " but failed.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Elfogyaszt egy spórát, amely tápanyagot biztosít a rovar számára.
     *
     * @param spore A {@link Spore}, amelyet a rovar elfogyaszt.
     */
    public void eatSpore(Spore spore) {
        position.removeSpore(spore); // spora eltavolitasa a tectonrol
        eatenNutrient += spore.getNutrientValue(); // spora tapanyag hozzaadasa
        eatenAffectingSpores.addLast(spore);
        String log = "Insect " + id + " ate spore " + spore.getId() + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * A rovar áthelyezése egy másik Tecton-ra.
     * Mozgás előtt ellenőrzi, hogy át tud-e mozogni a célra.
     *
     * @param target A cél {@link Tecton}, amelyre a rovar mozog.
     */
    public void move(Tecton target) {
        //printAction("move");
        if (target == null) return;
        if(isStunned) {
            String log = "Insect " + id + " can not move to " + target.getId() + " as it is stunned.";
            System.out.println(log);
            TestFramework.logOutput(log);
            return;
        }

        int currentSpeed = 0;
        switch (speed) {
            case SLOW   -> currentSpeed = 1;
            case MEDIUM -> currentSpeed = 2;
            case FAST   -> currentSpeed = 3;
        }

        Set<MyceliumJunction> targetJunctions = new HashSet<>(target.getMyceliumJunctions());
        Set<MyceliumJunction> startJunctions  = new HashSet<>(position.getMyceliumJunctions());

        Set<MyceliumJunction> visited = new HashSet<>();
        Queue<MyceliumJunction> queue = new ArrayDeque<>();
        Map<MyceliumJunction, Integer> distances = new HashMap<>();

        for (var junction : startJunctions) {
            queue.add(junction);
            visited.add(junction);
            distances.put(junction, 0);
        }

        while (!queue.isEmpty()) {
            MyceliumJunction current = queue.poll();
            int currentDistance = distances.get(current);
            if (targetJunctions.contains(current)) { //Ha megtalaltuk
                if(currentDistance <= currentSpeed) {
                    position.removeInsect(this);     // regi tectonrol szedjuk le az insectet
                    target.putInsect(this);          // uj tectonra tegyuk ra
                    position = target;               // allitsuk be a lokalis valtozot az uj tectonra
                    String log = "Insect " + id + " has moved to " + target.getId() + ".";
                    System.out.println(log);
                    TestFramework.logOutput(log);
                }
                else {
                    String log = "Insect " + id + " can not move to " + target.getId() + " because it is too far.";
                    System.out.println(log);
                    TestFramework.logOutput(log);
                }
                return;
            }

            for (MyceliumConnection conn : current.getConnections()) {
                MyceliumJunction neighbor = conn.getOtherEnd(current);
                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    distances.put(neighbor, currentDistance + 1);
                }
            }
        }
        String log = "Insect " + id + " can not move to " + target.getId() + " because there is no connection.";
        System.out.println(log);
        TestFramework.logOutput(log);    }

    /**
     * A rovar lebénításának beállítása.
     */
    public void setStunned() {
        isStunned = true;
        String log = "Insect " + id + " got stunned and now can not move.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Megakadályozza, hogy a rovar mycelium fonalakat vágjon el.
     */
    public void blockMyceliumCut() {
        canCutMycelium = false;
        String log = "Insect " + id + " from now can not cut Mycelium.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Növeli a rovar mozgási sebességét.
     */
    public void increaseSpeed() {
        switch (speed) {
            case SLOW -> speed = Speed.MEDIUM;
            case MEDIUM -> speed = Speed.FAST;
        }
        String log = "Insect " + id + " speed was increased to " + speed + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Csökkenti a rovar mozgási sebességét.
     */
    public void decreaseSpeed() {
        switch (speed) {
            case MEDIUM -> speed = Speed.SLOW;
            case FAST -> speed = Speed.MEDIUM;
        }
        String log = "Insect " + id + " speed was decreased to " + speed + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Végrehajtja a játék lépését a rovar esetében.
     * A resetEffectValues() függvénnyel visszaállítja a hatások attribútumait alapértékeire,
     * majd egy ciklussal végig megy az összes megevett hatást kifejtő spóra tárolóján (eatenAffectingSpores) és kifejteti magán a hatását.
     * Ha a spóra a hatáskifejtés beállítása után, ha a Spóra true-val jelzi, hogy lejárt a hatása. Ilyenkor a rovar kiveszi a rá hatást kifejtő spórák listájából.
     */
    @Override
    public void gameStep() {
        //printAction("gameStep");
        resetEffectValues();
        eatenAffectingSpores.removeIf(spore -> spore.doEffect(this));
        for(Spore spore : eatenAffectingSpores) {
            spore.doEffect(this);
        }
    }

    /**
     * A rovar lehetséges mozgási sebességei. SLOW, MEDIUM, FAST.
     */
    private enum Speed {SLOW, MEDIUM, FAST}

}
