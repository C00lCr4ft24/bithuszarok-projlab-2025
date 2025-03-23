package fungorium;

import fungorium.tecton.*;
import fungorium.mycelium.*;
import fungorium.spore.*;

public class Skeleton{

    public void EmptyTectonBreaking(){
        Tecton t1 = new Tecton(); // 1
        Tecton t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        Tecton t3 = new Tecton(); // 5
        t2.setNeighbour(t3); // 6
        t3.setNeighbour(t2); // 7
        t3.setNeighbour(t1); // 8
        t1.setNeighbour(t3); // 9
        Tecton tkozep = new Tecton(); // 10
        t1.setNeighbour(tkozep); // 11
        t2.setNeighbour(tkozep); // 12
        t3.setNeighbour(tkozep); // 13
        tkozep.setNeighbour(t1); // 14
        tkozep.setNeighbour(t2); // 15
        tkozep.setNeighbour(t3); // 16
    }

    public void BrokenTectonBreakingAgain(){
        Tecton t1 = new Tecton(); // 1
        Tecton t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        Tecton t3 = new Tecton(); // 5
        t2.setNeighbour(t3); // 6
        t3.setNeighbour(t2); // 7
        t3.setNeighbour(t1); // 8
        t1.setNeighbour(t3); // 9
        Tecton tkozep = new Tecton(); // 10
        t1.setNeighbour(tkozep); // 11
        t2.setNeighbour(tkozep); // 12
        t3.setNeighbour(tkozep); // 13
        tkozep.setNeighbour(t1); // 14
        tkozep.setNeighbour(t2); // 15
        tkozep.setNeighbour(t3); // 16
        tkozep.split(); // 17
        Tecton tkozep2 = new Tecton(); // 18
        tkozep.setNeighbour(tkozep2); // 19
        t1.setNeighbour(tkozep2); // 20
        t2.setNeighbour(tkozep2); // 21
        tkozep2.setNeighbour(tkozep); // 22
        tkozep2.setNeighbour(t1); // 23
        tkozep2.setNeighbour(t2); //24
    }

