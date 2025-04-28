package fungorium;

import fungorium.mycelium.Fungus;
import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.SporeTypes;
import fungorium.tecton.*;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class TestFramework {
    private static final int NUM_OF_TESTS = 24;

    private static int currentTestIndex = 0;

    public static Queue<String> readTestInput(int idx) {
        if (idx > NUM_OF_TESTS || idx <= 0) { return null; } //guard
        currentTestIndex = idx;
        String fileName = "test_" + idx + ".in";
        String logFileName = "test_" + currentTestIndex + ".out";
        Queue<String> output = new ArrayDeque<>();
        try {
            //Elozo out fajl torlese
            FileOutputStream fos = new FileOutputStream(logFileName);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("");
            bw.close();

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                output.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void executeTestLine(String line, GameModel game) {
        ArrayList<String> cmd = new ArrayList<>(Arrays.asList(line.split(" ")));
        switch(cmd.get(0)) {
            case "add" -> {
                switch(cmd.get(1)) {
                    case "tecton" -> {
                        switch(cmd.get(3)) {
                            case "standard"     -> {
                                game.tectonArrayList.add(new             Tecton(cmd.get(2)));
                            }
                            case "anticrossing" -> {
                                game.tectonArrayList.add(new AntiCrossingTecton(cmd.get(2)));
                            }
                            case "antifungus"   -> {
                                game.tectonArrayList.add(new   AntiFungusTecton(cmd.get(2)));
                            }
                            case "antimycelium" -> {
                                game.tectonArrayList.add(new AntiMyceliumTecton(cmd.get(2)));
                            }
                            case "preserver"    -> {
                                game.tectonArrayList.add(new    PreserverTecton(cmd.get(2)));
                            }

                        }
                    }
                    case "fungus" -> {
                        game.fungusArrayList.add(new Fungus(cmd.get(2), game.findMyceliumJunction(cmd.get(3))));
                    }
                    case "insect" ->  {
                        game.insectArrayList.add(new Insect(cmd.get(2), game.findTecton(cmd.get(3))));
                    }
                    case "neighbor" -> {
                        var t1 = game.findTecton(cmd.get(2));
                        var t2 = game.findTecton(cmd.get(3));
                        t1.setNeighbour(t2);
                        t2.setNeighbour(t1);
                    }
                    case "myceliumjunction" -> {
                        game.myceliumJunctionArrayList.add(new MyceliumJunction(cmd.get(2), game.findTecton(cmd.get(3))));
                    }
                    case "myceliumconnection" -> {
                        game.myceliumConnectionArrayList.add(new MyceliumConnection(cmd.get(2), game.findMyceliumJunction(cmd.get(3)), game.findMyceliumJunction(cmd.get(4))));
                    }
                }
            }
            case "move" -> {
                game.findInsect(cmd.get(1)).move(game.findTecton(cmd.get(2)));
            }
            case "grow" -> {
                switch (cmd.get(1)) {
                    case "mycelium" -> {
                        //TODO
                    }
                    case "fungus" -> {
                        //TODO
                    }
                }
            }
            case "spreadspore" -> {
                //TODO
            }
            case "cut" -> {
                game.findInsect(cmd.get(1)).cutMyceliumConnection(game.findMyceliumConnection(cmd.get(2)));
            }
        }
    }

    public static void logOutput(String str) {
        String logFileName = "test_" + currentTestIndex + ".out";
        try {
            FileOutputStream fos = new FileOutputStream(logFileName, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(str + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
