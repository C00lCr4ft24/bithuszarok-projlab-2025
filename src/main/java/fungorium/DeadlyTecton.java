package fungorium;

public class DeadlyTecton extends Tecton {

    public DeadlyTecton() { super(); }

    @Override
    public void doEffect() throws Exception { if (currentFungus != null) currentFungus = null; throw new Exception(); }
}
