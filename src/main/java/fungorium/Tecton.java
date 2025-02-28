package fungorium;

import java.util.HashSet;
import java.util.Set;

public abstract class Tecton implements ITecton {

    protected HashSet<Spore> spores;
    protected HashSet<Tecton> neighbours;
    protected Mycelium currentMycelium;
    protected Fungus   currentFungus;

    public Tecton() {
        spores          = new HashSet<>();
        neighbours      = new HashSet<>();
        currentMycelium = null;
        currentFungus   = null;
    }

    public void setCurrentMycelium(Mycelium currentMycelium) { this.currentMycelium = currentMycelium; }
    public Mycelium getCurrentMycelium() { return currentMycelium; }

    public void setCurrentFungus(Fungus currentFungus) { this.currentFungus = currentFungus; }
    public Fungus getCurrentFungus() { return currentFungus; }

    public void addSpore(Spore spore) { this.spores.add(spore); }
    public Set<Spore> getSpores() { return spores; }
}
