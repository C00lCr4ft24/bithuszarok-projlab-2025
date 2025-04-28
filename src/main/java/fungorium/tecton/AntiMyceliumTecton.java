package fungorium.tecton;

import fungorium.TestFramework;
import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;
import java.util.Random;

/**
 * Egy olyan Tecton típust reprezentál, amelyen egy megadott kör után a rajta lévő Mycelium-ok
 * fixen elhalnak.
 */
public class AntiMyceliumTecton extends Tecton {

    private int myceliumLifetime;

    /**
     * Létrehoz egy új AntiMyceliumTecton példányt a megadott szomszédos Tecton-ok alapján.
     *
     * @param t A Tecton szomszédjai.
     */
    public AntiMyceliumTecton(ArrayList<Tecton> t) {
        super(t);
        initLifetime();
    }

    /**
     * Létrehoz egy új, üres AntiMyceliumTecton példányt.
     */
    public AntiMyceliumTecton() {
        super();
        initLifetime();
    }

    public AntiMyceliumTecton(String id) {
        this.id = id;
        initLifetime();
        String log = "AntiMyceliumTecton " + id + " was created.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Inicializál egy random élettartam-értéket ameddig legfeljebb élhet egy {@link MyceliumJunction}
     */
    private void initLifetime() {
        var rnd = new Random();
        myceliumLifetime = rnd.nextInt(1, 5);
    }

    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    @Override
    protected Tecton createNewInstance() {
        return new AntiMyceliumTecton();
    }

    /**
     * Játék lépés során beállítja a rajta lévő MyceliumJunction-ben található
     * MyceliumConnection-ek fennmaradó életét.
     */
    @Override
    public void gameStep() {
        printAction("gameStep");
        for (MyceliumJunction mj : myceliumJunctions) {
            mj.setConnectionLifeTime(myceliumLifetime);
        }
    }
}
