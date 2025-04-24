package fungorium.mycelium;

import java.util.ArrayList;

import fungorium.FungoriumEntity;
import fungorium.Insect;
import fungorium.tecton.Tecton;

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

    /**
     * Létrehoz egy új MyceliumJunction példányt és beállítja a kapott Tecton pozíciót.
     *
     * @param position A beállítandó Tecton példány.
     */
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
        printAction("setConnectionLifeTime");
        for (MyceliumConnection c : connections) {
            c.setLifetime(lifeTime);
        }
    }

    /**
     * Eltávolítja az adott kapcsolatot ebből a csomópontból.
     * Ha a kapcsolatok tárolója üres, akkor törölteti magát a Tectonjáról, amin van.
     *
     * @param c Az eltávolítandó MyceliumConnection példány.
     */
    public void removeConnection(MyceliumConnection c) {
        printAction("removeConnection");
        connections.remove(c);
        if(connections.isEmpty()) {
            position.removeJunction(this);
        }
    }

    /**
     * Létrehoz egy új gombát, tárolja azt a csomóponton, és visszaadja a létrehozott példányt.
     *
     * @return Az újonnan létrehozott Fungus példány.
     */
    public Fungus createFungus() {
        printAction("createFungus");
        Fungus newFungus = new Fungus(this);
        this.currentFungus = newFungus;
        return newFungus;
    }

    /**
     * Megkeresi az a MyceliumConnection-át, melynek a másik vége a megadott Tectonon van.
     *
     * @param otherEnd másik Tecton melyen keresssük a MyceliumConnection végét.
     * @return Visszaadja a keresett MyceliumConnection-t, ha nem találta meg, akkor null ad vissza.
     */
    public MyceliumConnection getMyceliumConnectionByOtherEndTecton(Tecton otherEnd) {
        printAction("getMyceliumConnection");
        for (MyceliumConnection c : connections) {
            if(c.isThisYourOtherEndTecton(this, otherEnd)){
                return c;
            }
        }
        return null;
    }

    /**
     * A Junction megpróbálja elfogyasztani a rajta lévő Insect-et. 
     * Ha a rovar valóban rajta van és bénult, 
     * akkor megemészti és gombatestet növeszt (ha más nem akadályozza ezt).
     * @param insect A megevésre szánt rovar.
     */
    public void tryConsumeInsect(Insect insect) {
        printAction("tryConsumeInsect");
        if(!insect.isStunned() || insect.getPosition() != position) { return; } // Ha a rovar nincs stunnolva vagy nem ugyanazon Tectonon van

        insect.getPosition().removeInsect(insect); // Insect eltavolitasa a Tectonrol
        if(currentFungus != null) createFungus();  // Uj Fungus novesztese csak ha van hely neki
    }

    /**
     * Visszaadja a pozíció Tectonját.
     *
     * @return pozíció Tectonja.
     */
    public Tecton getPosition() {
        printAction("getPosition");
        return position;
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
