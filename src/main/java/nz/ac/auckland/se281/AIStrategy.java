package nz.ac.auckland.se281;
import java.util.List;
import java.util.Random;

public abstract class AIStrategy {

    private List<Integer> historyOfFingers; 

    public static Random random = new Random();

    public int numOfFingers() {
        return(Utils.getRandomNumber(1, 5));
    }
    
    public abstract int sumOfFingers(int numOfFingers);

    public String outcome(String playerSum, int jarvisSum, String playerFingers, int jarvisFingers) {
        Integer playerFingersInt = Integer.valueOf(playerFingers);
        Integer playerSumInt = Integer.valueOf(playerSum);

        Integer actualSum = playerFingersInt + jarvisFingers;
        
        if (playerSumInt == actualSum) {
            return "HUMAN_WINS";
        } else if (jarvisSum == actualSum) {
            return "AI_WINS";
        } else {
            return "DRAW";
        }

    }
    
}
