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

        System.out.println("\n");

        Fungus newFungus = new Fungus();

        System.out.println("uj fungus amit novesztenenk: " + newFungus);
        System.out.println("tec1 fungusa: " + tec1.getCurrentFungus());
        try {
            System.out.println("trying...");
            tec1.setCurrentFungus(newFungus);
            tec1.doEffect();
        } catch (Exception e) { System.out.println("ilyent nem lehet csinálni"); }
        System.out.println(tec1.getCurrentFungus());
    }
}