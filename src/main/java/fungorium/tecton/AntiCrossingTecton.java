package fungorium.tecton;

import fungorium.TestFramework;

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
    public AntiCrossingTecton(ArrayList<Tecton> t) {
        super(t);
    }

    /**
     * Létrehoz egy új, üres AntiCrossingTecton példányt.
     */
    public AntiCrossingTecton() {
        super();
    }

    public AntiCrossingTecton(String id) {
        this.id = id;
        String log = "AntiCrossingTecton " + id + " was created.";
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
        return new AntiCrossingTecton();
    }

    /**
     * Ellenőrzi, hogy van-e még hely MyceliumJunction hozzáadására.
     */
    @Override
    public boolean hasSpaceForJunction() {
        return myceliumJunctions.isEmpty();
    }

    @Override
    public void gameStep() {

    }
}
