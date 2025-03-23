package fungorium.mycelium;

import fungorium.FungoriumEntity;
import fungorium.tecton.Tecton;

public class Fungus implements FungoriumEntity {

    private int fungusLevel;
    private int sporeLevel;

    public Fungus() { System.out.println("New Fungus created: " + this); }

    public void spreadSpores(Tecton target) { printAction("spreadSpores"); }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
