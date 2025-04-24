package fungorium.tecton;

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
    public AntiFungusTecton(ArrayList<Tecton> t) {
        super(t);
    }

    /**
     * Létrehoz egy új, üres AntiFungusTecton példányt.
     */
    public AntiFungusTecton() {
        super();
    }

    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    @Override
    protected Tecton createNewInstance() {
        return new AntiFungusTecton();
    }

    /**
     * Ellenőrzi, hogy van-e üres hely gombatest számára ezen a Tecton-on.
     */
    @Override
    public boolean isFungusSpaceEmpty() {
        printAction("isFungusSpaceEmpty");
        return false;
    }
}
