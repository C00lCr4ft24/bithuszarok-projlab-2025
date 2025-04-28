package fungorium;

import fungorium.mycelium.Fungus;
import fungorium.mycelium.MyceliumConnection;
import fungorium.mycelium.MyceliumJunction;
import fungorium.spore.Spore;
import fungorium.tecton.Tecton;

import java.util.ArrayList;
import java.util.Objects;

public class GameModel {
    public ArrayList<Tecton>                         tectonArrayList = new ArrayList<>();
    public ArrayList<Spore>                           sporeArrayList = new ArrayList<>();
    public ArrayList<Fungus>                         fungusArrayList = new ArrayList<>();
    public ArrayList<MyceliumConnection> myceliumConnectionArrayList = new ArrayList<>();
    public ArrayList<MyceliumJunction>     myceliumJunctionArrayList = new ArrayList<>();
    public ArrayList<Insect>                         insectArrayList = new ArrayList<>();

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
}
