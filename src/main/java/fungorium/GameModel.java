package fungorium;

import fungorium.model.Insect;
import fungorium.model.events.EventType;
import fungorium.model.events.GameEvent;
import fungorium.model.events.GameEventController;
import fungorium.model.events.GameEventListener;
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

public class GameModel implements GameEventListener {

    public ArrayList<Tecton>                         tectonArrayList = new ArrayList<>();
    public ArrayList<Spore>                           sporeArrayList = new ArrayList<>();
    public ArrayList<Fungus>                         fungusArrayList = new ArrayList<>();
    public ArrayList<MyceliumConnection> myceliumConnectionArrayList = new ArrayList<>();
    public ArrayList<MyceliumJunction>     myceliumJunctionArrayList = new ArrayList<>();
    public ArrayList<Insect>                         insectArrayList = new ArrayList<>();
    public ArrayList<Player>                         playerArrayList = new ArrayList<>();

    private int roundN = 0;
    private int currentPlayerIndex;
    private boolean isGameOver = false;

    private final Random random = new Random();

    public GameModel() {
        GameEventController.addEventListener(this);
    }

    public void resetGameModel(int fungusPlayers, int insectPlayers) {
        tectonArrayList.clear();
        sporeArrayList.clear();
        fungusArrayList.clear();
        myceliumConnectionArrayList.clear();
        myceliumJunctionArrayList.clear();
        insectArrayList.clear();
        playerArrayList.clear();

        roundN = 0;
        isGameOver = false;

        PlayerFactory.resetCounters();

        for(int i = fungusPlayers; i > 0; i--) { playerArrayList.add(PlayerFactory.createPlayer(PlayerTypes.GOMBASZ )); }
        for(int i = insectPlayers; i > 0; i--) { playerArrayList.add(PlayerFactory.createPlayer(PlayerTypes.ROVARASZ)); }

        startGame();
    }

    private void incrementCurrentPlayerIndex() {
        currentPlayerIndex++;
        currentPlayerIndex %= playerArrayList.size();
    }

    private void createMap() {

        List<List<Tecton>> tectonsByLevel = new ArrayList<>();

        int levels = random.nextInt(5) + 5;
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

    private void executeRound() {
        for (Player player : playerArrayList) {
            currentPlayerIndex = playerArrayList.indexOf(player);
            switch (player.getType()) {
                case GOMBASZ -> {
                    GameEventController.dispatchEvent(new GameEvent(this, EventType.FUNGUS_PLAYERS_TURN, null));
                }
                case ROVARASZ -> {
                    GameEventController.dispatchEvent(new GameEvent(this, EventType.INSECT_PLAYERS_TURN, null));
                    System.out.println(player);
                }
            }
        }
    }

    private void executeAPlayerRound() {
        Player currentPlayer = playerArrayList.get(currentPlayerIndex);
        switch (currentPlayer.getType()) {
            case GOMBASZ -> {
                GameEventController.dispatchEvent(new GameEvent(this, EventType.FUNGUS_PLAYERS_TURN, currentPlayer.toString()));
            }
            case ROVARASZ -> {
                GameEventController.dispatchEvent(new GameEvent(this, EventType.INSECT_PLAYERS_TURN, currentPlayer.toString()));
            }
        }
    }
    private void initBeforeStart() {
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

    private void addStarterJunctionAndFungus(Player player) {
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

    private void addStarterInsect(Player player) {
        if(player.getType() != PlayerTypes.ROVARASZ) { return; }

        Tecton tecton = tectonArrayList.get(random.nextInt(tectonArrayList.size() - 1));
        tecton.putInsect(new Insect("I0", player, tecton));
        updateInsectList();
    }

    public void startGame() {
        initBeforeStart();
        executeAPlayerRound();
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
        for (MyceliumJunction mj : myceliumJunctionArrayList) {
            if(Objects.equals(mj.getId(), id)) {
                return mj;
            }
        }
        throw new IllegalArgumentException("No MyceliumJunction with id " + id + " exists");
    }

    public MyceliumConnection findMyceliumConnection(String id) {
        for (MyceliumConnection mc : myceliumConnectionArrayList) {
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

    public void updateSporeList() {
        sporeArrayList.clear();
        for(Tecton tecton : tectonArrayList ) {
            sporeArrayList.addAll(tecton.getAllSpores());
        }
    }

    public void updateJunctionList() {
        myceliumJunctionArrayList.clear();
        for(Tecton tecton : tectonArrayList ) {
            myceliumJunctionArrayList.addAll(tecton.getMyceliumJunctions());
        }
    }

    public void updateFungusList() {
        fungusArrayList.clear();
        for(MyceliumJunction mj : myceliumJunctionArrayList ) {
            fungusArrayList.add(mj.getFungus());
        }
    }

    public void updateInsectList() {
        insectArrayList.clear();
        for(Tecton tecton : tectonArrayList ) {
            insectArrayList.addAll(tecton.getInsects());
        }
    }

    public void executeAllgameStep() {
        for(Fungus fungus : fungusArrayList) {
            fungus.gameStep();
        }
        for(MyceliumConnection myceliumConnection : myceliumConnectionArrayList) {
            myceliumConnection.gameStep();
        }
        for(MyceliumJunction mycjunction : myceliumJunctionArrayList) {
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

    @Override
    public void onEvent(GameEvent event) {
        if(event.getEventType().equals(EventType.NEW_GAME_STARTED)) {
            ArrayList<Integer> players = (ArrayList<Integer>) event.getEventData();
            resetGameModel(players.get(0), players.get(1));
        }
        if(event.getEventType().equals(EventType.NEXT_PLAYER_IN_ROUND)) {
            incrementCurrentPlayerIndex();
            executeAPlayerRound();
        }
    }
}
