package nz.ac.auckland.se281;

public class TopStrategy extends AiStrategy {

  /*
   * This method returns the sum of the number of fingers the AI player will play
   * @param numOfPlayersFingers: the number of fingers the player has played
   * @param numOfAIFingers: the number of fingers the AI has played
   * @return the sum of the number of fingers the AI will play
   */
  @Override
  public int sumOfFingers(int numOfAiFingers, int modeOfHistory) {
    return numOfAiFingers + modeOfHistory;
  }
}
