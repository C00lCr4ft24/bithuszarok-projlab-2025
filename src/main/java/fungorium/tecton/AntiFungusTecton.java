package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

public class AntiFungusTecton extends Tecton {
    public AntiFungusTecton(ArrayList<MyceliumJunction> myceliumJunctions) { super(myceliumJunctions); }

    public AntiFungusTecton() { super(); }

    @Override
    public boolean isFungusSpaceEmpty() {
        printAction("canFungusGrow");
        return false;
    }
}
