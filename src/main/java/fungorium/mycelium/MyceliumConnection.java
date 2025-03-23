package fungorium.mycelium;

import fungorium.FungoriumEntity;

public class MyceliumConnection implements FungoriumEntity {

    private int lifetime = -1;
    private MyceliumJunction junctionA;
    private MyceliumJunction junctionB;

    public MyceliumConnection() { System.out.println("New MyceliumConnection created: " + this); }

    public MyceliumConnection(MyceliumJunction from, MyceliumJunction to) {
        System.out.println("New MyceliumConnection created: " + this);
        junctionA = from;
        junctionB = to;
    }

    public void cutMe() {
        printAction("cutMe");
        junctionA.removeConnection(this);
        junctionB.removeConnection(this);
        junctionA = null;
        junctionB = null;
    }

    public void setLifetime(int lifetime) {
        if(lifetime < 0) {
            printAction("setLifeTime");
            this.lifetime = lifetime;
        }
    }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
