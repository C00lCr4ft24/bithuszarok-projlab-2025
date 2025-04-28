package fungorium;

public class TestFramework {
    private static String currentLog = new String();

    public static String getCurrentLog() {
        return currentLog;
    }
    
    public static void logOutput(String log) {
        currentLog.concat(log);
    }

    public static void clearLog() {
        currentLog = new String();
    }
}
