package nz.ac.auckland.se281;

public class HardLevel implements Level {

  /*
   * @param numOfPlays: the number of plays that have been played
   * @return: the strategy that the AI will use - which is Top Strategy onve the
   * first three plays have been made
   */
  @Override
  public AiStrategy strategy(int numOfPlays) {
    if (numOfPlays < 4) {
      return new RandomStrategy();
    }
    return new TopStrategy();
  }
}
