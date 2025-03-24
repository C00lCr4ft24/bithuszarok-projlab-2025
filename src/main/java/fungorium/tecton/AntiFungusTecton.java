package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

/**
 * Egy olyan Tecton típust reprezentál, amelyen nem nőhet gombatest (Fungus).
 */
public class AntiFungusTecton extends Tecton {

    /**
     * Létrehoz egy új AntiFungusTecton példányt a megadott szomszédos Tecton-ok alapján.
     *
     * @param t A Tecton szomszédjai
     */
    public AntiFungusTecton(ArrayList<Tecton> t) { super(t); }

    /**
     * Létrehoz egy új, üres AntiFungusTecton példányt.
     */
    public AntiFungusTecton() { super(); }

    /**
     * Ellenőrzi, hogy van-e üres hely gombatest számára ezen a Tecton-on.
     */
    @Override
    public boolean isFungusSpaceEmpty() {
        printAction("isFungusSpaceEmpty");
        return false;
    }
}
