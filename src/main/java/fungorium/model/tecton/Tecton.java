package fungorium.model.tecton;

import fungorium.TestFramework;
import fungorium.model.FungoriumEntity;
import fungorium.model.Insect;
import fungorium.model.mycelium.MyceliumConnection;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.player.Player;
import fungorium.model.spore.Spore;

import java.util.*;

/**
 * A Tecton osztály a játéktér felszínét alkotó különálló kéregdarabok alapját képezi.
 */
public class Tecton implements FungoriumEntity {

    /**
     * A szükséges spórák száma egy gomba növesztéséhez.
     */
    protected static final int REQ_NUTRIENT_TO_GROW_FUNGUS = 200;
    /**
     * A Tecton-on található MyceliumJunction-ok tárolója.
     */
    protected final ArrayList<MyceliumJunction> myceliumJunctions = new ArrayList<>();
    /**
     * A Tecton-hoz tartozó spórák tárolója.
     */
    protected final LinkedList<Spore> spores = new LinkedList<>();
    /**
     * A Tecton-on lévő rovarok tárolója.
     */
    protected final ArrayList<Insect> insects = new ArrayList<>();
    /**
     * A szomszédos Tecton-ok listája.
     */
    protected ArrayList<Tecton> TectonN = new ArrayList<>();
    /**
     * A Tecton állapota: törött vagy ép.
     */
    protected boolean isBroken = false;
    /**
     * A Tecton id-jét tartalmazó String.
     */
    protected String id;

    /**
     * Visszaadja az ID-t.
     * @return ID String.
     */
    public String getId() { return id; }

