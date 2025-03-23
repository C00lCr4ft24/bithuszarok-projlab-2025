package fungorium.mycelium;

import fungorium.FungoriumEntity;

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
     * Új `MyceliumConnection` példányt hoz létre. Alapértelmezett konstruktor.
     */
    public MyceliumConnection() { System.out.println("New MyceliumConnection created: " + this); }

    /**
     * Új `MyceliumConnection` példányt hoz létre, amely a megadott két junction-t köti össze.
     *
     * @param from Az egyik {@link MyceliumJunction}, ahonnan a kapcsolat indul.
     * @param to A másik {@link MyceliumJunction}, ahová a kapcsolat érkezik.
     */
    public MyceliumConnection(MyceliumJunction from, MyceliumJunction to) {
        System.out.println("New MyceliumConnection created: " + this);
        junctionA = from;
        junctionB = to;
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
     * Végrehajtja a kapcsolat következő játék lépését.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
