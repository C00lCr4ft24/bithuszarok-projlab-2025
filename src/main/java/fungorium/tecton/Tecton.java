package fungorium.tecton;

import fungorium.FungoriumEntity;
import fungorium.mycelium.Fungus;
import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.Spore;

import java.util.ArrayList;
import java.util.Random;

public class Tecton implements FungoriumEntity {

    protected static final int REQ_SPORES_TO_GROW_FUNGUS = 2;
    protected static final int MAX_MYCELIUMJUNCTION_ON_TECTON = 5;

    private ArrayList<Spore> spores = new ArrayList<>();
    private ArrayList<MyceliumJunction> myceliumJunctions = new ArrayList<>();
    private Fungus fungus = null;

    public Tecton() { System.out.println("New Tecton created: " + this); }

    // Ez nem volt az analizis modellben de szukseges a splithez
    public Tecton(ArrayList<MyceliumJunction> myceliumJunctions) {
        this.myceliumJunctions = myceliumJunctions;
    }

    public Spore getASpore() throws Exception {
        printAction("getASpore");
        if(spores.isEmpty()) { throw new Exception("There is no spore on " + this); }
        return spores.get(0);
    }

    // itt vagy fixalva kell hogy legyen hogy hany spora vagy nutrient szukseges
    // egy fungus novesztesehez vagy be kellene adni ezt a szamot a metodusnak
    public void removeSporeForFungus() throws Exception {
        printAction("removeSporeForFungus");
        if(spores.isEmpty())                          { throw new Exception("There is no spore on " + this); }
        if(spores.size() < REQ_SPORES_TO_GROW_FUNGUS) { throw new Exception("There is not enough Spore on " + this); }
        for(int i = 0; i < REQ_SPORES_TO_GROW_FUNGUS; ++i) { getASpore(); }
        fungus = new Fungus();
    }
    public Tecton split() {
        printAction("split");

        Random rand = new Random();
        int splitAt = rand.nextInt(0, myceliumJunctions.size());

        ArrayList<MyceliumJunction> newMycJunctList = new ArrayList<>();
        for(int i = 0; i < splitAt; i++) {
            newMycJunctList.add(myceliumJunctions.get(i));
            myceliumJunctions.remove(i);
        }
        return new Tecton(newMycJunctList);
    }
    public MyceliumJunction createMyceliumJunction() {
        printAction("createMyceliumJunction");
        MyceliumJunction junction = new MyceliumJunction();
        myceliumJunctions.add(junction);
        return junction;
    }
    public boolean isFungusSpaceEmpty() {
        printAction("canFungusGrow");
        return fungus == null;
    }

    // Itt nem egyertelmu hogy egy Tectonnak hany Junctionja lehet.
    // Vagy ez is legyen egy fix szam vagy egy privat valtozo
    public boolean hasSpaceForJunction() {
        printAction("hasSpaceForJunction");
        return spores.size() < MAX_MYCELIUMJUNCTION_ON_TECTON;
    }
    public void putASpore(Spore spore) {
        printAction("putASpore");
        spores.add(spore);
    }
    public void removeJunction(MyceliumJunction junction) throws Exception {
        printAction("removeJunction");
        if(myceliumJunctions.contains(junction)) myceliumJunctions.remove(junction);
    }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
