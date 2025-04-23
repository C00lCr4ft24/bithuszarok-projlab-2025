package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

/**
 * Egy olyan Tecton típust reprezentál, amelyen nem nőhet több játékos Myceliumja,
 * kizárólag egyé.
 */
public class AntiCrossingTecton extends Tecton {

    /**
     * Létrehoz egy új AntiCrossingTecton példányt a megadott MyceliumJunction-ok listájával.
     *
     * @param t A Tecton szomszédjai
     */
    public AntiCrossingTecton(ArrayList<Tecton> t) { super(t); }

    /**
     * Létrehoz egy új, üres AntiCrossingTecton példányt.
     */
    public AntiCrossingTecton() { super(); }


    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    @Override
    protected Tecton createNewInstance() {
        return new AntiCrossingTecton();
    }

    /**
     * Ellenőrzi, hogy van-e még hely MyceliumJunction hozzáadására.
     */
    @Override
    public boolean hasSpaceForJunction() {
        printAction("hasSpaceForJunction");
        return myceliumJunctions.isEmpty();
    }
}
