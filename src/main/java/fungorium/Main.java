package fungorium;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tecton tec1 = new DeadlyTecton();
        Tecton tec2 = new NormalTecton();

        tec1.addSpore(new BigSpore());

        Insect insect = new Insect();
        insect.setCurrentTecton(tec1);

        System.out.println(insect.getCurrentTecton() + " sporai: " + insect.getCurrentTecton().getSpores());

        insect.eatSpore();

        System.out.println(insect.getCurrentTecton() + " sporai: " + insect.getCurrentTecton().getSpores());

        System.out.println(insect.getEatenSpores());

        System.out.println("\n");

        boolean isRunning = true;

        while(isRunning)
        {
            Fungus newFungus = new Fungus();
            System.out.println("\n\nuj fungus amit novesztenenk: " + newFungus);
            System.out.println("01 tec1: " + tec1.getClass());
            System.out.println("02 tec2: " + tec2.getClass());
            System.out.print("Melyik tektonra növesztene: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            int choice = Integer.parseInt(input);
            Tecton selectedTecton;
            switch (choice) {
                case 1:
                    selectedTecton = tec1;
                    break;
                case 2:
                    selectedTecton = tec2;
                    break;
                default:
                    selectedTecton = tec1;
                    break;
            }
            System.out.println(selectedTecton + " fungusa: " + selectedTecton.getCurrentFungus());
            try {
                System.out.println("gombatestnovesztes probalkozasa...");
                selectedTecton.setCurrentFungus(newFungus);
                selectedTecton.doEffect();
            } catch (Exception e) { System.out.println("ilyent nem lehet csinálni"); }
            System.out.println(selectedTecton + " fungusa: " + selectedTecton.getCurrentFungus());
        }





    }
}