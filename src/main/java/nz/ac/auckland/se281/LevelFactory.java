package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class LevelFactory {

  /*
   * This method returns the level of difficulty the user has chosen.
   * @param difficulty: the level of difficulty the user has chosen
   * @return the level of difficulty the user has chosen, and this will trigger the reciporating strategy for these levels
   */
  public static Level setDifficulty(Difficulty difficulty) {
    switch (difficulty) {
      case EASY:
        return new EasyLevel();
      case MEDIUM:
        return new MediumLevel();
      case HARD:
        return new HardLevel();
      case MASTER:
        return new MasterLevel();
      default:
        return null;
    }
  }
}
