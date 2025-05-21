package fungorium.view.frames;

import javax.swing.JFrame;
import javax.swing.JTextArea;


/**
 * A HelperFrame osztály egy új ablakot reprezentál.
 */
public class HelperFrame extends JFrame {
    private static final String rulesDescription = """            
                  Gombászok
               A gombászok képesek gombatestet növeszteni, gombafonalat növeszteni és spórákat szórni.
               A gombákszok pontot szereznek, ha növesztenek egy új gombatestet.
            
                  Rovarászok
               A rovarok képesek mozogni, gombafonalat vágni és spórát enni.
               A rovarok pontokat szereznek, ha megeszik a gombák spóráit.
             
                  Játék menete:
               A játék során a gombászok és rovarászok felváltva lépnek egymás után.
               Mindenki egy valamilyen akciót hajthat végre a saját körében.
               Lehet az akció végrehajtást kihagyni, a Lépés kihagyása gomb megnyomásával.""";

    
    public HelperFrame() {
        var text = new JTextArea(rulesDescription);
        text.setEditable(false);
        text.setFocusable(false);
        this.add(text);

        this.setTitle("Súgó");
        this.setSize(480,640);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
}
