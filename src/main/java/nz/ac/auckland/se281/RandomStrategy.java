package nz.ac.auckland.se281;

public class RandomStrategy extends AIStrategy {

  /*
   * This method returns the sum of the number of fingers the AI player will play.
   * In this particular strategy, the number of player's fingers will be irrelevant to
   * find the sum of fingers the AI will guess.
   * @param numOfPlayersFingers: the number of fingers the player has played
   * @param numOfAIFingers: the number of fingers the AI has played
   * @return the sum of the number of fingers the AI will play
   */
  @Override
  public int sumOfFingers(int numOfPlayersFingers, int numOfAiFingers) {
    int min = numOfAiFingers + 1;
    int max = numOfAiFingers + 5;
    return Utils.getRandomNumber(min, max);
  }
}
