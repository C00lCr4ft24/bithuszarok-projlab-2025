package fungorium.tecton;

import java.util.ArrayList;
import java.util.Random;

import fungorium.FungoriumEntity;
import fungorium.Insect;
import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.Spore;

/**
 * A Tecton osztály a játéktér felszínét alkotó különálló kéregdarabok alapját képezi.
 */
public class Tecton implements FungoriumEntity {

    /**
     * A szükséges spórák száma egy gomba növesztéséhez.
     */
    protected static final int REQ_NUTRIENT_TO_GROW_FUNGUS = 500;

    /**
     * A Tecton-hoz tartozó spórák tárolója.
     */
    private final ArrayList<Spore> spores = new ArrayList<>();

    /**
     * A Tecton-on található MyceliumJunction-ok tárolója.
     */
    protected final ArrayList<MyceliumJunction> myceliumJunctions = new ArrayList<>();

    /**
     * A szomszédos Tecton-ok listája.
     */
    private ArrayList<Tecton> TectonN = new ArrayList<>();

    private final ArrayList<Insect> insects = new ArrayList<>();

    /**
     * A Tecton állapota: törött vagy ép.
     */
    private boolean isBroken = false;

    /**
     * Létrehoz egy új Tecton példányt a megadott szomszédos Tecton-ok alapján.
     *
     * @param t A Tecton szomszédjai.
     */
    public Tecton(ArrayList<Tecton> t) {
        TectonN=t;
    }

    /**
     * Létrehoz egy új, alapértelmezett Tecton példányt.
     */
    public Tecton() {
        System.out.println("New Tecton created: " + this);
    }

    /**
     * Létrehoz egy új Tecton példányt, amely törött állapotban van.
     *
     * @param broken A Tecton állapota (törött).
     */
    public Tecton(boolean broken) { //--------------------------------------------------------------------------------------------------------
        this();
        this.isBroken = broken;
    }

    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     * split() függvény használja.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    protected Tecton createNewInstance() {
        return new Tecton();
    }


    /**
     * Visszaad egy spórát a Tecton-on található spórák közül.
     *
     * @return Egy spóra a Tecton-on lévő spórák közül.
     * @throws Exception Ha nincs spóra a Tecton-on.
     */
    public Spore getASpore() throws Exception { //--------------------------------------------------------------------------------------------------------
        printAction("getASpore");
        if(spores.isEmpty()) { throw new Exception("There is no spore on " + this); }
        return spores.get(0);
    }

    /**
     * Eltávolítja a szükséges tápanyagtartalmú spórát egy gomba növesztéséhez.
     *
     * @throws Exception Ha nincs elegendő spóra a gomba növesztéséhez.
     */
    public void removeSporeForFungus() throws Exception { //--------------------------------------------------------------------------------------------------------
        printAction("removeSporeForFungus");

        int availableNutrientAmount = 0;
        while (!spores.isEmpty()) {
            if  (availableNutrientAmount >= REQ_NUTRIENT_TO_GROW_FUNGUS) break;
            else availableNutrientAmount += spores.remove(0).getNutrientValue();
        }
        if(availableNutrientAmount < REQ_NUTRIENT_TO_GROW_FUNGUS) { throw new Exception("There is not enough nutrient to grow Fungus on " + this); }
    }

    /**
     * Kettétöri a Tecton-t, és visszaadja az új töredék Tecton-t.
     *
     * @return Az újonnan létrehozott Tecton töredék vagy `null`, ha a Tecton törött.
     */
    public Tecton split() {
        printAction("split");
        if (isBroken) {
            printAction("Tecton is already broken!");
            return null;
        }
        if (TectonN.size() <= 1) {
            return null;
        }
        Tecton newTecton = this.createNewInstance();
        newTecton.TectonN.add(this);

        int splitAtIndex = (TectonN.size() - 1) / 2;
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
                        newMyceliumJunction = newTecton.createMyceliumJunction();
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
    public MyceliumJunction createMyceliumJunction() { //--------------------------------------------------------------------------------------------------------
        printAction("createMyceliumJunction");
        MyceliumJunction junction = new MyceliumJunction(this);
        myceliumJunctions.add(junction);
        return junction;
    }

    /**
     * Ellenőrzi, hogy van-e üres hely gombatest számára ezen a Tecton-on.
     *
     * @return `true`, ha lehet gombatestet helyezni a Tecton-ra, különben `false`.
     */
    public boolean isFungusSpaceEmpty() { //--------------------------------------------------------------------------------------------------------
        printAction("isFungusSpaceEmpty");
        for(MyceliumJunction j : myceliumJunctions) {
            if(j.hasAFungus()) {
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
        printAction("hasSpaceForJunction");
        return true;
    }

    /**
     * Elhelyez egy új spórát a Tecton-on.
     *
     * @param spore A hozzáadni kívánt spóra.
     */
    public void putASpore(Spore spore) { //--------------------------------------------------------------------------------------------------------
        printAction("putASpore");
        spores.add(spore);
    }

    /**
     * Elhelyez egy {@link Insect} példányt a Tectonon.
     * @param insect ami rákerül a Tectonra.
     */
    public void putInsect(Insect insect) {
        printAction("putInsect");
        insects.add(insect);
    }
    /**
     * Eltávolítja az {@link Insect} példányt a Tectonról.
     * @param insect ami eltávolításra kerül.
     */
    public void removeInsect(Insect insect) {
        printAction("removeInsect");
        insects.remove(insect);
    }

    /**
     * Eltávolítja a megadott Sporet a Tectonról.
     * @param spore a megadott {@link Spore} objektum.
     */
    public void removeSpore(Spore spore) {
        printAction("removeSpore");
        spores.remove(spore);
    }

    /**
     * Eltávolítja a megadott MyceliumJunction-t a Tecton-on található listából.
     *
     * @param junction Az eltávolítandó MyceliumJunction.
     * @throws Exception Ha a megadott MyceliumJunction nem található a listában.
     */
    public void removeJunction(MyceliumJunction junction) throws Exception { //--------------------------------------------------------------------------------------------------------
        printAction("removeJunction");
        myceliumJunctions.remove(junction);
    }

    /**
     * Hozzáad egy szomszédos Tectont a jelenlegi Tecton-hoz.
     *
     * @param t A hozzáadni kívánt szomszédos Tecton.
     */
    public void setNeighbour(Tecton t){ //--------------------------------------------------------------------------------------------------------
        printAction("setNeighbour");
        TectonN.add(t);
    }

    /**
     * Végrehajtja a játék lépését a Tecton-on.
     */
    @Override
    public void gameStep() {  }
}
