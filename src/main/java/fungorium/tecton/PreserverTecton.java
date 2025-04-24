package fungorium.tecton;

public class PreserverTecton extends Tecton {

    /**
     * Létrehoz egy új PreserverTecton példányt a megadott MyceliumJunction-ok listájával.
     *
     * @param t A Tecton szomszédjai
     */
    public PreserverTecton(ArrayList<Tecton> t) {
        super(t);
    }

    /**
     * Létrehoz egy új, üres PreserverTecton példányt.
     */
    public PreserverTecton() {
        super();
    }

    /**
     * A saját osztály típusából csinál egy új alappéldányt és visszaadja azt.
     *
     * @return Az példány, melynek megyezik az osztály típusa azzal, akin hívódik.
     */
    @Override
    protected Tecton createNewInstance() {
        return new PreserverTecton();
    }

    @Override
    public void gameStep() {
        printAction("gameStep");

    }
}
