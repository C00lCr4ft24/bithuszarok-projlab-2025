package fungorium;

public class DeadlyTecton extends Tecton {

    public DeadlyTecton() { super(); }

    @Override
    public void doEffect() {
        if(currentFungus != null) { currentFungus = null; System.out.println("Fungus can not grow here " + this); }
    }
}
