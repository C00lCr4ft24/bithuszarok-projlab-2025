package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

/**
 * Egy olyan Tecton típust reprezentál, amelyen egy megadott kör után a rajta lévő Mycelium-ok
 * fixen elhalnak.
 */
public class AntiMyceliumTecton extends Tecton {

    /**
     * Létrehoz egy új AntiMyceliumTecton példányt a megadott szomszédos Tecton-ok alapján.
     *
     * @param t A Tecton szomszédjai.
     */
    public AntiMyceliumTecton(ArrayList<Tecton> t) {super(t); }

    /**
     * Létrehoz egy új, üres AntiMyceliumTecton példányt.
     */
    public AntiMyceliumTecton() { super(); }


    /**
     * Játék lépés során beállítja a rajta lévő MyceliumJunction-ben található
     * MyceliumConnection-ek fennmaradó életét.
     */
    @Override
    public void gameStep() {
        printAction("gameStep");

    }
}
