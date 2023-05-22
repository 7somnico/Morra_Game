package nz.ac.auckland.se281;

import java.util.List;
import java.util.Random;

public abstract class AIStrategy {

  public static Random random = new Random();

  /*
   * This abstract method will give the sum fo fingers the AI will give, different strategies will have different implementations.
   * @param numOfFingers1: the number of fingers user 1 will play (the player)
   * @param numOfFingers2: the number of fingers user 2 will play (the AI)
   */
  public abstract int sumOfFingers(int numOfFingers1, int numOfFingers2);

  /*
   * @return the number of fingers the AI will play, using the random number generator given from the Utils.java file
   */
  public int numOfAIsFingers() {
    return (Utils.getRandomNumber(1, 5));
  }

  /*
   * This method is mainly used for the average strategy.
   * @param historyOfFingers: a list of the number of fingers the player has played
   * @return the average of the historyOfFingers
   */
  public int getAverage(List<Integer> historyOfFingers) {
    int average = 0;
    double numOfHistory = historyOfFingers.size() - 1;
    for (int i = 0; i < numOfHistory; i++) {
      average += historyOfFingers.get(i);
    }
    average = (int) Math.round(average / numOfHistory);
    System.out.println(average);
    return average;
  }

  /*
   * @param aiSum: the sum of fingers of AI
   * @param playerSum: the sum of fingers of player
   * @param aiFingers: the number of fingers of AI
   * @param playerFingers: the number of fingers of player
   * @return the outcome of the game
   */
  public String outcome(int aiSum, int playerSum, int aiFingers, int playerFingers) {
    Integer actualSum = aiFingers + playerFingers;

    if (playerSum == actualSum && aiSum != actualSum) {
      return "HUMAN_WINS";
    } else if (aiSum == actualSum && playerSum != actualSum) {
      return "AI_WINS";
    } else {
      return "DRAW";
    }
  }

  /*
   * This method is mainly used for the top strategy, wehre you find the most common number the player has given as their fingers.
   * @param historyOfFingers: a list of the number of fingers the player has played
   * @return the mode of the historyOfFingers
   */
  public int getMode(List<Integer> historyOfFingers) {

    // finding the most common integer in the list
    int mode = 0;
    for (int i = 0; i < historyOfFingers.size() - 1; i++) {
      int count = 0;
      for (int j = 0; j < historyOfFingers.size() - 1; j++) {
        if (historyOfFingers.get(i) == historyOfFingers.get(j)) {
          count++;
        }
      }
      if (count > mode) {
        mode = historyOfFingers.get(i);
      }
    }

    return mode;
  }
}
