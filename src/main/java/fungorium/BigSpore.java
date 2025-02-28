package fungorium;

import java.awt.*;

public class BigSpore extends Spore {

    @Override
    public void doEffect(Insect insect) {
        insect.addEatenSpore();
        insect.addEatenSpore();
        System.out.println("\nBigSpore zabálás történt, dupla kaja!");
    }
}
