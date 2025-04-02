package fungorium;

public interface FungoriumEntity {
    /**
     * With this function, the game elements will sense the passage of time and perform the necessary actions.
     */
    public void gameStep();

    public default void printAction(String methodName) { System.out.println(methodName + " called on " + this); }

}
