package nz.ac.auckland.se281;

public class AI {

    /*
     * This method is used to get the sum of fingers of AI.
     */
  public int getAIFingersSum(
      Morra game, AIStrategy strategy, int numOfPlayersFingers, int numOfAIFingers) {
    if (strategy instanceof RandomStrategy) {
      return strategy.sumOfFingers(numOfAIFingers, numOfAIFingers);
    } else if (strategy instanceof AverageStrategy) {
      return strategy.sumOfFingers(numOfAIFingers, strategy.getAverage(game.getHistoryOfFingers()));
    } else {
      return strategy.sumOfFingers(numOfAIFingers, strategy.getMode(game.getHistoryOfFingers()));
    }
  }
}
