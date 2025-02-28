package fungorium;

import java.util.ArrayList;
import java.util.List;

public class Insect {
    private Tecton currentTecton;
    private int eatenSpores = 0;

    public int getEatenSpores() {
        return eatenSpores;
    }

    public void addEatenSpore() {
        this.eatenSpores++;
    }

    public void setCurrentTecton(Tecton currentTecton) {
        this.currentTecton = currentTecton;
    }

    public Tecton getCurrentTecton() {
        return currentTecton;
    }

    public void eatSpore() {
        List<Spore> list = new ArrayList<Spore>(currentTecton.getSpores());
        Spore eatenSpore = list.get(0);
        eatenSpore.doEffect(this);
        System.out.println("Nyam");
        currentTecton.getSpores().remove(eatenSpore);
    }
}