    public Tecton(String id) {
        this.id = id;
        String log = "Tecton " + id + " was created.";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    public ArrayList<Insect> getInsects() { return new ArrayList<>(insects); }

    public ArrayList<Tecton> getNeighborTectons() { return TectonN; }

    /**
     * Létrehoz egy új Tecton példányt a megadott szomszédos Tecton-ok alapján.
     *
     * @param t A Tecton szomszédjai.
     */
    public Tecton(ArrayList<Tecton> t) {
        this();
        TectonN = t;
    }

    /**
     * Létrehoz egy új, alapértelmezett Tecton példányt.
     */
    public Tecton() {}

    /**
     * Létrehoz egy új Tecton példányt, amely törött állapotban van.
     *
     * @param broken A Tecton állapota (törött).
     */
    public Tecton(boolean broken) {
        this();
        this.isBroken = broken;
    }

    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    protected Tecton createNewInstance() {
        return new Tecton(this.id + "-S");
    }

    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Visszaad egy spórát a Tecton-on található spórák közül.
     *
     * @return Egy spóra a Tecton-on lévő spórák közül.
     * @throws Exception Ha nincs spóra a Tecton-on.
     */
    public Spore getASpore() { //--------------------------------------------------------------------------------------------------------
        if (spores.isEmpty()) {
            return null;
        }
        return spores.getFirst();
    }

    /**
     * Visszaadja az összes Spórát a Tectonon.
     * @return az összes Spóra a Tectonon egy új listában.
     */
    public LinkedList<Spore> getAllSpores() {
        return new LinkedList<>(spores);
    }
    /**
     * Getter a Tecton MyceliumJunction listájához.
     * @return A Tecton MyceliumJunction listája.
     */
    public ArrayList<MyceliumJunction> getMyceliumJunctions() { return new ArrayList<>(myceliumJunctions); }

    public MyceliumJunction getMyceliumJunctionOfFungus() {
        for (MyceliumJunction j : myceliumJunctions) {
            if(j.hasAFungus()) return j;
        }
        return null;
    }

    /**
     * Eltávolítja a szükséges tápanyagtartalmú spórát egy gomba növesztéséhez.
     *
     * @throws Exception Ha nincs elegendő spóra a gomba növesztéséhez.
     */
    public void removeSporeForFungus() throws Exception { //--------------------------------------------------------------------------------------------------------
        int toBeDeletedNumberOfSpores = 0;
        int availableNutrientAmount = 0;
        while (!spores.isEmpty()) {
            if (availableNutrientAmount >= REQ_NUTRIENT_TO_GROW_FUNGUS) {
                break;
            } else {
                availableNutrientAmount += spores.get(toBeDeletedNumberOfSpores).getNutrientValue();
                toBeDeletedNumberOfSpores++;
            }
        }
        if (availableNutrientAmount < REQ_NUTRIENT_TO_GROW_FUNGUS) {
            throw new Exception("There is not enough nutrient to grow Fungus on " + this);
        }
        for (int i = 0; i < toBeDeletedNumberOfSpores; i++) {
            spores.removeFirst();
        }
    }

    /**
     * Kettétöri a Tecton-t, és visszaadja az új töredék Tecton-t.
     *
     * @return Az újonnan létrehozott Tecton töredék vagy `null`, ha a Tecton törött.
     */
    public Tecton split() {
        if (isBroken) {
            return null;
        }
        if (TectonN.size() <= 1) {
            isBroken = true;
            return null;
        }
        Tecton newTecton = this.createNewInstance();
        newTecton.TectonN.add(this);

        int splitAtIndex = ((TectonN.size() - 1) / 2) + 1;
        ArrayList<Tecton> tempNeighbours = new ArrayList<>(TectonN.subList(splitAtIndex, TectonN.size())); // Create a copy of the sublist

        // Resetting Tecton neighbours
        for (Tecton tempTecton : tempNeighbours) {
            TectonN.remove(tempTecton); // Modify the original list here
            tempTecton.TectonN.remove(this);
            tempTecton.TectonN.add(newTecton);
            newTecton.TectonN.add(tempTecton);
        }

        // Resetting MyceliumConnections
        for (MyceliumJunction mj : myceliumJunctions) {
            MyceliumJunction newMyceliumJunction = null;
            for (Tecton tempTecton : tempNeighbours) {
                MyceliumConnection toBeChangedConnection = mj.getMyceliumConnectionByOtherEndTecton(tempTecton);
                if (toBeChangedConnection != null) {
                    if (newMyceliumJunction == null) {
                        newMyceliumJunction = newTecton.createMyceliumJunction(mj.getPlayer());
                    }
                    mj.removeConnection(toBeChangedConnection);
                    newMyceliumJunction.addConnection(toBeChangedConnection);
                    toBeChangedConnection.changeThisJunctionTo(mj, newMyceliumJunction);
                }
            }
        }

        TectonN.add(newTecton);
        isBroken = true;
        newTecton.isBroken = true;
        return newTecton;
    }

    /**
     * Létrehoz egy új MyceliumJunction-t a Tecton-on, és visszaadja azt.
     *
     * @return A létrehozott MyceliumJunction.
     */
    public MyceliumJunction createMyceliumJunction(Player player) { //--------------------------------------------------------------------------------------------------------
        for (MyceliumJunction myceliumJunction : myceliumJunctions) {
            if (myceliumJunction.getPlayer().equals(player)) {
                return myceliumJunction;
            }
        }
        if(this.hasSpaceForJunction()) {
            return new MyceliumJunction("MJ-" + this.id + "-" + player.toString(), this, player);
        }
        throw new IllegalStateException("A kiválasztott tektonra már más játékos növesztett gombafonal csomópontot!");
    }

    public void addJunction(MyceliumJunction junction) {
        myceliumJunctions.add(junction);
        String log = "MyceliumJunction " + junction.getId() + " was added to " + id + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Ellenőrzi, hogy van-e üres hely gombatest számára ezen a Tecton-on.
     *
     * @return `true`, ha lehet gombatestet helyezni a Tecton-ra, különben `false`.
     */
    public boolean isFungusSpaceEmpty() { //--------------------------------------------------------------------------------------------------------
        for (MyceliumJunction j : myceliumJunctions) {
            if (j.hasAFungus()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ellenőrzi, hogy van-e még hely több gombafonal kereszteződése számára ezen a Tecton-on.
     *
     * @return `true`, ha lehet további MyceliumJunction-t hozzáadni, különben `false`.
     */
    public boolean hasSpaceForJunction() { //--------------------------------------------------------------------------------------------------------
        return true;
    }

    /**
     * Elhelyez egy új spórát a Tecton-on.
     *
     * @param spore A hozzáadni kívánt spóra.
     */
    public void putASpore(Spore spore) { //--------------------------------------------------------------------------------------------------------
        spores.addLast(spore);
        String log = "A new spore has landed on " + id + ".";
        System.out.println(log);
        TestFramework.logOutput(log);
    }

    /**
     * Elhelyez egy {@link Insect} példányt a Tectonon.
     *
     * @param insect ami rákerül a Tectonra.
     */
    public void putInsect(Insect insect) {
        insects.add(insect);
    }

    /**
     * Eltávolítja az {@link Insect} példányt a Tectonról.
     *
     * @param insect ami eltávolításra kerül.
     */
    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    /**
     * Eltávolítja a megadott Sporet a Tectonról.
     *
     * @param spore a megadott {@link Spore} objektum.
     */
    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }

    /**
     * Eltávolítja a megadott MyceliumJunction-t a Tecton-on található listából.
     *
     * @param junction Az eltávolítandó MyceliumJunction.
     */
    public void removeJunction(MyceliumJunction junction) { //--------------------------------------------------------------------------------------------------------
        myceliumJunctions.remove(junction);
    }

    /**
     * Hozzáad egy szomszédos Tectont a jelenlegi Tecton-hoz.
     *
     * @param t A hozzáadni kívánt szomszédos Tecton.
     */
    public void setNeighbour(Tecton t) { //--------------------------------------------------------------------------------------------------------
        if(t == null || TectonN.contains(t)) { return; } //Guard
        TectonN.add(t);
        //String log = id + " is now neighbour of " + t.id + ".";
        //System.out.println(log);
        //TestFramework.logOutput(log);
    }

    /**
     * Megnézi, hogy a kapott Tecton a megadott lépés távolságon belül van-e. (Egy lépés két szomszádos Tecton között történik.)
     *
     * @param neighbour A keresett Tecton, akit a megadott lépés számon belül el lehet-e érni
     * @param range     Lépésszám, ami belül keresni kell.
     * @return True-t ad vissza, ha megtalálja a keresett Tecton a lépésszámon belül, minden más esetben False-t, rögtön True-t ad vissza, ha neighbour megegyezik vele
     */
    public boolean isThisYourNeighbourInRange(Tecton neighbour, int range) {
        HashSet<Tecton> checkedTectons = new HashSet<>();
        if (this == neighbour) {
            return true;
        }
        return isThisYourNeighbourInRangeRecursiveHelper(neighbour, range, checkedTectons);
    }

    /**
     * isThisYourNeighbourInRange függvény rekurzív segéd függvénye, mely a tényleges keresést végzi.
     * Először körbenézi a saját szomszédjait, majd utána ha nem találta meg a keresett Tecton, akkor a szomszédjaira is meghívja ezt a függvényt eggyel csökkentett lépésszámmal.
     *
     * @param neighbour      A keresett Tecton, akit a megadott lépés számon belül el lehet-e érni
     * @param range          Lépésszám, ami belül keresni kell.
     * @param checkedTectons HashSet, mely tárolja azt, hogy mely Tectonokat ellenőrizte már a kereső algoritmus
     * @return True-t ad vissza, ha megtalálja a keresett Tecton a lépésszámon belül, minden más esetben False-t
     */
    private boolean isThisYourNeighbourInRangeRecursiveHelper(Tecton neighbour, int range, HashSet<Tecton> checkedTectons) {
        if (range <= 0) {
            return false;
        }
        checkedTectons.add(this);

        // Saját szomszédjainak ellenőrzése
        for (Tecton tempTecton : TectonN) {
            if (checkedTectons.contains(tempTecton)) {
                continue;
            }
            if (tempTecton == neighbour) {
                return true;
            }
        }
        // Saját szomszédjainak szomszédjai ellenőriztetése
        for (Tecton tempTecton : TectonN) {
            if (checkedTectons.contains(tempTecton)) {
                continue;
            }
            if (tempTecton.isThisYourNeighbourInRangeRecursiveHelper(neighbour, (range - 1), checkedTectons)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * Végrehajtja a játék lépését a Tecton-on.
     */
    @Override
    public void gameStep() {
    }
}
