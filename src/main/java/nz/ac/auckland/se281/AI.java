package nz.ac.auckland.se281;

public class AI {

  /*
   * This method is used to get the sum of fingers of AI.
   * @param game: the game object
   * @param strategy: the strategy of AI
   * @param numOfPlayersFingers: the number of fingers the player has played
   * @param numOfAiFingers: the number of fingers the AI has played
   * @return the sum of the number of fingers the AI will play, each strategy will have
   * different way to calculate the sum of fingers
   */
  public int getAiFingersSum(
      Morra game, AiStrategy strategy, int numOfPlayersFingers, int numOfAiFingers) {
    if (strategy instanceof RandomStrategy) {
      return strategy.sumOfFingers(numOfAiFingers, numOfAiFingers);
    } else if (strategy instanceof AverageStrategy) {
      return strategy.sumOfFingers(numOfAiFingers, strategy.getAverage(game.getHistoryOfFingers()));
    } else {
      return strategy.sumOfFingers(numOfAiFingers, strategy.getMode(game.getHistoryOfFingers()));
    }
  }
}
