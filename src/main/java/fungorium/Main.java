package fungorium;

public class Main {
    public static void main(String[] args) {
        // ideiglenesen kapcsoltam csak vissza
        //var skeleton = new Skeleton();
        //skeleton.start();

        var game = new GameModel();

        TestFramework.runTest("test_1", game);
        /*
        if(test1 != null) {
            for (String str : test1) {
                System.out.println(str);
            }
        }
        */
    }
}