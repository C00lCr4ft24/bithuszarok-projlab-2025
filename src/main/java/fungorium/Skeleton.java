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
            drawMenu();
            command = scanner.nextInt();
            if(commands.get(command) != null) {
                commands.get(command).run();
            }
        }
        scanner.close();
    }

    private void drawMenu() {
        System.out.println("------------------------------------------------------------");
        System.err.println("--- Fungorium tesztkörnyezet ---");
        System.out.println("1. Üres tekton törése");
        System.out.println("2. Tört tekton újra törése");
        System.out.println("3. Tekton törés gombafonalakkal, gombatesttel és rovarral");
        System.out.println("4. Gombatest növesztés üres tektonra");
        System.out.println("5. Gombatest növesztés foglalt tektonra");
        System.out.println("6. Gombatest növesztés gombatest-mentes tektonra");
        System.out.println("7. Gombafonál növesztés üres kereszteződésmentes tektonra");
        System.out.println("8. Gombafonál növesztés foglalt kereszteződésmentes tektonra");
        System.out.println("9. Gombafonál növesztés gombafonál gyilkos tektonra");
        System.out.println("10. Gombafonál vágás több gombatest kapcsolattal");
        System.out.println("11. Gombafonál vágás egy gombatest kapcsolattal");
        System.out.println("12. Spóra szórás egy tekton távolságra");
        System.out.println("13. Spóra szórás két tekton távolságra");
        System.out.println("14. Rovar mozgás hatás nélkül");
        System.out.println("15. Rovar mozgás gyorsító hatással");
        System.out.println("16. Rovar mozgás lassító hatással");
        System.out.println("17. Rovar mozgás kábítás hatással");
        System.out.println("18. Rovar mozgás vágás tiltó hatással");
        System.out.println("19. Rovar gombafonál vágás hatás nélkül");
        System.out.println("20. Rovar gombafonál vágás gyorsító hatással");
        System.out.println("21. Rovar gombafonál vágás lassító hatással");
        System.out.println("22. Rovar gombafonál vágás kábítás hatással");
        System.out.println("23. Rovar gombafonál vágás vágás tiltó hatással");
        System.out.println("24. Rovar spóra evés");
        System.out.println("0. Kilépés");
        System.out.println("------------------------------------------------------------");
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
        i1 = new Insect(tkozep); // 10
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
        t3 = new AntiFungusTecton(); // 5
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
    public void insectFunctionsInit() {
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
        s1 = new AntiCutSpore(0, 0); //32
        t1.putASpore(s1); //33
        i1 = new Insect(t1); //34
    }

    /* Tesztesetek */

    public void emptyTectonBreaking() {
        System.err.println("-- Kiinduló állapot --");
        emptyTectonBreakingInit();
        System.out.println("-- Teszt indul --");
        Tecton temp = tkozep.split();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void brokenTectonBreakingAgain() {
        System.err.println("-- Kiinduló állapot --");
        brokenTectonBreakingAgainInit();
        System.out.println("-- Teszt indul --");
        Tecton temp = tkozep2.split();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void fullTectonBreak() {
        System.err.println("-- Kiinduló állapot --");
        fullTectonBreakInit();
        System.out.println("-- Teszt indul --");
        Tecton temp = tkozep.split();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void growFungusOnEmptyTecton() {
        System.err.println("-- Kiinduló állapot --");
        fungusGrowingInit();
        System.out.println("-- Teszt indul --");
        Fungus temp = m2.createFungus();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void growFungusOnOccupiedTecton() {
        System.err.println("-- Kiinduló állapot --");
        fungusGrowingInit();
        System.out.println("-- Teszt indul --");
        Fungus temp = m1.createFungus();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void growFungusOnAntiFungusTecton() {
        System.err.println("-- Kiinduló állapot --");
        fungusGrowingInit();
        System.out.println("-- Teszt indul --");
        Fungus temp = m3.createFungus();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void growMyceliumOnEmptyAntiCrossingTecton() {
        System.err.println("-- Kiinduló állapot --");
        myceliumGrowingInit();
        System.out.println("-- Teszt indul --");
        MyceliumJunction temp = t3.createMyceliumJunction();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void growMyceliumOnOccupiedAntiCrossingTecton() {
        System.err.println("-- Kiinduló állapot --");
        myceliumGrowingInit();
        System.out.println("-- Teszt indul --");
        MyceliumJunction temp = t2.createMyceliumJunction();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void growMyceliumAndJunctionOnAntiMyceliumTecton() {
        System.err.println("-- Kiinduló állapot --");
        myceliumGrowingInit();
        System.out.println("-- Teszt indul --");
        MyceliumJunction temp = t2.createMyceliumJunction();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void cutMyceliumWithMultipleFungusConnection() {
        System.err.println("-- Kiinduló állapot --");
        myceliumConnectionCutting2Init();
        System.out.println("-- Teszt indul --");
        c2.cutMe();
        c1.gameStep();
        c3.gameStep();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void cutMyceliumWithSingleFungusConnection() {
        System.err.println("-- Kiinduló állapot --");
        myceliumConnectionCutting1Init();
        System.out.println("-- Teszt indul --");
        c2.cutMe();
        c1.gameStep();
        c3.gameStep();
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void spreadSporeOnOneDistanceTecton() {
        System.err.println("-- Kiinduló állapot --");
        sporeSpreadingInit();
        System.out.println("-- Teszt indul --");
        f1.spreadSpores(t2);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void spreadSporeOnTwoDistanceTecton() {
        System.err.println("-- Kiinduló állapot --");
        sporeSpreadingInit();
        System.out.println("-- Teszt indul --");
        f1.spreadSpores(t5);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectMoveWithoutEffects() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        System.out.println("-- Teszt indul --");
        i1.move(t2);
        i1.move(t3);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectMoveWithSpeedUpEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.increaseSpeed();
        System.out.println("-- Teszt indul --");
        i1.move(t2);
        i1.move(t3);
        i1.move(t4);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectMoveWithSlowDownEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.decreaseSpeed();
        System.out.println("-- Teszt indul --");
        i1.move(t2);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectMoveWithStunEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.setStunned();
        System.out.println("-- Teszt indul --");
        i1.move(t2);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectMoveWithAntiCutEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.blockMyceliumCut();
        System.out.println("-- Teszt indul --");
        i1.move(t2);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectCutMyceliumWithoutEffects() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        System.out.println("-- Teszt indul --");
        i1.cutMyceliumConnection(c1);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectCutMyceliumWithSpeedUpEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.increaseSpeed();
        System.out.println("-- Teszt indul --");
        i1.cutMyceliumConnection(c1);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectCutMyceliumWithSlowDownEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.decreaseSpeed();
        System.out.println("-- Teszt indul --");
        i1.cutMyceliumConnection(c1);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectCutMyceliumWithStunEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.setStunned();
        System.out.println("-- Teszt indul --");
        i1.cutMyceliumConnection(c1);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectCutMyceliumWithAntiCutEffect() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        i1.blockMyceliumCut();
        System.out.println("-- Teszt indul --");
        i1.cutMyceliumConnection(c1);
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }

    public void insectEatSpore() {
        System.err.println("-- Kiinduló állapot --");
        insectFunctionsInit();
        System.out.println("-- Teszt indul --");
        try {
            i1.eatSpore(t1.getASpore());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-- Végső állapot --");
        System.out.println("-- Elvárt eredmény --");
    }
}
