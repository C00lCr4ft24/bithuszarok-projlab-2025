package fungorium;

import fungorium.model.Insect;
import fungorium.model.mycelium.Fungus;
import fungorium.model.mycelium.MyceliumConnection;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.player.Player;
import fungorium.model.player.PlayerTypes;
import fungorium.model.spore.Spore;
import fungorium.model.tecton.Tecton;

import java.util.ArrayList;
import java.util.Objects;

public class GameModel {
    public static ArrayList<Tecton>                         tectonArrayList = new ArrayList<>();
    public static ArrayList<Spore>                           sporeArrayList = new ArrayList<>();
    public static ArrayList<Fungus>                         fungusArrayList = new ArrayList<>();
    public static ArrayList<MyceliumConnection> myceliumConnectionArrayList = new ArrayList<>();
    public static ArrayList<MyceliumJunction>     myceliumJunctionArrayList = new ArrayList<>();
    public static ArrayList<Insect>                         insectArrayList = new ArrayList<>();
    public static ArrayList<Player>                         playerArrayList = new ArrayList<>();

    public static void resetGameModel(int fungusPlayers, int insectPlayers) {
        tectonArrayList.clear();
        sporeArrayList.clear();
        fungusArrayList.clear();
        myceliumConnectionArrayList.clear();
        myceliumJunctionArrayList.clear();
        insectArrayList.clear();
        playerArrayList.clear();
        for(int i = fungusPlayers; i >= 0; i--) { playerArrayList.add(new Player(PlayerTypes.GOMBASZ )); }
        for(int i = insectPlayers; i >= 0; i--) { playerArrayList.add(new Player(PlayerTypes.ROVARASZ)); }
        System.out.println("GameModel was reset.");
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
}
