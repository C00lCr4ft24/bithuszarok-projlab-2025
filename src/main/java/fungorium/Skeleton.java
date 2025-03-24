package fungorium;

import fungorium.tecton.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import fungorium.mycelium.*;
import fungorium.spore.*;

public class Skeleton {

    /**
     * Tároló a teszteset-szám + teszteset kombinációkhoz
     */
    private HashMap<Integer, Runnable> commands;

    private Tecton t1, t2, t3, t4, t5, t6, t7, tkozep, tkozep2;
    private Insect i1;
    private Spore s1;
    private Fungus f1, f2;
    private MyceliumJunction m1, m2, m3, m4;
    private MyceliumConnection c1, c2, c3;

    /**
     * Konstruktor a Szkeleton objektumhoz, beállítja a commands tároló elemeit
     */
    public Skeleton() {
        commands = new HashMap<>();
        commands.put(1, this::emptyTectonBreaking);
        commands.put(2, this::brokenTectonBreakingAgain);
        commands.put(3, this::fullTectonBreak);
        commands.put(4, this::growFungusOnEmptyTecton);
        commands.put(5, this::growFungusOnOccupiedTecton);
        commands.put(6, this::growFungusOnAntiFungusTecton);
        commands.put(7, this::growMyceliumOnEmptyAntiCrossingTecton);
        commands.put(8, this::growMyceliumOnOccupiedAntiCrossingTecton);
        commands.put(9, this::growMyceliumAndJunctionOnAntiMyceliumTecton);
        commands.put(10, this::cutMyceliumWithMultipleFungusConnection);
        commands.put(11, this::cutMyceliumWithSingleFungusConnection);
        commands.put(12, this::spreadSporeOnOneDistanceTecton);
        commands.put(13, this::spreadSporeOnTwoDistanceTecton);
        commands.put(14, this::insectMoveWithoutEffects);
        commands.put(15, this::insectMoveWithSpeedUpEffect);
        commands.put(16, this::insectMoveWithSlowDownEffect);
        commands.put(17, this::insectMoveWithStunEffect);
        commands.put(18, this::insectMoveWithAntiCutEffect);
        commands.put(19, this::insectCutMyceliumWithoutEffects);
        commands.put(20, this::insectCutMyceliumWithSpeedUpEffect);
        commands.put(21, this::insectCutMyceliumWithSlowDownEffect);
        commands.put(22, this::insectCutMyceliumWithStunEffect);
        commands.put(23, this::insectCutMyceliumWithAntiCutEffect);
        commands.put(24, this::insectEatSpore);
    }

    public void start() {
        int command = 1;
        Scanner scanner = new Scanner(System.in);
        while (command != 0) {
            command = scanner.nextInt();
            if(commands.get(command) != null) {
                commands.get(command).run();
            }
        }
        scanner.close();
    }

    /* Inicializáló függvények */

    /**
     * Üres tekton törés tesztesetének inicializáló függvénye
     */
    public void emptyTectonBreakingInit() {
        t1 = new Tecton(); // 1
        t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new Tecton(); // 5
        t2.setNeighbour(t3); // 6
        t3.setNeighbour(t2); // 7
        t3.setNeighbour(t1); // 8
        t1.setNeighbour(t3); // 9
        tkozep = new Tecton(); // 10
        t1.setNeighbour(tkozep); // 11
        t2.setNeighbour(tkozep); // 12
        t3.setNeighbour(tkozep); // 13
        tkozep.setNeighbour(t1); // 14
        tkozep.setNeighbour(t2); // 15
        tkozep.setNeighbour(t3); // 16
    }

    /**
     * Eltört tekton törés tesztesetének inicializáló függvénye
     */
    public void brokenTectonBreakingAgainInit() {
        t1 = new Tecton(); // 1
        t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new Tecton(); // 5
        t2.setNeighbour(t3); // 6
        t3.setNeighbour(t2); // 7
        t3.setNeighbour(t1); // 8
        t1.setNeighbour(t3); // 9
        tkozep = new Tecton(true); // 10
        t1.setNeighbour(tkozep); // 11
        t2.setNeighbour(tkozep); // 12
        t3.setNeighbour(tkozep); // 13
        tkozep.setNeighbour(t1); // 14
        tkozep.setNeighbour(t2); // 15
        tkozep.setNeighbour(t3); // 16
        tkozep.split(); // 17
        tkozep2 = new Tecton(true); // 18
        tkozep.setNeighbour(tkozep2); // 19
        t1.setNeighbour(tkozep2); // 20
        t2.setNeighbour(tkozep2); // 21
        tkozep2.setNeighbour(tkozep); // 22
        tkozep2.setNeighbour(t1); // 23
        tkozep2.setNeighbour(t2); //24
    }

