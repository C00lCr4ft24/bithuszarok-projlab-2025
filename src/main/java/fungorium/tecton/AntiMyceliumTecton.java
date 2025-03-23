package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

/**
 * Egy olyan Tecton típust reprezentál, amelyen egy megadott kör után a rajta lévő Mycelium-ok
 * fixen elhalnak.
 */
public class AntiMyceliumTecton extends Tecton {

    /**
     * Létrehoz egy új AntiMyceliumTecton példányt a megadott MyceliumJunction-ok listájával.
     *
     * @param myceliumJunctions Azok a MyceliumJunction-ok, amelyek a Tecton-on elhelyezkednek.
     */
    public AntiMyceliumTecton(ArrayList<MyceliumJunction> myceliumJunctions) { super(myceliumJunctions); }

    /**
     * Létrehoz egy új, üres AntiMyceliumTecton példányt.
     */
    public AntiMyceliumTecton() { super(); }

    // Itt nem egyertelmu hogy a gameStep hogyan garantalja hogy Junction ne nohessen rajta
    // Ha ez minden kor elejen/vegen lefut akkor max csak le lehetne szedni itt rola a ranovo Junctionoket
    /**
     * Játék lépés során beállítja a rajta lévő MyceliumJunction-ben található
     * MyceliumConnection-ek fennmaradó életét.
     */
    @Override
    public void gameStep() {
        printAction("gameStep");

    }
}
