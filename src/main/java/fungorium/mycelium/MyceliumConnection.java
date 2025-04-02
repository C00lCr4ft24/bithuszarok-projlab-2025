package fungorium.mycelium;

import fungorium.FungoriumEntity;
import fungorium.tecton.Tecton;

/**
 * Két MyceliumJunction közötti kapcsolatot reprezentál.
 */
public class MyceliumConnection implements FungoriumEntity {

    /**
     * A kapcsolat fennmaradó élettartama. Alapértelmezett érték: -1, amely végtelen élettartamot jelent.
     */
    private int lifetime = -1;

    /**
     * Az első MyceliumJunction, amelyhez a kapcsolat tartozik.
     */
    private MyceliumJunction junctionA;

    /**
     * A második MyceliumJunction, amelyhez a kapcsolat tartozik.
     */
    private MyceliumJunction junctionB;

    /**
     * Új `MyceliumConnection` példányt hoz létre, amely a megadott két junction-t köti össze.
     *
     * @param a Az egyik {@link MyceliumJunction}, ahonnan a kapcsolat indul.
     * @param b A másik {@link MyceliumJunction}, ahová a kapcsolat érkezik.
     */
    public MyceliumConnection(MyceliumJunction a, MyceliumJunction b) {
        System.out.println("New MyceliumConnection created: " + this);
        junctionA = a;
        junctionB = b;
    }

    /**
     * Megszakítja a kapcsolatot a két MyceliumJunction között.
     *
     * <p>Eltávolítja magát mindkét kapcsolódó junction-ből, majd `null` értéket rendel
     * az attribútumaihoz, ezzel megszüntetve a kapcsolatot.</p>
     */
    public void cutMe() {
        printAction("cutMe");
        junctionA.removeConnection(this);
        junctionB.removeConnection(this);
        junctionA = null;
        junctionB = null;
    }

    /**
     * Beállítja a kapcsolat élettartamát, ha az új érték nem negatív.
     *
     * @param lifetime Az élettartam új értéke.
     */
    public void setLifetime(int lifetime) {
        if(lifetime < 0) {
            printAction("setLifeTime");
            this.lifetime = lifetime;
        }
    }

    /**
     * Megnézi, hogy a nem megadott vége a megadott Tecton van-e.
     *
     * @param end Az a vég, ahonnan ellenőrizzük a másik végének helyét.
     * @param otherEnd A Tecton, melyen a keresett végnek lennie kell.
     * @return Igaz, ha a nem megadott vége a megadott Tectonon van, egyébként hamis.
     */
    public boolean isThisYOurOtherEndTecton(MyceliumJunction end, Tecton otherEnd){
        printAction("isThisYOurOtherEndTecton");
        if(junctionA == end){
            return junctionB.getPosition() == otherEnd;
        } else if(junctionB == end){
            return junctionA.getPosition() == otherEnd;
        } else {
            return false;
        }
    }

    /**
     * Végrehajtja a kapcsolat következő játék lépését.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
