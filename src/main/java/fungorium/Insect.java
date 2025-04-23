package fungorium;

import fungorium.mycelium.MyceliumConnection;
import fungorium.spore.Spore;
import fungorium.tecton.Tecton;

import java.util.LinkedList;
import java.util.List;

/**
 * Az Insect osztály a rovarokat reprezentálja, amelyek a benőtt Tecton-okon tudnak közlekedni,
 * tápanyagokat gyűjtenek és mycelium fonalakat vágnak el.
 */
public class Insect implements FungoriumEntity {

    /**
     * A rovar által megevett spórák, melyek hatásukat kifejtik rá.
     */
    private final LinkedList<Spore> eatenAffectingSpores;
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
     * Létrehoz egy új Insect példányt alapértelmezett értékekkel.
     *
     * @param position Az Insect kezdő pozíciója.
     */
    public Insect(Tecton position) {
        System.out.println("New Insect created: " + this);
        this.position = position;
        eatenNutrient = 0;
        eatenAffectingSpores = new LinkedList<>();
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
            printAction("cutMyceliumConnection");
            mc.cutMe();
        } else {
            printAction("can't cutMyceliumConnection");
        }
    }

    /**
     * Elfogyaszt egy spórát, amely tápanyagot biztosít a rovar számára.
     *
     * @param spore A {@link Spore}, amelyet a rovar elfogyaszt.
     */
    public void eatSpore(Spore spore) {
        printAction("eatSpore");
        position.removeSpore(spore); // spora eltavolitasa a tectonrol
        eatenNutrient += spore.getNutrientValue(); // spora tapanyag hozzaadasa
        eatenAffectingSpores.addLast(spore);
    }

    /**
     * A rovar áthelyezése egy másik Tecton-ra.
     *
     * @param target A cél {@link Tecton}, amelyre a rovar mozog.
     */
    public void move(Tecton target) {
        if (!isStunned) {                   // ha nincs stunnolva
            printAction("move");
            position.removeInsect(this);     // regi tectonrol szedjuk le az insectet
            target.putInsect(this);          // uj tctonra tegyuk ra
            position = target;               // allitsuk be a lokalis valtozot az uj tectonra
        } else {
            printAction("can't move");
        }
    }

    /**
     * A rovar lebénításának beállítása.
     */
    public void setStunned() {
        printAction("setStunned");
        isStunned = true;
    }

    /**
     * Megakadályozza, hogy a rovar mycelium fonalakat vágjon el.
     */
    public void blockMyceliumCut() {
        printAction("blockMyceliumCut");
        canCutMycelium = false;
    }

    /**
     * Növeli a rovar mozgási sebességét.
     */
    public void increaseSpeed() {
        printAction("increaseSpeed");
        switch (speed) {
            case SLOW -> speed = Speed.MEDIUM;
            case MEDIUM -> speed = Speed.FAST;
        }
    }

    /**
     * Csökkenti a rovar mozgási sebességét.
     */
    public void decreaseSpeed() {
        printAction("decreaseSpeed");
        switch (speed) {
            case MEDIUM -> speed = Speed.SLOW;
            case FAST -> speed = Speed.MEDIUM;
        }
    }

    /**
     * Végrehajtja a játék lépését a rovar esetében.
     * A resetEffectValues() függvénnyel visszaállítja a hatások attribútumait alapértékeire,
     * majd egy ciklussal végig megy az összes megevett hatást kifejtő spóra tárolóján (eatenAffectingSpores) és kifejteti magán a hatását.
     * Ha a spóra a hatáskifejtés beállítása után, ha a Spóra true-val jelzi, hogy lejárt a hatása. Ilyenkor a rovar kiveszi a rá hatást kifejtő spórák listájából.
     */
    @Override
    public void gameStep() {
        printAction("gameStep");
        resetEffectValues();
        for (Spore spore : eatenAffectingSpores) {
            if (spore.doEffect(this)) {
                eatenAffectingSpores.remove(spore);
            }
        }
    }

    /**
     * A rovar lehetséges mozgási sebességei. SLOW, MEDIUM, FAST.
     */
    private enum Speed {SLOW, MEDIUM, FAST}

}
