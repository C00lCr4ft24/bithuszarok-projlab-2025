package fungorium;

public class TestFramework {
    
    /**
     * Statikus logger string, az elvárt teszteredmény és az akutális teszteredmény logger alapú összehasonlításához
     */
    private static String currentLog = new String();

    /**
     * Visszaadja a futás során keletkezett log üzenetet.
     * @return A futás során feljegyzett események
     */
    public static String getCurrentLog() {
        return currentLog;
    }
    
    /**
     * Hozzáfűzi a loghoz az adott függvény eseményéhez tartozó stringet.
     * @param log az adott esemény/függvény üzenete
     */
    public static void logOutput(String log) {
        currentLog.concat(log);
    }

    /**
     * Üríti a futás során keletkezett logot, és újat kezd.
     */
    public static void clearLog() {
        currentLog = new String();
    }
}
