package nz.ac.auckland.se281;

public class MasterLevel implements Level {

  /*
   * @param numOfPlays: the number of plays that have been played
   * @return: the strategy that the AI will use - alternating between random and top strategy once the first three plays have been made
   */
  @Override
  public AIStrategy strategy(int numOfPlays) {
    if (numOfPlays < 4) {
      return new RandomStrategy();
    } else if (numOfPlays % 2 == 0) {
      return new AverageStrategy();
    }
    return new TopStrategy();
  }
}
