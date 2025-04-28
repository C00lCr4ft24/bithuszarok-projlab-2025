package fungorium;

public class Main {
    public static void main(String[] args) {
        // ideiglenesen kapcsoltam csak vissza
        //var skeleton = new Skeleton();
        //skeleton.start();

        var game = new GameModel();

        var test1 = TestFramework.readTestInput(1);
        /*
        if(test1 != null) {
            for (String str : test1) {
                System.out.println(str);
            }
        }
        */
        if(test1 != null) {
            for (String str : test1) {
                TestFramework.executeTestLine(str, game);
            }
        }
    }
}