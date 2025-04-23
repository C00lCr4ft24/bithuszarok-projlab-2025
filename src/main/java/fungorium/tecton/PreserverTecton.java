package fungorium.tecton;

public class PreserverTecton extends Tecton {

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
