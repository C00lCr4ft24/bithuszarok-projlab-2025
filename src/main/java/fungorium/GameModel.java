package fungorium;

import fungorium.model.Insect;
import fungorium.model.mycelium.Fungus;
import fungorium.model.mycelium.MyceliumConnection;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.player.Player;
import fungorium.model.player.PlayerFactory;
import fungorium.model.player.PlayerTypes;
import fungorium.model.spore.Spore;
import fungorium.model.tecton.Tecton;
import fungorium.model.tecton.TectonFactory;

import java.util.*;

public class GameModel {

    public static final ArrayList<Tecton>                         tectonArrayList = new ArrayList<>();
    public static final ArrayList<Spore>                           sporeArrayList = new ArrayList<>();
    public static final ArrayList<Fungus>                         fungusArrayList = new ArrayList<>();
    public static final ArrayList<MyceliumConnection>         connectionArrayList = new ArrayList<>();
    public static final ArrayList<MyceliumJunction>             junctionArrayList = new ArrayList<>();
    public static final ArrayList<Insect>                         insectArrayList = new ArrayList<>();
    public static final ArrayList<Player>                        playerArrayList = new ArrayList<>();


    public static Tecton selectedTecton;
    public Tecton getSelectedTecton() { return selectedTecton; }
    public void setSelectedTecton(Tecton tecton) { selectedTecton = tecton; }

    public static Spore selectedSpore;
    public static Fungus selectedFungus;
    public static MyceliumConnection selectedMyceliumConnection;
    public static MyceliumJunction selectedMyceliumJunction;
    public static Insect selectedInsect;

    public static int roundN = 0;
    public static int currentPlayerIndex = 0;
    public static boolean isGameOver = false;

    public static final Random random = new Random();

    public static void resetGameModel(int fungusPlayers, int insectPlayers) {
        tectonArrayList.clear();
        sporeArrayList.clear();
        fungusArrayList.clear();
        connectionArrayList.clear();
        junctionArrayList.clear();
        insectArrayList.clear();
        playerArrayList.clear();

        roundN = 0;
        currentPlayerIndex = 0;
        isGameOver = false;

        PlayerFactory.resetCounters();

        for(int i = fungusPlayers; i > 0; i--) { playerArrayList.add(PlayerFactory.createPlayer(PlayerTypes.GOMBASZ )); }
        for(int i = insectPlayers; i > 0; i--) { playerArrayList.add(PlayerFactory.createPlayer(PlayerTypes.ROVARASZ)); }

        startGame();
    }

    public static void incrementCurrentPlayerIndex() {
        currentPlayerIndex++;
        if(currentPlayerIndex >= playerArrayList.size()) { executeAllgameStep(); }
        currentPlayerIndex %= playerArrayList.size();
    }

    public static void createMap() {

        List<List<Tecton>> tectonsByLevel = new ArrayList<>();

        int levels = random.nextInt(3) + 9; // 5-7 levels
        for(int i = 0; i < levels; i++) {
            List<Tecton> tectonsOnThisLevel = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                Tecton tecton = TectonFactory.createTecton("[T" + i + "-" + j + "]");
                tectonArrayList.add(tecton);
                tectonsOnThisLevel.add(tecton);
            }
            tectonsByLevel.add(tectonsOnThisLevel);
        }

