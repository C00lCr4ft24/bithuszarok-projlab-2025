package fungorium;

import fungorium.mycelium.Fungus;
import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.SporeTypes;
import fungorium.tecton.*;

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

    /**
     * Privát konstruktor a példányosítás elkerülése érdekében
     */
    private TestFramework() {
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
        List<String> commands = readTestInput();
        for (String command : commands) {
            executeTestLine(command, game);
        }
        writeTestOutput();
        checkTestResult();
    }

    /**
     * Megnyitja az adott nevű teszthez tartozó fájlt és beolvassa a parancsokat
     * 
     * @return a fájlban lévő parancsok listája
     */
    private static List<String> readTestInput() {
        List<String> output = new ArrayList<>();
        try (Scanner inputScanner = new Scanner(new File("tests/" + currentTestName + ".in"))) {
            while (inputScanner.hasNextLine()) {
                output.add(inputScanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    private static void writeTestOutput() {
        try (FileWriter outputWriter = new FileWriter(new File("tests/" + currentTestName + ".out"))) {
            for (String message : logMessages) {
                outputWriter.write(message + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkTestResult() {
        try (Scanner expectedScanner = new Scanner(new File("tests/" + currentTestName + ".expected"))) {
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
                    case "fungus" ->
                        game.fungusArrayList.add(new Fungus(cmd.get(2), game.findMyceliumJunction(cmd.get(3))));
                    case "insect" -> game.insectArrayList.add(new Insect(cmd.get(2), game.findTecton(cmd.get(3))));
                    case "neighbor" -> {
                        var t1 = game.findTecton(cmd.get(2));
                        var t2 = game.findTecton(cmd.get(3));
                        t1.setNeighbour(t2);
                        t2.setNeighbour(t1);
                    }
                    case "myceliumjunction" -> game.myceliumJunctionArrayList
                            .add(new MyceliumJunction(cmd.get(2), game.findTecton(cmd.get(3))));

                    case "myceliumconnection" -> game.myceliumConnectionArrayList.add(new MyceliumConnection(cmd.get(2),
                            game.findMyceliumJunction(cmd.get(3)), game.findMyceliumJunction(cmd.get(4))));
                    default -> {
                        break;
                    }
                }
            }
            case "move" -> game.findInsect(cmd.get(1)).move(game.findTecton(cmd.get(2)));
            case "grow" -> {
                switch (cmd.get(1)) {
                    case "mycelium" -> {
                        // TODO
                    }
                    case "fungus" -> {
                        // TODO
                    }
                    default -> {
                        break;
                    }
                }
            }
            case "spreadspore" -> {
                // TODO
            }
            case "cut" -> game.findInsect(cmd.get(1)).cutMyceliumConnection(game.findMyceliumConnection(cmd.get(2)));
            default -> {
                break;
            }
        }
    }
}
