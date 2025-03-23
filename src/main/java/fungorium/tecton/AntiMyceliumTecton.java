package fungorium.tecton;

import fungorium.mycelium.MyceliumJunction;

import java.util.ArrayList;

public class AntiMyceliumTecton extends Tecton {

    public AntiMyceliumTecton(ArrayList<MyceliumJunction> myceliumJunctions) { super(myceliumJunctions); }

    public AntiMyceliumTecton() { super(); }

    // Itt nem egyertelmu hogy a gameStep hogyan garantalja hogy Junction ne nohessen rajta
    // Ha ez minden kor elejen/vegen lefut akkor max csak le lehetne szedni itt rola a ranovo Junctionoket
    @Override
    public void gameStep() {
        printAction("gameStep");

    }
}
