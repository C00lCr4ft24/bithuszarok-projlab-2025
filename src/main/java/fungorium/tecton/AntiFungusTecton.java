package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

/**
 * Egy olyan Tecton típust reprezentál, amelyen nem nőhet gombatest (Fungus).
 */
public class AntiFungusTecton extends Tecton {

    /**
     * Létrehoz egy új AntiFungusTecton példányt a megadott MyceliumJunction-ok listájával.
     *
     * @param myceliumJunctions Azok a MyceliumJunction-ok, amelyek a Tecton-on elhelyezkednek.
     */
    public AntiFungusTecton(ArrayList<MyceliumJunction> myceliumJunctions) { super(myceliumJunctions); }

    /**
     * Létrehoz egy új, üres AntiFungusTecton példányt.
     */
    public AntiFungusTecton() { super(); }

    /**
     * Ellenőrzi, hogy van-e üres hely gombatest számára ezen a Tecton-on.
     */
    @Override
    public boolean isFungusSpaceEmpty() {
        printAction("canFungusGrow");
        return false;
    }
}
