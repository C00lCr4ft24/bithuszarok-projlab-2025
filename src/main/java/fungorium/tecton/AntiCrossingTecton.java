package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

public class AntiCrossingTecton extends Tecton {

    public AntiCrossingTecton(ArrayList<MyceliumJunction> myceliumJunctions) { super(myceliumJunctions); }
    public AntiCrossingTecton() { super(); }

    @Override
    public boolean hasSpaceForJunction() {
        printAction("hasSpaceForJunction");
        return false;
    }
}
