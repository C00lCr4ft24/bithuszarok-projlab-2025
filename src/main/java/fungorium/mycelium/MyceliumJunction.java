package fungorium.mycelium;

import fungorium.FungoriumEntity;
import fungorium.tecton.Tecton;

import java.util.ArrayList;

/**
 * A MyceliumJunction osztály egy gombafonal csomópontot reprezentál, amely felelős a kapcsolatok
 * és a tárolt gombák kezeléséért.
 */
public class MyceliumJunction implements FungoriumEntity {

    /**
     * A MyceliumConnection példányok listája, amelyek ehhez a csomóponthoz tartoznak.
     */
    private final ArrayList<MyceliumConnection> connections = new ArrayList<>();

    /**
     * A MyceliumJunction-hoz kapcsolódó gomba példánya.
     */
    private Fungus currentFungus;

    /**
     * Tecton, melyen a MyceliumJunction található.
     */
    private Tecton position;

    /**
     * Létrehoz egy új MyceliumJunction példányt.
     */
    public MyceliumJunction() { System.out.println("New MyceliumJunction created: " + this); }

    public MyceliumJunction(Tecton position) {
        this();
        this.position = position;
    }

    /**
     * Hozzáad egy új kapcsolatot ehhez a csomóponthoz.
     *
     * @param c A hozzáadandó MyceliumConnection példány.
     */
    public void addConnection(MyceliumConnection c) {
        printAction("addConnection");
        connections.add(c);
    }

    /**
     * Ellenőrzi, hogy van-e gomba ezen a csomóponton.
     *
     * @return `true`, ha van gomba, különben `false`.
     */
    public boolean hasAFungus() {
        printAction("hasAFungus");
        return currentFungus != null;
    }

    /**
     * Beállítja az összes kapcsolódó kapcsolat élettartamát.
     *
     * @param lifeTime Az új élettartam értéke.
     */
    public void setConnectionLifeTime(int lifeTime) {
        printAction("setConectionLifeTime");
    }

    /**
     * Eltávolítja az adott kapcsolatot ebből a csomópontból.
     *
     * @param c Az eltávolítandó MyceliumConnection példány.
     */
    public void removeConnection(MyceliumConnection c) {
        printAction("removeConnection");
        connections.remove(c);
    }

    /**
     * Létrehoz egy új gombát, tárolja azt a csomóponton, és visszaadja a létrehozott példányt.
     *
     * @return Az újonnan létrehozott Fungus példány.
     */
    public Fungus createFungus() {
        printAction("createFungus");
        var newFungus = new Fungus(this);
        this.currentFungus = newFungus;
        return newFungus;
    }

    /**
     * Eltávolítja a tárolt gombát a csomópontról.
     */
    public void removeFungus() {
        printAction("removeFungus");
        currentFungus = null;
    }

    /**
     * Végrehajtja a csomópont következő játék lépését.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
