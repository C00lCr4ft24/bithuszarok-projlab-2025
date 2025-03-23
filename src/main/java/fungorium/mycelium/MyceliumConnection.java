package fungorium.mycelium;

import fungorium.FungoriumEntity;

public class MyceliumConnection implements FungoriumEntity {

    private int lifeTime;
    private MyceliumJunction fromJunction;
    private MyceliumJunction toJunction;

    public MyceliumConnection() { System.out.println("New MyceliumConnection created: " + this); }

    public MyceliumConnection(MyceliumJunction from, MyceliumJunction to) {
        System.out.println("New MyceliumConnection created: " + this);
        fromJunction = from;
        toJunction = to;
    }

    public void cutMe() {
        printAction("cutMe");
        fromJunction = null;
        toJunction = null;
    }

    public void setLifeTime(int lifeTime) {
        printAction("setLifeTime");
        this.lifeTime = lifeTime;
    }

    @Override
    public void gameStep() { printAction("gameStep"); }
}
