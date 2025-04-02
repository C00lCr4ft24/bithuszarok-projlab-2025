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
     * Ellenőrzi, hogy van-e még hely MyceliumJunction hozzáadására.
     */
    @Override
    public boolean hasSpaceForJunction() {
        printAction("hasSpaceForJunction");
        return myceliumJunctions.isEmpty();
    }
}
