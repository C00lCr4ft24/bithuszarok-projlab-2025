package fungorium;
import fungorium.mycelium.MyceliumConnection;
import fungorium.spore.Spore;
import fungorium.tecton.Tecton;

/**
 * Az Insect osztály a rovarokat reprezentálja, amelyek a benőtt Tecton-okon tudnak közlekedni,
 * tápanyagokat gyűjtenek és mycelium fonalakat vágnak el.
 */
public class Insect implements FungoriumEntity {

    /**
     * A rovar lehetséges mozgási sebességei. SLOW, MEDIUM, FAST.
     */
    private enum Speed { SLOW, MEDIUM, FAST }

    /**
     * Az eddig összesen begyűjtött tápanyag mennyisége.
     */
    private int eatenNutrient;

    /**
     * A rovar jelenlegi mozgási sebessége.
     */
    private Speed speed;
    private int speedEffectTimer;

    /**
     * Jelzi, hogy a rovar képes-e mycelium fonalat vágni két Tecton között.
     */
    private boolean canCutMycelium;
    private int blockMyceliumCutTimer;

    /**
     * Jelzi, hogy a rovar le van-e bénítva.
     */
    private boolean isStunned;
    private int isStunnedTimer;

    /**
     * A rovar aktuális pozícióját jelző Tecton.
     */
    private Tecton position;

    /**
     * Alapértelmezett hatások értékeinek visszaállítása.
     */
    private void resetEffectValues() {
        speed = Speed.MEDIUM;
        canCutMycelium = true;
        isStunned = false;
    }

    /**
     * Létrehoz egy új Insect példányt alapértelmezett értékekkel.
     *
     * @param position Az Insect kezdő pozíciója.
     */
    public Insect(Tecton position) {
        System.out.println("New Insect created: " + this);
        this.position = position;
        eatenNutrient = 0;
        resetEffectValues();
    }

    /**
     * Visszaadja a {@link Tecton} objektumot, amelyen az Insect a metódus hívásakor állt.
     * @return az a Tecton amelyen az Insect a metódus hívásakor állt.
     */
    public Tecton getPosition() { return position; }

    /**
     * Visszaadja az Insect mozgásképességi állapotát
     * @return true ha bénult, false egyébként.
     */
    public boolean isStunned() { return isStunned; }

    /**
     * Elvágja a megadott Mycelium kapcsolatot.
     *
     * @param mc A {@link MyceliumConnection}, amelyet el kell vágni.
     */
    public void cutMyceliumConnection(MyceliumConnection mc) {
        printAction("cutMyceliumConnection");
        mc.cutMe();
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
        spore.doEffect(this); //doEffect rahivasa az Insectre
    }

    /**
     * A rovar áthelyezése egy másik Tecton-ra.
     *
     * @param target A cél {@link Tecton}, amelyre a rovar mozog.
     */
    public void move(Tecton target) {
        if(!(isStunned)) {                   // ha nincs stunnolva
            printAction("move");
            position.removeInsect(this);     // regi tectonrol szedjuk le az insectet
            target.putInsect(this);          // uj tctonra tegyuk ra
            position = target;               // allitsuk be a lokalis valtozot az uj tectonra
        }
        else { printAction("cant move"); }
    }

    /**
     * A rovar lebénításának beállítása.
     */
    public void setStunned(int effectTime) {
        printAction("setStunned");

        isStunnedTimer = effectTime;
        isStunned = true;
    }

    /**
     * Megakadályozza, hogy a rovar mycelium fonalakat vágjon el.
     */
    public void blockMyceliumCut(int effectTime) {
        printAction("blockMyceliumCut");

        blockMyceliumCutTimer = effectTime;
        canCutMycelium = false;
    }

    /**
     * Növeli a rovar mozgási sebességét.
     */
    public void increaseSpeed(int effectTime) {
        printAction("increaseSpeed");

        speedEffectTimer = effectTime;
        switch (speed) {
            case SLOW   -> speed = Speed.MEDIUM;
            case MEDIUM -> speed = Speed.FAST;
        }
    }

    /**
     * Csökkenti a rovar mozgási sebességét.
     */
    public void decreaseSpeed(int effectTime) {
        printAction("decreaseSpeed");

        speedEffectTimer = effectTime;
        switch (speed) {
            case MEDIUM -> speed = Speed.SLOW;
            case FAST   -> speed = Speed.MEDIUM;
        }
    }

    /**
     * Végrehajtja a játék lépését a rovar esetében.
     */
    @Override
    public void gameStep() {

        --blockMyceliumCutTimer;
        if (blockMyceliumCutTimer == 0) { canCutMycelium = true; } // Default ertek visszaallitasa

        --isStunnedTimer;
        if (isStunnedTimer        == 0) { isStunned = false; }     // --||--

        --speedEffectTimer;
        if (speedEffectTimer      == 0) { speed = Speed.MEDIUM; }  // --||--

    }
}
