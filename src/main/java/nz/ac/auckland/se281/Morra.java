package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private List<Integer> historyOfFingers;
  private Level level;
  private int numOfPlays;
  private int pointsToWin;
  private int playerPoints;
  private int aiPoints;
  private String playerName;
  private boolean hasGameStarted;

  /*
   * This is the constructor for the Morra class
   */
  public Morra() {
    this.playerPoints = 0;
    this.aiPoints = 0;
    this.hasGameStarted = false;
  }

  /*
   * This method returns the level of difficulty the user has chosen, and sets up the
   * game so that it is ready for the player to play
   * @param difficulty: the level of difficulty the user has chosen
   * @param pointsToWin: the number of points the user has chosen to play up to
   * @param options: array of the player's name
   */
  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    this.level = LevelFactory.setDifficulty(difficulty);
    this.playerName = options[0];
    this.pointsToWin = pointsToWin;
    this.hasGameStarted = true;
    this.historyOfFingers = new ArrayList<>();
    numOfPlays = 0;
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
  }

  /*
   * @return the number of points the user has chosen to play up to
   */
  public int getNumOfPlays() {
    return numOfPlays;
  }

  /*
   * @return the number of list of the user's history in their choice in number of fingers
   */
  public List<Integer> getHistoryOfFingers() {
    return historyOfFingers;
  }

  /*
   * @return the number of points the player has won at the moment
   */
  public int getNumOfWins() {
    return playerPoints;
  }

  /*
   * This method is used to check if the player has given a valid input, within the range of 1-5 and 1-10
   * @param inputStrArray: the array of the user's input
   * @return true if the user has given a valid input, false otherwise
   */
  public boolean checkIfValidRange(String[] inputStrArray) {
    // if the first string in the array is in matches the range of 1-5 and the second string in the
    // array matches in between the range of 1-10
    if (inputStrArray[0].matches("[1-5]+") && inputStrArray[1].matches("[1-9]|10")) {
      return true;
    }
    return false;
  }

  /*
   * This method is used to start playing the game
   */
  public void play() {

    // if the player has not entered in "NEW_GAME" in the command line, then the game will not start
    if (!hasGameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // increment the number of plays made, and ask for the player's input
    numOfPlays++;
    MessageCli.START_ROUND.printMessage(Integer.toString(numOfPlays));
    MessageCli.ASK_INPUT.printMessage();

    // check if the player has given a valid input, if not, then ask for the player's input again
    String input = Utils.scanner.nextLine();
    String[] inputStrArray = input.split(" ");
    while (!Utils.isInteger(inputStrArray[0])
        || !Utils.isInteger(inputStrArray[1])
        || !checkIfValidRange(inputStrArray)) {
      MessageCli.INVALID_INPUT.printMessage();
      MessageCli.ASK_INPUT.printMessage();
      inputStrArray = (Utils.scanner.nextLine()).split(" ");
      checkIfValidRange(inputStrArray);
    }

    // set the variables to be the valid input of the player
    String playerFingers = inputStrArray[0];
    String playerSumFinger = inputStrArray[1];

    MessageCli.PRINT_INFO_HAND.printMessage(playerName, playerFingers, playerSumFinger);

    // execute the play
    executePlay(Integer.parseInt(playerFingers), Integer.parseInt(playerSumFinger));
  }

  /*
   * This method is used to execute play, and for the AI to make its move
   * @param playerFingers: the number of fingers the player has played
   * @param playerSum: the sum of the number of fingers the player has played
   */
  public void executePlay(int playerFingers, int playerSum) {

    // create a new AI to make its move
    AI ai = new AI();

    // get the AI's strategy, which is determined by the level that the player wanted to play with
    AiStrategy aiStrategy = level.strategy(numOfPlays);

    historyOfFingers.add(playerFingers);

    // get the number of fingers the AI has played, and the sum of the number of fingers the AI has
    int numOfAisFingers = aiStrategy.numOfAisFingers();
    int sumOfAisFingers = ai.getAiFingersSum(this, aiStrategy, playerFingers, numOfAisFingers);

    MessageCli.PRINT_INFO_HAND.printMessage(
        "Jarvis", Integer.toString(numOfAisFingers), Integer.toString(sumOfAisFingers));

    // get the outcome of the play
    String result = aiStrategy.outcome(sumOfAisFingers, playerSum, numOfAisFingers, playerFingers);
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(result);

    // increment the number of wins for the player or the AI, or a draw
    incrementWins(result);

    checkIfAnyoneWon();
  }

  /*
   * This method is used to increment the number of wins for the player or the AI, or a draw
   * @param resultOfPlay: the result of the play
   */
  public void incrementWins(String resultOfPlay) {
    if (resultOfPlay.equals("HUMAN_WINS")) {
      playerPoints++;
    } else if (resultOfPlay.equals("AI_WINS")) {
      aiPoints++;
    }
  }

  /*
   * This method is used to check if anyone has won the game, if so, then the game will end
   */
  public void checkIfAnyoneWon() {
    if (playerPoints == pointsToWin) {
      MessageCli.END_GAME.printMessage(playerName, Integer.toString(numOfPlays));
      this.hasGameStarted = false;
    } else if (aiPoints == pointsToWin) {
      MessageCli.END_GAME.printMessage("Jarvis", Integer.toString(numOfPlays));
      this.hasGameStarted = false;
    }
  }

  /*
   * This method is used to show the statistics of the game
   * It will show the number of wins the player has, the number of wins the AI has, and the number of
   * wins left for the player and the AI to win the game
   */
  public void showStats() {

    // if the player has either not entered in "NEW_GAME" in the command line, or the game is over,
    // then
    // the statistics will not be shown
    if (!hasGameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    MessageCli.PRINT_PLAYER_WINS.printMessage(
        playerName, Integer.toString(playerPoints), Integer.toString(pointsToWin - playerPoints));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        "Jarvis", Integer.toString(aiPoints), Integer.toString(pointsToWin - aiPoints));
  }
}
