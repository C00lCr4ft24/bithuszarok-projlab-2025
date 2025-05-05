package fungorium.model.mycelium;

import fungorium.TestFramework;
import fungorium.model.FungoriumEntity;
import fungorium.model.tecton.Tecton;

import java.util.ArrayList;
import java.util.Set;

/**
 * Két MyceliumJunction közötti kapcsolatot reprezentál.
 */
public class MyceliumConnection implements FungoriumEntity {

    public static final int CUT_DEFAULT_LIFETIME = 3;
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
     * Azt tárolja, hogy el vágta-e már egy rovar őt.
     */
    private boolean hasBeenCut = false;

    /**
     * Tárolja, hogy megszűnt-e már a kapcsolat
     */
    private boolean hasBeenTerminated = false;

    private String id;
    public String getId() { return id; }
    public MyceliumConnection(String id, MyceliumJunction a, MyceliumJunction b) {
        this.id = id;
        junctionA = a;
        junctionA.addConnection(this);
        junctionB = b;
        junctionB.addConnection(this);
        String log = "MyceliumConnection " + id + " was added to " + junctionA.getId() + " and " + junctionB.getId() + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Új `MyceliumConnection` példányt hoz létre, amely a megadott két junction-t köti össze.
     *
     * @param a Az egyik {@link MyceliumJunction}, ahonnan a kapcsolat indul.
     * @param b A másik {@link MyceliumJunction}, ahová a kapcsolat érkezik.
     */
    public MyceliumConnection(MyceliumJunction a, MyceliumJunction b) {
        junctionA = a;
        junctionA.addConnection(this);
        junctionB = b;
        junctionB.addConnection(this);
    }

    /**
     * Véglegesen megszünteti a csatlakozását a két MyceliumJunction-nek.
     * Eltávolítja magát mindkét kapcsolódó junction-ből, majd `null` értéket rendel az attribútumaihoz, ezzel megszüntetve a kapcsolatot.
     */
    private void terminateConnection() {
        if(!hasBeenTerminated) {
            String log = "MyceliumConnection " + id + " was destroyed.";
            System.out.println(log);
            TestFramework.logOutput(log);
            hasBeenTerminated = true;
            junctionA.removeConnection(this);
            junctionB.removeConnection(this);
            junctionA = null;
            junctionB = null;
        }
    }

    /**
     * Ezzel tudja elvágni a fonalat egy rovar.
     * Az elvágás bejegyzése után rögtön beállítja a fonal életidejét a statikus CUT_DEFAULT_LIFETIME értékére.
     */
    public void cutMe() {
        hasBeenCut = true;
        setLifetime(CUT_DEFAULT_LIFETIME);
        String log = "Connection " + id + " was cut.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Beállítja a kapcsolat élettartamát.
     * Ha a kapott érték nulla, akkor rögtön terminálja a fonalat.
     * Ha a kapott érték negatív és még nem lett elvágva a fonál, akkor beállítja a lifetime-ot a kapott értékre.
     * Ha a kapott érték pozitív és még nem lett beállítva, hogy majd haljon el a fonál vagy a kapott érték kisebb, mint a beállított, akkor beállítja a lifetime-ot a kapott értékre.
     *
     * @param newLifetime Az élettartam új értéke.
     */
    public void setLifetime(int newLifetime) {
        if (newLifetime == 0) {
            // Ha az új kapott newLifetime nulla, akkor azonnal megszakítjuk az összekötést.
            terminateConnection();
            String log = "Connection " + id + " has died.";
            System.out.println(log);
            TestFramework.logOutput(log);
        } else if (newLifetime < 0 && !hasBeenCut) {
            // Ha a newLifetime nullánál kisebb és még nem lett elvágva, akkor beállítja a lifetime értékét a kapott newLifetime értékére. (Ilyenkor a fonal örökké él)
            lifetime = newLifetime;
        } else if (newLifetime > 0 && (lifetime < 0 || lifetime > newLifetime)) {
            // Ha newLifetime nullánál nagyobb és már a lifetime-ja még nullánál kisebb (tehát még nem lett beállítva, hogy haljon majd el) vagy a kapott newLifetime érték kisebb, mint a pillanatnyi beállított lifetime értéke.
            lifetime = newLifetime;
        }
    }

    /**
     * Megnézi, hogy a nem megadott vége a megadott Tecton van-e.
     *
     * @param end      Az a vég, ahonnan ellenőrizzük a másik végének helyét.
     * @param otherEnd A Tecton, melyen a keresett végnek lennie kell.
     * @return Igaz, ha a nem megadott vége a megadott Tectonon van, egyébként hamis.
     */
    public boolean isThisYourOtherEndTecton(MyceliumJunction end, Tecton otherEnd) {
        if (junctionA == end) {
            return junctionB.getPosition() == otherEnd;
        } else if (junctionB == end) {
            return junctionA.getPosition() == otherEnd;
        } else {
            return false;
        }
    }

    /**
     * Visszaadja az ehhez tartozó MyceliumJunctionnek a másik végét.
     *
     * @return másik vég vagy null, ha a kapott MyceliumJunction egyik végével sem egyezik meg.
     */
    public MyceliumJunction getOtherEnd(MyceliumJunction from) {
        if (junctionA == from) {
            return junctionB;
        } else if (junctionB == from) {
            return junctionA;
        }
        return null;
    }

    public ArrayList<MyceliumJunction> getBothEnds() {
        var list = new ArrayList<MyceliumJunction>();
        list.add(junctionA);
        list.add(junctionB);
        return list;
    }
    /**
     * Kicseréli a from paraméterként kapott MyceliumJunction-et a to paraméterként kapott MyceliumJunction-re, ha a from paraméter megegyezik valamelyik végével.
     *
     * @param from Cserélendő MyceliumJunction vég
     * @param to   Amire cserélni kell a from paramétert
     */
    public void changeThisJunctionTo(MyceliumJunction from, MyceliumJunction to) {
        if (junctionA == from) {
            junctionA = to;
        } else if (junctionB == from) {
            junctionB = to;
        }
    }

    /**
     * Ha a lifetime értéke kisebb, mint nulla, akkor rögtön visszatér.
     * Egyébként csökkenti eggyel a lifetime értékét, majd ezután ha az nulla vagy kisebb lesz, akkor terminálja a fonalat.
     */
    @Override
    public void gameStep() {
        if (lifetime < 0) {
            return;
        }
        lifetime--;
        if (lifetime <= 0) {
            lifetime--;
            terminateConnection();
        }
    }
}
