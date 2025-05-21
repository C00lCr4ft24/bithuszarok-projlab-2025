package fungorium.model;

public interface FungoriumEntity {
    /**
     * With this function, the game elements will sense the passage of time and perform the necessary actions.
     */
    void gameStep();

    default void printAction(String methodName) {
        //System.out.println(methodName + " called on " + this);
    }

}
