package fungorium.model.tecton;

import java.util.Random;

/**
 * Ez az osztály felelős random Tectonok generálásáért.
 */
public class TectonFactory {
    private static final Random random = new Random();
    private static Tecton newTecton;

    public static Tecton createTecton(String id) {
        newTecton = null;
        int typePercentage  = random.nextInt(100);

        //Basic Tecton 80% esellyel
        if(typePercentage < 80) { newTecton = new Tecton(id); }

        //Maradek tipusu Tectonok 5-5% esellyel
        if(typePercentage >= 80 && typePercentage < 85) { newTecton = new AntiCrossingTecton(id); }
        if(typePercentage >= 85 && typePercentage < 90) { newTecton = new AntiFungusTecton(id); }
        if(typePercentage >= 90 && typePercentage < 95) { newTecton = new AntiMyceliumTecton(id); }
        if(typePercentage >= 95)                        { newTecton = new PreserverTecton(id); }
        return newTecton;
    }
}
