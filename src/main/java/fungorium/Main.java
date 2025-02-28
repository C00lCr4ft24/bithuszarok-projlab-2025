package fungorium;

public class Main {
    public static void main(String[] args) {
        Tecton tec1 = new DeadlyTecton();
        Tecton tec2 = new DeadlyTecton();

        tec1.addSpore(new BigSpore());

        Insect insect = new Insect();
        insect.setCurrentTecton(tec1);

        System.out.println(insect.getCurrentTecton() + " sporai: " + insect.getCurrentTecton().getSpores());

        insect.eatSpore();

        System.out.println(insect.getCurrentTecton() + " sporai: " + insect.getCurrentTecton().getSpores());

        System.out.println(insect.getEatenSpores());

        tec1.setCurrentFungus(new Fungus());
        tec1.doEffect();
    }
}