    /**
     * További játékelemeket tartalmazó tekton törés tesztesetének inicializáló függvénye
     */
    public void fullTectonBreakInit(){
        t1 = new Tecton(); // 1
        t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t2); // 4
        tkozep = new Tecton(); // 5
        t1.setNeighbour(tkozep); // 6
        t2.setNeighbour(tkozep); // 7
        tkozep.setNeighbour(t1); // 8
        tkozep.setNeighbour(t2); // 9
        i1 = new Insect(); // 10
        m1 = t1.createMyceliumJunction(); // 11 & 12
        m2 = tkozep.createMyceliumJunction(); // 13 & 14
        m3 = t2.createMyceliumJunction(); // 15 & 16
        c1 = new MyceliumConnection(m1, m2);
        m1.addConnection(c1);
        m2.addConnection(c1);
        c2 = new MyceliumConnection(m2, m3);
        m2.addConnection(c2);
        m3.addConnection(c2);
        f1 = m2.createFungus();
    }

    /**
     * Spóra szórás teszteseteinek inicializáló függvénye
     */
    public void sporeSpreadingInit(){
        t1 = new Tecton(); // 1
        m1 = t1.createMyceliumJunction(); // 2 & 3
        f1 = m1.createFungus(); // 4
        t2 = new Tecton(); // 5
        t1.setNeighbour(t2); // 6
        t2.setNeighbour(t1); // 7
        t3 = new Tecton(); // 8
        t1.setNeighbour(t3); // 9
        t2.setNeighbour(t3); // 10
        t3.setNeighbour(t2); // 11
        t3.setNeighbour(t1); // 12
        t4 = new Tecton(); // 13
        t1.setNeighbour(t4); // 14
        t3.setNeighbour(t4); // 15
        t4.setNeighbour(t1); // 16
        t4.setNeighbour(t3); // 17
        t5 = new Tecton(); // 18
        t2.setNeighbour(t5); // 19
        t5.setNeighbour(t2); // 20
        t6 = new Tecton(); // 21
        t3.setNeighbour(t6); // 22
        t5.setNeighbour(t6); // 23
        t6.setNeighbour(t3); // 24
        t6.setNeighbour(t5); // 25
        t7 = new Tecton(); // 26
        t4.setNeighbour(t7); // 27
        t6.setNeighbour(t7); // 28
        t7.setNeighbour(t4); // 29
        t7.setNeighbour(t6); // 30
    }

    /**
     * Gombatest növesztés teszteseteinek inicializáló függvénye
     */
    public void fungusGrowingInit(){
        t1 = new Tecton(); // 1
        t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        m1 = t1.createMyceliumJunction(); // 10 & 11
        f1 = m1.createFungus(); // 12
        m2 = t2.createMyceliumJunction(); // 13 & 14
        m3 = t3.createMyceliumJunction(); // 15 & 16
        c1 = new MyceliumConnection(m1, m2); // 17
        m1.addConnection(c1); // 18
        m2.addConnection(c1); // 19
        c2 = new MyceliumConnection(m2, m3); // 20
        m2.addConnection(c2); // 21
        m3.addConnection(c2); // 22
        s1 = new SpeedUpSpore(0, 0);t2.putASpore(s1); // 23
    }

    /**
     * Gombafonál növesztés teszteseteinek inicializáló függvénye
     */
    public void myceliumGrowingInit(){
        t1 = new Tecton(); // 1
        t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        t4 = new AntiMyceliumTecton(); // 10
        t1.setNeighbour(t4); // 11
        t3.setNeighbour(t4); // 12
        t4.setNeighbour(t1); // 13
        t4.setNeighbour(t3); // 14
        m1 = t1.createMyceliumJunction(); // 15 & 16
        f1 = m1.createFungus(); // 17
        m2 = t2.createMyceliumJunction(); // 18 & 19
        c1 = new MyceliumConnection(m1, m2); // 20
        m1.addConnection(c1);
        m2.addConnection(c1);
    }

    /**
     * Gombafonál elvágás tesztesetének inicializáló függvénye (1. verzió)
     */
    public void myceliumConnectionCutting1Init(){
        t1 = new Tecton(); // 1
        t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        t4 = new AntiMyceliumTecton(); // 10
        t3.setNeighbour(t4); // 11
        t4.setNeighbour(t3); // 12
        m1 = t1.createMyceliumJunction(); // 13 & 14
        f1 = m1.createFungus(); // 15
        m2 = t2.createMyceliumJunction(); // 16 & 17
        m3 = t3.createMyceliumJunction(); // 18 & 19
        m4 = t4.createMyceliumJunction(); // 20 & 21
        c1 = new MyceliumConnection(m1, m2); // 22
        m1.addConnection(c1); // 23
        m2.addConnection(c1); // 24
        c2 = new MyceliumConnection(m2, m3); // 25
        m2.addConnection(c2); // 26
        m3.addConnection(c2); // 27
        c3 = new MyceliumConnection(m3, m4); // 28
        m3.addConnection(c3); // 29
        m4.addConnection(c3); // 30
    }

    /**
     * Gombafonál elvágás tesztesetének inicializáló függvénye (2. verzió)
     */
    public void myceliumConnectionCutting2Init(){
        t1 = new Tecton(); // 1
        t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        t4 = new AntiMyceliumTecton(); // 10
        t3.setNeighbour(t4); // 11
        t4.setNeighbour(t3); // 12
        m1 = t1.createMyceliumJunction(); // 13 & 14
        f1 = m1.createFungus(); // 15
        m2 = t2.createMyceliumJunction(); // 16 & 17
        m3 = t3.createMyceliumJunction(); // 18 & 19
        m4 = t4.createMyceliumJunction(); // 20 & 21
        c1 = new MyceliumConnection(m1, m2); // 22
        m1.addConnection(c1); // 23
        m2.addConnection(c1); // 24
        c2 = new MyceliumConnection(m2, m3); // 25
        m2.addConnection(c2); // 26
        m3.addConnection(c2); // 27
        c3 = new MyceliumConnection(m3, m4); // 28
        m3.addConnection(c3); // 29
        m4.addConnection(c3); // 30
        f2 = m4.createFungus(); // 31
    }

    /**
     * Rovar funkciók teszteseteinek inicializáló függvénye
     */
    public void insectFunctionsInit(){
        t1 = new Tecton(); // 1
        t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        t4 = new AntiMyceliumTecton(); // 10
        t3.setNeighbour(t4); // 11
        t4.setNeighbour(t3); // 12
        m1 = t1.createMyceliumJunction(); // 13 & 14
        f1 = m1.createFungus(); // 15
        m2 = t2.createMyceliumJunction(); // 16 & 17
        m3 = t3.createMyceliumJunction(); // 18 & 19
        m4 = t4.createMyceliumJunction(); // 20 & 21
        c1 = new MyceliumConnection(m1, m2); // 22
        m1.addConnection(c1); // 23
        m2.addConnection(c1); // 24
        c2 = new MyceliumConnection(m2, m3); // 25
        m2.addConnection(c2); // 26
        m3.addConnection(c2); // 27
        c3 = new MyceliumConnection(m3, m4); // 28
        m3.addConnection(c3); // 29
        m4.addConnection(c3); // 30
        f2 = m4.createFungus(); // 31
        s1 = new AntiCutSpore(0, 0); t1.putASpore(s1);
    }

    /* Tesztesetek */

    public void emptyTectonBreaking() {
        System.err.println("-- Kiinduló állapot --");
        emptyTectonBreakingInit();
        System.out.println("-- Teszt indul --");
        tkozep.split();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void brokenTectonBreakingAgain() {
        brokenTectonBreakingAgainInit();
    }

    public void fullTectonBreak() {
        fullTectonBreakInit();
    }

    public void growFungusOnEmptyTecton() {
        fungusGrowingInit();
    }

    public void growFungusOnOccupiedTecton() {
        fungusGrowingInit();
    }

    public void growFungusOnAntiFungusTecton() {
        fungusGrowingInit();
    }

    public void growMyceliumOnEmptyAntiCrossingTecton() {
        myceliumGrowingInit();
    }

    public void growMyceliumOnOccupiedAntiCrossingTecton() {
        myceliumGrowingInit();
    }

    public void growMyceliumAndJunctionOnAntiMyceliumTecton() {
        myceliumGrowingInit();
    }

    public void cutMyceliumWithMultipleFungusConnection() {
        myceliumConnectionCutting1Init();
    }

    public void cutMyceliumWithSingleFungusConnection() {
        myceliumConnectionCutting2Init();
    }

    public void spreadSporeOnOneDistanceTecton() {
        sporeSpreadingInit();
    }

    public void spreadSporeOnTwoDistanceTecton() {
        sporeSpreadingInit();
    }

    public void insectMoveWithoutEffects() {
        insectFunctionsInit();
    }

    public void insectMoveWithSpeedUpEffect() {
        insectFunctionsInit();
    }

    public void insectMoveWithSlowDownEffect() {
        insectFunctionsInit();
    }

    public void insectMoveWithStunEffect() {
        insectFunctionsInit();
    }

    public void insectMoveWithAntiCutEffect() {
        insectFunctionsInit();
    }

    public void insectCutMyceliumWithoutEffects() {
        insectFunctionsInit();
    }

    public void insectCutMyceliumWithSpeedUpEffect() {
        insectFunctionsInit();
    }

    public void insectCutMyceliumWithSlowDownEffect() {
        insectFunctionsInit();
    }

    public void insectCutMyceliumWithStunEffect() {
        insectFunctionsInit();
    }

    public void insectCutMyceliumWithAntiCutEffect() {
        insectFunctionsInit();
    }

    public void insectEatSpore() {
        insectFunctionsInit();
    }
}
