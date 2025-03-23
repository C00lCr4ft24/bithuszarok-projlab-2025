package fungorium.mycelium;

import fungorium.FungoriumEntity;

import java.util.ArrayList;

public class MyceliumJunction implements FungoriumEntity {

    private final ArrayList<MyceliumConnection> connections = new ArrayList<>();
    private Fungus currentFungus;

    public MyceliumJunction() { System.out.println("New MyceliumJunction created: " + this); }

    public void addConnection(MyceliumConnection c) {
        printAction("addConnection");
        connections.add(c);
    }

    public boolean hasAFungus() {
        printAction("hasAFungus");
        return currentFungus != null;
    }

    public void setConnectionLifeTime(int lifeTime) {
        printAction("setConectionLifeTime");
    }

    public void removeConnection(MyceliumConnection c) {
        printAction("removeConnection");
        connections.remove(c);
    }

    public Fungus createFungus() {
        printAction("createFungus");
        var newFungus = new Fungus();
        this.currentFungus = newFungus;
        return newFungus;
    }

    public void removeFungus() {
        printAction("removeFungus");
        currentFungus = null;
    }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
