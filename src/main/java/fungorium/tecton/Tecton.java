package fungorium.tecton;

import fungorium.FungoriumEntity;
import fungorium.mycelium.Fungus;
import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.Spore;

import java.util.ArrayList;
import java.util.Random;

/**
 * A Tecton osztály a játéktér felszínét alkotó különálló kéregdarabok alapját képezi.
 */
public class Tecton implements FungoriumEntity {

    /**
     * A szükséges spórák száma egy gomba növesztéséhez.
     */
    protected static final int REQ_SPORES_TO_GROW_FUNGUS = 2;

    /**
     * A Tecton-ra helyezhető MyceliumJunction-ok maximális száma.
     */
    protected static final int MAX_MYCELIUMJUNCTION_ON_TECTON = 5;

    /**
     * A Tecton-hoz tartozó spórák tárolója.
     */
    private ArrayList<Spore> spores = new ArrayList<>();

    /**
     * A Tecton-on található MyceliumJunction-ok tárolója.
     */
    private ArrayList<MyceliumJunction> myceliumJunctions = new ArrayList<>();

    /**
     * A szomszédos Tecton-ok listája.
     */
    private ArrayList<Tecton> TectonN = new ArrayList<>();

    /**
     * A Tecton-on elhelyezkedő gombatest.
     */
    private Fungus fungus = null;

    /**
     * A Tecton állapota: törött vagy ép.
     */
    private boolean isBroken = false;

    /**
     * Létrehoz egy új Tecton példányt a megadott spórák, MyceliumJunction-ok, szomszédos Tecton-ok és gombatest alapján.
     *
     * @param s A Tecton-hoz tartozó spórák.
     * @param m A Tecton-on található MyceliumJunction-ok.
     * @param t A Tecton szomszédjai.
     * @param f A Tecton-on található gombatest.
     */
    public Tecton(ArrayList<Spore> s, ArrayList<MyceliumJunction> m, ArrayList<Tecton> t, Fungus f) {spores= s; myceliumJunctions=m;TectonN=t; fungus=f; }

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
    public Tecton(boolean broken) {
        this();
        this.isBroken = broken;
    }

    // Ez nem volt az analizis modellben de szukseges a splithez
    /**
     * Létrehoz egy új Tecton példányt a megadott MyceliumJunction-okkal.
     *
     * @param myceliumJunctions A Tecton-on található MyceliumJunction-ok listája.
     */
    public Tecton(ArrayList<MyceliumJunction> myceliumJunctions) {
        this.myceliumJunctions = myceliumJunctions;
    }

    /**
     * Visszaad egy spórát a Tecton-on található spórák közül.
     *
     * @return Egy spóra a Tecton-on lévő spórák közül.
     * @throws Exception Ha nincs spóra a Tecton-on.
     */
    public Spore getASpore() throws Exception {
        printAction("getASpore");
        if(spores.isEmpty()) { throw new Exception("There is no spore on " + this); }
        return spores.get(0);
    }

    // itt vagy fixalva kell hogy legyen hogy hany spora vagy nutrient szukseges
    // egy fungus novesztesehez vagy be kellene adni ezt a szamot a metodusnak
    /**
     * Eltávolítja a szükséges számú spórát egy gomba növesztéséhez.
     *
     * @throws Exception Ha nincs elegendő spóra a gomba növesztéséhez.
     */
    public void removeSporeForFungus() throws Exception {
        printAction("removeSporeForFungus");
        if(spores.isEmpty())                          { throw new Exception("There is no spore on " + this); }
        if(spores.size() < REQ_SPORES_TO_GROW_FUNGUS) { throw new Exception("There is not enough Spore on " + this); }
        for(int i = 0; i < REQ_SPORES_TO_GROW_FUNGUS; ++i) { getASpore(); }
        fungus = new Fungus();
    }

    /**
     * Kettétöri a Tecton-t, és visszaadja az új töredék Tecton-t.
     *
     * @return Az újonnan létrehozott Tecton töredék vagy `null`, ha a Tecton törött.
     */
    public Tecton split() {
        printAction("split");

        if(isBroken) {
            printAction("itIsBroken");
            return null;
        }
        this.isBroken = true;
        Tecton tBroken = new Tecton(true);
        return tBroken;
        /// To Do implement
        /*Random rand = new Random();
        int splitAtIndex = (TectonN.size() - 1)/2;

        for(int i = (splitAtIndex+1); i < TectonN.size(); i++) {

        }

        ArrayList<MyceliumJunction> newMycJunctList = new ArrayList<>();
        for(int i = 0; i < splitAt; i++) {
            newMycJunctList.add(myceliumJunctions.get(i));
            myceliumJunctions.remove(i);
        }
        return new Tecton(newMycJunctList);
         */
    }

    /**
     * Létrehoz egy új MyceliumJunction-t a Tecton-on, és visszaadja azt.
     *
     * @return A létrehozott MyceliumJunction.
     */
    public MyceliumJunction createMyceliumJunction() {
        printAction("createMyceliumJunction");
        MyceliumJunction junction = new MyceliumJunction();
        myceliumJunctions.add(junction);
        return junction;
    }

    /**
     * Ellenőrzi, hogy van-e üres hely gombatest számára ezen a Tecton-on.
     *
     * @return `true`, ha lehet gombatestet helyezni a Tecton-ra, különben `false`.
     */
    public boolean isFungusSpaceEmpty() {
        printAction("canFungusGrow");
        return fungus == null;
    }

    // Itt nem egyertelmu hogy egy Tectonnak hany Junctionja lehet.
    // Vagy ez is legyen egy fix szam vagy egy privat valtozo
    /**
     * Ellenőrzi, hogy van-e még hely több gombafonal kereszteződése számára ezen a Tecton-on.
     *
     * @return `true`, ha lehet további MyceliumJunction-t hozzáadni, különben `false`.
     */
    public boolean hasSpaceForJunction() {
        printAction("hasSpaceForJunction");
        return spores.size() < MAX_MYCELIUMJUNCTION_ON_TECTON;
    }

    /**
     * Elhelyez egy új spórát a Tecton-on.
     *
     * @param spore A hozzáadni kívánt spóra.
     */
    public void putASpore(Spore spore) {
        printAction("putASpore");
        spores.add(spore);
    }

    /**
     * Eltávolítja a megadott MyceliumJunction-t a Tecton-on található listából.
     *
     * @param junction Az eltávolítandó MyceliumJunction.
     * @throws Exception Ha a megadott MyceliumJunction nem található a listában.
     */
    public void removeJunction(MyceliumJunction junction) throws Exception {
        printAction("removeJunction");
        if(myceliumJunctions.contains(junction)) myceliumJunctions.remove(junction);
    }

    /**
     * Hozzáad egy szomszédos Tectont a jelenlegi Tecton-hoz.
     *
     * @param t A hozzáadni kívánt szomszédos Tecton.
     */
    public void setNeighbour(Tecton t){
        TectonN.add(t);
    }

    /**
     * Végrehajtja a játék lépését a Tecton-on.
     */
    @Override
    public void gameStep() { printAction("gameStep"); }
}
