package fungorium;

import fungorium.model.Insect;
import fungorium.model.mycelium.Fungus;
import fungorium.model.mycelium.MyceliumConnection;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.spore.Spore;
import fungorium.model.spore.SporeFactory;
import fungorium.model.spore.SporeTypes;
import fungorium.model.tecton.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestFramework {
    /**
     * Az aktuálisan futó teszt neve
     */
    private static String currentTestName;
    /**
     * Az aktuálisan futó teszt üzenetei
     */
    private static List<String> logMessages = new ArrayList<>();

    private static Scanner systemIn = new Scanner(System.in);

    private static boolean interactiveMode = false;

    private static boolean exit = false;

    /**
     * Privát konstruktor a példányosítás elkerülése érdekében
     */
    private TestFramework() {
    }

    public static void testMenu(GameModel game) {
        while (!exit) {
            System.out.println("1. Interaktív mód");
            System.out.println("2. Megadott teszt futtatása");
            System.out.println("3. Összes teszt futtatása");
            System.out.println("4. Kilépés");
            switch (systemIn.nextInt()) {
                case 1 -> {
                    interactiveMode = true;
                    System.out.println("--- INTERAKTÍV MÓD ---");
                    while (interactiveMode) {
                        executeTestLine(systemIn.nextLine(), game);
                    }
                    System.out.println("--- INTERAKTÍV MÓD VÉGE ---");
                }
                case 2 -> {
                    System.out.println("Add meg a futtatandó teszt nevét:");
                    runTest(systemIn.next(), game);
                }

                case 3 -> {
                    try (Scanner testListScanner = new Scanner(new File("tests/testslist.txt"))) {
                        List<String> testNames = new ArrayList<>();
                        while (testListScanner.hasNext()) {
                            testNames.add(testListScanner.next());
                        }
                        for (String testName : testNames) {
                            runTest(testName, game);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 4 -> exit = true;
            }
        }
    }

    /**
     * Eltárolja az üzenetet amit a hívó átadott paraméterként
     * 
     * @param str az üzenet
     */
    public static void logOutput(String str) {
        logMessages.add(str);
    }

    /**
     * Lefuttatja a megadott nevű tesztet
     * 
     * @param testname a teszt neve (fájlkiterjesztés nélkül)
     * @param game     a játék modellje
     */
    public static void runTest(String testname, GameModel game) {
        currentTestName = testname;
        logMessages.clear();
        game.resetGameModel(0, 0);
        System.out.println("\n--- TESZT INDUL: " + currentTestName + " ---");
        List<String> commands = readTestInput();
        for (String command : commands) {
            executeTestLine(command, game);
        }
        writeTestOutput();
        checkTestResult();
        System.out.println("--- TESZT VÉGE: " + currentTestName + " ---\n");
    }

    /**
     * Megnyitja az adott nevű teszthez tartozó fájlt és beolvassa a parancsokat
     * 
     * @return a fájlban lévő parancsok listája
     */
    private static List<String> readTestInput() {
        List<String> output = new ArrayList<>();
        try (Scanner inputScanner = new Scanner(new File("tests/input/" + currentTestName + ".in"))) {
            while (inputScanner.hasNextLine()) {
                output.add(inputScanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Kiírja a futtatott teszt logjait
     */
    private static void writeTestOutput() {
        try (FileWriter outputWriter = new FileWriter(new File("tests/output/" + currentTestName + ".out"))) {
            for (String message : logMessages) {
                outputWriter.write(message + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ellenőrzi a futtatott teszt logjait az elvárt logokkal
     */
    private static void checkTestResult() {
        try (Scanner expectedScanner = new Scanner(new File("tests/expected/" + currentTestName + ".expected"))) {
            List<String> expectedLines = new ArrayList<>();
            while (expectedScanner.hasNextLine()) {
                expectedLines.add(expectedScanner.nextLine());
            }

            if (logMessages.size() != expectedLines.size()) {
                System.out.println("Test with test file " + currentTestName + ".out FAILED!");
                return;
            }
            for (int i = 0; i < logMessages.size(); i++) {
                if (!logMessages.get(i).equals(expectedLines.get(i))) {
                    System.out.println("Test with test file " + currentTestName + ".out FAILED!");
                    return;
                }
            }
            System.out.println("Test with test file " + currentTestName + ".out PASSED.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeTestLine(String line, GameModel game) {
        ArrayList<String> cmd = new ArrayList<>(Arrays.asList(line.split(" ")));
        switch (cmd.get(0)) {
            case "add" -> {
                switch (cmd.get(1)) {
                    case "tecton" -> {
                        switch (cmd.get(3)) {
                            case "standard" -> game.tectonArrayList.add(new Tecton(cmd.get(2)));
                            case "anticrossing" -> game.tectonArrayList.add(new AntiCrossingTecton(cmd.get(2)));
                            case "antifungus" -> game.tectonArrayList.add(new AntiFungusTecton(cmd.get(2)));
                            case "antimycelium" -> game.tectonArrayList.add(new AntiMyceliumTecton(cmd.get(2)));
                            case "preserver" -> game.tectonArrayList.add(new PreserverTecton(cmd.get(2)));
                            default -> {
                                break;
                            }
                        }
                    }
                    case "fungus" -> {
                        var mj = game.findMyceliumJunction(cmd.get(3));
                        game.fungusArrayList.add(new Fungus(cmd.get(2), mj));
                        mj.removeFungus();
                        mj.setFungus(game.findFungus(cmd.get(2)));
                    }
                    case "insect" -> game.insectArrayList.add(new Insect(cmd.get(2), game.findTecton(cmd.get(3))));
                    case "spore" -> {
                        game.findTecton(cmd.get(4)).putASpore(SporeFactory.createSpore(cmd.get(2), SporeTypes.valueOf(cmd.get(3))));
                        game.updateSporeList();
                    }
                    case "neighbour" -> {
                        var t1 = game.findTecton(cmd.get(2));
                        var t2 = game.findTecton(cmd.get(3));
                        t1.setNeighbour(t2);
                        t2.setNeighbour(t1);
                    }
                    case "myceliumjunction" -> game.junctionArrayList
                            .add(new MyceliumJunction(cmd.get(2), game.findTecton(cmd.get(3))));

                    case "myceliumconnection" -> game.connectionArrayList.add(new MyceliumConnection(cmd.get(2),
                            game.findMyceliumJunction(cmd.get(3)), game.findMyceliumJunction(cmd.get(4))));
                    default -> {
                        break;
                    }
                }
            }
            case "move" -> game.findInsect(cmd.get(1)).move(game.findTecton(cmd.get(2)));                             //KESZ
            case "grow" -> {
                switch (cmd.get(1)) {
                    case "mycelium" -> {
                        MyceliumJunction myceliumJunction = new MyceliumJunction(cmd.get(4), game.findTecton(cmd.get(5)));
                        game.junctionArrayList.add(myceliumJunction);
                        MyceliumConnection myceliumConnection = new MyceliumConnection(cmd.get(2), game.findMyceliumJunction(cmd.get(3)), game.findMyceliumJunction(cmd.get(4)));
                        game.connectionArrayList.add(myceliumConnection);
                    }
                    case "fungus" -> {
                        switch (cmd.get(3)) {
                            case "spore" -> {
                                Fungus f = game.findMyceliumJunction(cmd.get(2)).createFungus(cmd.get(4));
                                if(f == null) break;
                                else {
                                    game.fungusArrayList.add(f);
                                    game.updateSporeList();
                                }
                            }
                            case "insect" -> {
                                var list = game.findMyceliumJunction(cmd.get(2)).getPosition().getInsects();
                                if(list == null || list.isEmpty()) break;
                                else {
                                    //list.get(0).setStunned();
                                    Fungus f = game.findMyceliumJunction(cmd.get(2)).tryConsumeInsect(cmd.get(4));
                                    game.fungusArrayList.add(f);
                                    game.updateFungusList();
                                }
                            }
                        }
                    }
                    default -> {
                        break;
                    }
                }
            }
            case "spreadspore" -> {
                game.findFungus(cmd.get(1)).spreadSpores(game.findTecton(cmd.get(2)), SporeTypes.valueOf(cmd.get(3)), cmd.get(4));
                game.updateSporeList();
            }                                                                               //KESZ
            case "eatspore" -> {
                switch (cmd.get(1)) {
                    case "tecton" -> {
                        Insect i = game.findInsect(cmd.get(2));
                        Spore s = i.getPosition().getASpore();
                        if(s == null) {
                            String log = "No spores at " + i.getId() + " position (" + i.getPosition().getId() + ")! " + i.getId() + " can't eat.";
                            System.out.println(log);
                            TestFramework.logOutput(log);
                            break;
                        }
                        else {
                            i.eatSpore(s);
                            game.updateSporeList();
                            game.updateInsectList();
                        }
                    }
                    case "given" -> {
                        Insect i = game.findInsect(cmd.get(2));
                        Spore s = game.findSpore(cmd.get(3));
                        if(s == null) break;
                        else {
                            i.eatSpore(s);
                            game.updateSporeList();
                            game.updateInsectList();
                        }
                    }
                }
            }
            case "cut" -> game.findInsect(cmd.get(1)).cutMyceliumConnection(game.findMyceliumConnection(cmd.get(2))); //KESZ
            case "pass" -> game.executeAllgameStep();
            case "leave" -> interactiveMode = false;
            default -> {
                break;
            }
        }
    }
}
