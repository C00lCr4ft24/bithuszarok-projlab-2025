package fungorium;
import fungorium.mycelium.MyceliumConnection;
import fungorium.spore.Spore;
import fungorium.tecton.Tecton;

public class Insect implements FungoriumEntity {

    private enum Speed { SLOW, MEDIUM, FAST }

    private int     eatenNutrient;
    private Speed   speed;
    private boolean canCutMycelium;
    private boolean isStunned;

    private void resetEffectValues() {  }

    public Insect() {
        eatenNutrient = 0;
        speed = Speed.MEDIUM;
        canCutMycelium = true;
        isStunned = false;
    }

    public void cutMyceliumConnection(MyceliumConnection mc) { printAction("cutMyceliumConnection"); }
    public void eatSpore(Spore spore)                        { printAction("eatSpore");              }
    public void move(Tecton target)                          { printAction("move");                  }
    public void setStunned()                                 { printAction("setStunned");            }
    public void blockMyceliumCut()                           { printAction("blockMyceliumCut");      }
    public void increaseSpeed()                              { printAction("increaseSpeed");         }
    public void decreaseSpeed()                              { printAction("decreaseSpeed");         }

    @Override
    public void gameStep() { System.out.println("gameStep");  }

    private void printAction(String methodName) { System.out.println(methodName + " called on " + this); }
}