    public void FullTectonBreak(){
        Tecton t1 = new Tecton(); // 1
        Tecton t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t2); // 4
        Tecton tkozep = new Tecton(); // 5
        t1.setNeighbour(tkozep); // 6
        t2.setNeighbour(tkozep); // 7
        tkozep.setNeighbour(t1); // 8
        tkozep.setNeighbour(t2); // 9
        Insect i1 = new Insect(); // 10
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 11 & 12
        MyceliumJunction m2 = tkozep.createMyceliumJunction(); // 13 & 14
        MyceliumJunction m3 = t2.createMyceliumJunction(); // 15 & 16
        MyceliumConnection c1 = new MyceliumConnection();
        m1.addConnection(c1);
        m2.addConnection(c1);
        MyceliumConnection c2 = new MyceliumConnection();
        m2.addConnection(c2);
        m3.addConnection(c2);
        Fungus f1 =m2.createFungus();
    }
    public void SporeSpreading(){
        Tecton t1 = new Tecton(); // 1
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 2 & 3
        Fungus f1 = m1.createFungus(); // 4
        Tecton t2 = new Tecton(); // 5
        t1.setNeighbour(t2); // 6
        t2.setNeighbour(t1); // 7
        Tecton t3 = new Tecton(); // 8
        t1.setNeighbour(t3); // 9
        t2.setNeighbour(t3); // 10
        t3.setNeighbour(t2); // 11
        t3.setNeighbour(t1); // 12
        Tecton t4 = new Tecton(); // 13
        t1.setNeighbour(t4); // 14
        t3.setNeighbour(t4); // 15
        t4.setNeighbour(t1); // 16
        t4.setNeighbour(t3); // 17
        Tecton t5 = new Tecton(); // 18
        t2.setNeighbour(t5); // 19
        t5.setNeighbour(t2); // 20
        Tecton t6 = new Tecton(); // 21
        t3.setNeighbour(t6); // 22
        t5.setNeighbour(t6); // 23
        t6.setNeighbour(t3); // 24
        t6.setNeighbour(t5); // 25
        Tecton t7 = new Tecton(); // 26
        t4.setNeighbour(t7); // 27
        t6.setNeighbour(t7); // 28
        t7.setNeighbour(t4); // 29
        t7.setNeighbour(t6); // 30
    }

    public void FungusGrowing(){
        Tecton t1 = new Tecton(); // 1
        Tecton t2 = new Tecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        AntiCrossingTecton t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 10 & 11
        Fungus f1 = m1.createFungus(); // 12
        MyceliumJunction m2 = t2.createMyceliumJunction(); // 13 & 14
        MyceliumJunction m3 = t3.createMyceliumJunction(); // 15 & 16
        MyceliumConnection c1 = new MyceliumConnection(); // 17
        m1.addConnection(c1); // 18
        m2.addConnection(c1); // 19
        m3.addConnection(c1); // 20
        MyceliumConnection c2 = new MyceliumConnection(); // 21 !!! KÉT DARAB 19-ES VAN A DIAGRAMBAN !!!
        m2.addConnection(c2); // 22
        m3.addConnection(c2); // 23
        SpeedUpSpore s1= new SpeedUpSpore(0, 0);t2.putASpore(s1); // 24
        
    }

    public void MyceliumGrowing(){
        Tecton t1 = new Tecton(); // 1
        AntiCrossingTecton t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        AntiCrossingTecton t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        AntiMyceliumTecton t4 = new AntiMyceliumTecton(); // 10
        t1.setNeighbour(t4); // 11
        t3.setNeighbour(t4); // 12
        t4.setNeighbour(t1); // 13
        t4.setNeighbour(t3); // 14
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 15 & 16
        Fungus f1 = m1.createFungus(); // 17
        MyceliumJunction m2 = t2.createMyceliumJunction(); // 18 & 19
        MyceliumConnection c1 = new MyceliumConnection(); // 20
        m1.addConnection(c1);
        m2.addConnection(c1);
    }
    public void MyceliumConnectionCutting1(){
        Tecton t1 = new Tecton(); // 1
        AntiCrossingTecton t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        AntiCrossingTecton t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        AntiMyceliumTecton t4 = new AntiMyceliumTecton(); // 10
        t3.setNeighbour(t4); // 11
        t4.setNeighbour(t3); // 12
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 13 & 14
        Fungus f1 = m1.createFungus(); // 15
        MyceliumJunction m2 = t2.createMyceliumJunction(); // 16 & 17
        MyceliumJunction m3 = t3.createMyceliumJunction(); // 18 & 19
        MyceliumJunction m4 = t4.createMyceliumJunction(); // 20 & 21
        MyceliumConnection c1 = new MyceliumConnection(); // 22
        m1.addConnection(c1); // 23
        m2.addConnection(c1); // 24
        MyceliumConnection c2 = new MyceliumConnection(); // 25
        m2.addConnection(c2); // 26
        m3.addConnection(c2); // 27
        MyceliumConnection c3 = new MyceliumConnection(); // 28
        m3.addConnection(c3); // 29
        m4.addConnection(c3); // 30
    }

    public void MyceliumConnectionCutting2(){
        Tecton t1 = new Tecton(); // 1
        AntiCrossingTecton t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        AntiCrossingTecton t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        AntiMyceliumTecton t4 = new AntiMyceliumTecton(); // 10
        t3.setNeighbour(t4); // 11
        t4.setNeighbour(t3); // 12
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 13 & 14
        Fungus f1 = m1.createFungus(); // 15
        MyceliumJunction m2 = t2.createMyceliumJunction(); // 16 & 17
        MyceliumJunction m3 = t3.createMyceliumJunction(); // 18 & 19
        MyceliumJunction m4 = t4.createMyceliumJunction(); // 20 & 21
        MyceliumConnection c1 = new MyceliumConnection(); // 22
        m1.addConnection(c1); // 23
        m2.addConnection(c1); // 24
        MyceliumConnection c2 = new MyceliumConnection(); // 25
        m2.addConnection(c2); // 26
        m3.addConnection(c2); // 27
        MyceliumConnection c3 = new MyceliumConnection(); // 28
        m3.addConnection(c3); // 29
        m4.addConnection(c3); // 30
        Fungus f2 = m4.createFungus(); // 31 !!! Az m4 csinálja a Fungust nem a t4, ez rossz a diagramon !!!
        
    }
    public void InsectFunctions(){
        Tecton t1 = new Tecton(); // 1
        AntiCrossingTecton t2 = new AntiCrossingTecton(); // 2
        t1.setNeighbour(t2); // 3
        t2.setNeighbour(t1); // 4
        AntiCrossingTecton t3 = new AntiCrossingTecton(); // 5
        t1.setNeighbour(t3); // 6
        t2.setNeighbour(t3); // 7
        t3.setNeighbour(t1); // 8
        t3.setNeighbour(t2); // 9
        AntiMyceliumTecton t4 = new AntiMyceliumTecton(); // 10
        t3.setNeighbour(t4); // 11
        t4.setNeighbour(t3); // 12
        MyceliumJunction m1 = t1.createMyceliumJunction(); // 13 & 14
        Fungus f1 = m1.createFungus(); // 15
        MyceliumJunction m2 = t2.createMyceliumJunction(); // 16 & 17
        MyceliumJunction m3 = t3.createMyceliumJunction(); // 18 & 19
        MyceliumJunction m4 = t4.createMyceliumJunction(); // 20 & 21
        MyceliumConnection c1 = new MyceliumConnection(); // 22
        m1.addConnection(c1); // 23
        m2.addConnection(c1); // 24
        MyceliumConnection c2 = new MyceliumConnection(); // 25
        m2.addConnection(c2); // 26
        m3.addConnection(c2); // 27
        MyceliumConnection c3 = new MyceliumConnection(); // 28
        m3.addConnection(c3); // 29
        m4.addConnection(c3); // 30
        Fungus f2 = m4.createFungus(); // 31 !!! Az m4 csinálja a Fungust nem a t4, ez rossz a diagramon !!!
        AntiCutSpore s1 = new AntiCutSpore(0, 0); t1.putASpore(s1); // 32 !!! putspore hiányzik a diagramon !!!
        
        
    }

}
