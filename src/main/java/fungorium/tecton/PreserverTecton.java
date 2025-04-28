package fungorium.tecton;

import fungorium.TestFramework;
import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

public class PreserverTecton extends Tecton {

    /**
     * Létrehoz egy új PreserverTecton példányt a megadott MyceliumJunction-ok listájával.
     *
     * @param t A Tecton szomszédjai
     */
    public PreserverTecton(ArrayList<Tecton> t) {
        super(t);
    }

    /**
     * Létrehoz egy új, üres PreserverTecton példányt.
     */
    public PreserverTecton() {
        super();
    }

    public PreserverTecton(String id) {
        this.id = id;
        String log = "PreserverTecton " + id + " was created.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    @Override
    protected Tecton createNewInstance() {
        return new PreserverTecton();
    }

    /**
     * Játék lépés során beállítja a rajta lévő MyceliumJunction-ben található
     * MyceliumConnection-ek fennmaradó életét végtelenre (negatív szám: -2).
     */
    @Override
    public void gameStep() {
        printAction("gameStep");
        for (MyceliumJunction mj : myceliumJunctions) {
            mj.setConnectionLifeTime(-2);
        }
    }
}
