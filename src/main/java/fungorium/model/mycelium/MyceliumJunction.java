package fungorium.model.mycelium;

import fungorium.TestFramework;
import fungorium.model.FungoriumEntity;
import fungorium.model.Insect;
import fungorium.model.player.Player;
import fungorium.model.tecton.Tecton;

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
     * Tecton, melyen a MyceliumJunction található.
     */
    private Tecton position;
    /**
     * A MyceliumJunction-hoz kapcsolódó gomba példánya.
     */
    private Fungus currentFungus;

    private Player player;

    private String id;
    public String getId() { return id; }
    public MyceliumJunction(String id, Tecton position) {
        this.id = id;
        this.position = position;
        position.addJunction(this);
    }

    public MyceliumJunction(String id, Tecton position, Player player) {
        this.id = id;
        this.position = position;
        this.player = player;
        position.addJunction(this);
    }
    
    /**
     * Létrehoz egy új MyceliumJunction példányt és beállítja a kapott Tecton pozíciót.
     *
     * @param position A beállítandó Tecton példány.
     */
    public MyceliumJunction(Tecton position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public void createConnectionToNeighbourTecton(Tecton tecton) throws IllegalStateException {
        if (this.getPosition().equals(tecton)) {
            throw new IllegalStateException("A kiválasztott tekton megegyezik a kiválasztott csomópont helyével!");
        }
        if (!this.getPosition().isThisYourNeighbourInRange(tecton, 1)) {
            throw new IllegalStateException("A kiválasztott tekton nem szomszédos a gombafonal csomóponttal!");
        }
        if (tecton.hasSpaceForJunction()) {
                MyceliumJunction newJunction = tecton.createMyceliumJunction(this.getPlayer());
                new MyceliumConnection(this, newJunction);
                return;
        }
        throw new IllegalStateException("A kiválasztott tektonra már más játékos növesztett gombafonal csomópontot!");
    }

    /**
     * Hozzáad egy új kapcsolatot ehhez a csomóponthoz.
     *
     * @param c A hozzáadandó MyceliumConnection példány.
     */
    public void addConnection(MyceliumConnection c) {
        connections.add(c);
    }

    public ArrayList<MyceliumConnection> getConnections() {
        return new ArrayList<>(connections);
    }

    /**
     * Ellenőrzi, hogy van-e gomba ezen a csomóponton.
     *
     * @return `true`, ha van gomba, különben `false`.
     */
    public boolean hasAFungus() {
        return currentFungus != null;
    }

    /**
     * Beállítja az összes kapcsolódó kapcsolat élettartamát.
     *
     * @param lifeTime Az új élettartam értéke.
     */
    public void setConnectionLifeTime(int lifeTime) {
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
        connections.remove(c);
        if (connections.isEmpty() && currentFungus == null) {
            position.removeJunction(this);
            position = null;
        }
    }

    /**
     * Létrehoz egy új gombát, tárolja azt a csomóponton, és visszaadja a létrehozott példányt. Levonja a létrehozáshoz szükséges tápanyagot a Tectonjáról
     *
     * @return Az újonnan létrehozott Fungus példány vagy null, ha nem tud újat létrehozni.
     */
    public Fungus createFungus(String id) {
        if (!position.isFungusSpaceEmpty()) {
            String log = "Can not grow new Fungus as there is already one on " + position.getId() + ".";
            System.out.println(log);
            TestFramework.logOutput(log);
            throw new IllegalStateException("Már van ezen a gombafonal csomóponton gombatest!");
        }
        try {
            position.removeSporeForFungus();
        } catch (Exception e) {
            String log = "Can not grow new Fungus as there is not enough spore on " + position.getId() + ".";
            System.out.println(log);
            TestFramework.logOutput(log);
            throw new IllegalStateException("Nincs elég spóra a gombatest növesztéshez!");
        }
        Fungus newFungus = new Fungus(id, this.getPlayer(), this);
        this.currentFungus = newFungus;
        return newFungus;
    }

    /**
     * Eltávolítja a tárolt gombát a csomópontról.
     */
    public void removeFungus() {
        currentFungus = null;
    }

    /**
     * Beállítja a kapott a Fungust magára, ha az null
     *
     * @param newFungus Új Fungus, amit beállítunk
     */
    public void setFungus(Fungus newFungus) {
        if(currentFungus == null) {
            currentFungus = newFungus;
        }
    }

    public Fungus getFungus() {
        return currentFungus;
    }

    /**
     * Megkeresi az a MyceliumConnection-át, melynek a másik vége a megadott Tectonon van.
     *
     * @param otherEnd másik Tecton melyen keresssük a MyceliumConnection végét.
     * @return Visszaadja a keresett MyceliumConnection-t, ha nem találta meg, akkor null ad vissza.
     */
    public MyceliumConnection getMyceliumConnectionByOtherEndTecton(Tecton otherEnd) {
        for (MyceliumConnection c : connections) {
            if (c.isThisYourOtherEndTecton(this, otherEnd)) {
                return c;
            }
        }
        return null;
    }

    /**
     * A Junction megpróbálja elfogyasztani a rajta lévő Insect-et és növeszteni egy új gombatestet.
     * Ha a rovar valóban rajta van és bénult, akkor megemészti és gombatestet növeszt (ha más nem akadályozza ezt).
     *
     * @param insect A megevésre szánt rovar.
     * @return Az új gombatest vagy null, ha nem tud újat létrehozni.
     */
    public Fungus tryConsumeInsect(String id, Insect insect) {
        if (!insect.isStunned()) {
            String log = "Can not consume insect " + insect.getId() + " as it is not stunned.";
            System.out.println(log);
            TestFramework.logOutput(log);
            return null;
        }
        if(insect.getPosition() != position) {
            String log = "Can not consume insect " + insect.getId() + " as it is not on tecton " + position.getId() + ".";
            System.out.println(log);
            TestFramework.logOutput(log);
            return null;
        }
        if(!position.isFungusSpaceEmpty()) {
            String log = "Can not consume insect " + insect.getId() + " as a fungus " + currentFungus.getId() + " is already on " + id + ".";
            System.out.println(log);
            TestFramework.logOutput(log);
            return null;
        }

        insect.getPosition().removeInsect(insect); // Insect eltavolitasa a Tectonrol
        Fungus newFungus = new Fungus(id, this.getPlayer(), this);
        this.currentFungus = newFungus;

        String log = "Insect " + insect.getId() + " was eaten and Fungus " + currentFungus.getId() + " grew on " + this.id + ".";
        System.out.println(log);
        TestFramework.logOutput(log);

        return newFungus;
    }

    /**
     * Visszaadja a pozíció Tectonját.
     *
     * @return pozíció Tectonja.
     */
    public Tecton getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * Végrehajtja a csomópont következő játék lépését.
     */
    @Override
    public void gameStep() {

    }
}