        //Szomszedsagok beallitasa
        for (int i = 1; i < levels; i++) {
            List<Tecton> currLevel = tectonsByLevel.get(i);
            List<Tecton> prevLevel = tectonsByLevel.get(i - 1);
            for (int j = 0; j < currLevel.size(); j++) {
                Tecton curr = currLevel.get(j);
                if (j - 1 >= 0) {
                    Tecton aboveLeft = prevLevel.get(j - 1);
                    curr     .setNeighbour(aboveLeft);
                    aboveLeft.setNeighbour(curr);
                    System.out.println(curr + "-" + aboveLeft);
                }
                if (j < prevLevel.size()) {
                    Tecton aboveRight = prevLevel.get(j);
                    curr      .setNeighbour(aboveRight);
                    aboveRight.setNeighbour(curr);
                    System.out.println(curr + "-" + aboveRight);
                }
                if (j - 1 >= 0) {
                    Tecton leftNeighbor = currLevel.get(j - 1);
                    curr.setNeighbour(leftNeighbor);
                    System.out.println(curr + "-" + leftNeighbor);
                }
            }
        }
    }

    public static void executeAPlayerRound() {
        incrementCurrentPlayerIndex();
    }
    public static void initBeforeStart() {
        createMap();                                //Tectonok generalasa

        for(Player player : playerArrayList) {      //KezdoFungusok generalasa
            switch (player.getType()) {
                case GOMBASZ -> { addStarterJunctionAndFungus(player); }
                case ROVARASZ -> { addStarterInsect(player); }
            }
        }
        updateJunctionList();
        updateFungusList();
    }

    public static void addStarterJunctionAndFungus(Player player) {
        if(player.getType() != PlayerTypes.GOMBASZ) { return; }

        Tecton tecton = tectonArrayList.get(random.nextInt(tectonArrayList.size() - 1));
        if(tecton.isFungusSpaceEmpty()) {
            var junction = tecton.createMyceliumJunction();
            var fungus = new Fungus("F0", player, junction);
            junction.setFungus(fungus);
            fungusArrayList.add(fungus);
        }
        else addStarterJunctionAndFungus(player);
    }

    public static void addStarterInsect(Player player) {
        if(player.getType() != PlayerTypes.ROVARASZ) { return; }

        Tecton tecton = tectonArrayList.get(random.nextInt(tectonArrayList.size() - 1));
        tecton.putInsect(new Insect("I0", player, tecton));
        updateInsectList();
    }

    public static void startGame() {
        initBeforeStart();
        executeAPlayerRound();
    }

    public Player getCurrentPlayer() {
        return playerArrayList.get(currentPlayerIndex);
    }

    public ArrayList<Tecton> getAllTectonsForCurrentPlayer() {
        Player currentPlayer = getCurrentPlayer();

        var list = new ArrayList<Tecton>();

        if(currentPlayer.getType().equals(PlayerTypes.ROVARASZ)) {
            for(Insect insect : insectArrayList) {
                if(insect.getPlayer().equals(currentPlayer)) {
                    list.add(insect.getPosition());
                }
            }
        }
        if(currentPlayer.getType().equals(PlayerTypes.GOMBASZ )) {
            for(MyceliumJunction junction : junctionArrayList) {
                if(junction.getFungus().getPlayer().equals(currentPlayer)) {
                    list.add(junction.getPosition());
                }
            }
        }
        return list;
    }

    public Tecton findTecton(String id) {
        for (Tecton tecton : tectonArrayList) {
            if(Objects.equals(tecton.getId(), id)) {
                return tecton;
            }
        }
        throw new IllegalArgumentException("No tecton with id " + id + " exists");
    }

    public MyceliumJunction findMyceliumJunction(String id) {
        for (MyceliumJunction mj : junctionArrayList) {
            if(Objects.equals(mj.getId(), id)) {
                return mj;
            }
        }
        throw new IllegalArgumentException("No MyceliumJunction with id " + id + " exists");
    }

    public MyceliumConnection findMyceliumConnection(String id) {
        for (MyceliumConnection mc : connectionArrayList) {
            if(Objects.equals(mc.getId(), id)) {
                return mc;
            }
        }
        throw new IllegalArgumentException("No MyceliumConnection with id " + id + " exists");
    }

    public Insect findInsect(String id) {
        for (Insect insect : insectArrayList) {
            if(Objects.equals(insect.getId(), id)) {
                return insect;
            }
        }
        throw new IllegalArgumentException("No Insect with id " + id + " exists");
    }

    public Fungus findFungus(String id) {
        for(Fungus fungus : fungusArrayList) {
            if(Objects.equals(fungus.getId(), id)) {
                return fungus;
            }
        }
        throw new IllegalArgumentException("No Fungus with id " + id + " exists");
    }

    public Spore findSpore(String id) {
        for(Spore spore : sporeArrayList) {
            if(Objects.equals(spore.getId(), id)) {
                return spore;
            }
        }
        throw new IllegalArgumentException("No Spore with id " + id + " exists");
    }

    public static void updateSporeList() {
        sporeArrayList.clear();
        for(Tecton tecton : tectonArrayList ) {
            sporeArrayList.addAll(tecton.getAllSpores());
        }
    }

    public static void updateJunctionList() {
        junctionArrayList.clear();
        for(Tecton tecton : tectonArrayList ) {
            junctionArrayList.addAll(tecton.getMyceliumJunctions());
        }
    }

    public static void updateFungusList() {
        fungusArrayList.clear();
        for(MyceliumJunction mj : junctionArrayList) {
            fungusArrayList.add(mj.getFungus());
        }
    }

    public static void updateInsectList() {
        insectArrayList.clear();
        for(Tecton tecton : tectonArrayList ) {
            insectArrayList.addAll(tecton.getInsects());
        }
    }

    public static void executeAllgameStep() {
        for(Fungus fungus : fungusArrayList) {
            fungus.gameStep();
        }
        for(MyceliumConnection myceliumConnection : connectionArrayList) {
            myceliumConnection.gameStep();
        }
        for(MyceliumJunction mycjunction : junctionArrayList) {
            mycjunction.gameStep();
        }
        for(Insect insect : insectArrayList) {
            insect.gameStep();
        }
        for(Tecton tecton : tectonArrayList ) {
            tecton.gameStep();
        }
        updateSporeList();
        String log = "<--------------EACH OBJECT MOVED A GAME STEP-------------->";
        System.out.println(log);
        TestFramework.logOutput(log);
    }
}
