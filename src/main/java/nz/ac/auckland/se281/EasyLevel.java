package nz.ac.auckland.se281;

public class EasyLevel implements Level {

  /*
   * @param numOfPlays: the number of plays that have been played
   * @return: the strategy that the AI will use - which is Rnadom Strategy
   */
  @Override
  public AiStrategy strategy(int numOfPlays) {
    return new RandomStrategy();
  }
}
