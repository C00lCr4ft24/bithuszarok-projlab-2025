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
     * A rovar lehetséges mozgási sebességei.
     */
    private enum Speed { SLOW, MEDIUM, FAST }

    /**
     * Az eddig összesen begyűjtött tápanyag mennyisége.
     */
    private int     eatenNutrient;

    /**
     * A rovar jelenlegi mozgási sebessége.
     */
    private Speed   speed;

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
     * Alapértelmezett hatások értékeinek visszaállítása.
     */
    private void resetEffectValues() {  }

    /**
     * Létrehoz egy új Insect példányt alapértelmezett értékekkel.
     */
    public Insect() {
        eatenNutrient = 0;
        speed = Speed.MEDIUM;
        canCutMycelium = true;
        isStunned = false;
    }

    /**
     * Elvágja a megadott Mycelium kapcsolatot.
     *
     * @param mc A {@link MyceliumConnection}, amelyet el kell vágni.
     */
    public void cutMyceliumConnection(MyceliumConnection mc) { printAction("cutMyceliumConnection"); }

    /**
     * Elfogyaszt egy spórát, amely tápanyagot biztosít a rovar számára.
     *
     * @param spore A {@link Spore}, amelyet a rovar elfogyaszt.
     */
    public void eatSpore(Spore spore)                        { printAction("eatSpore");              }

    /**
     * A rovar áthelyezése egy másik Tecton-ra.
     *
     * @param target A cél {@link Tecton}, amelyre a rovar mozog.
     */
    public void move(Tecton target)                          { printAction("move");                  }

    /**
     * A rovar lebénításának beállítása.
     */
    public void setStunned()                                 { printAction("setStunned");            }

    /**
     * Megakadályozza, hogy a rovar mycelium fonalakat vágjon el.
     */
    public void blockMyceliumCut()                           { printAction("blockMyceliumCut");      }

    /**
     * Növeli a rovar mozgási sebességét.
     */
    public void increaseSpeed()                              { printAction("increaseSpeed");         }

    /**
     * Csökkenti a rovar mozgási sebességét.
     */
    public void decreaseSpeed()                              { printAction("decreaseSpeed");         }

    /**
     * Végrehajtja a játék lépését a rovar esetében.
     */
    @Override
    public void gameStep() { System.out.println("gameStep");  }
}
