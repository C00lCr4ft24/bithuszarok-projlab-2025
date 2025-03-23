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
     * @param myceliumJunctions Azok a MyceliumJunction-ok, amelyek a Tecton-on elhelyezkednek.
     */
    public AntiCrossingTecton(ArrayList<MyceliumJunction> myceliumJunctions) { super(myceliumJunctions); }

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
        return false;
    }
}
