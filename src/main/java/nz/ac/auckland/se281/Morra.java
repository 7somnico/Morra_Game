package nz.ac.auckland.se281;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private List<Integer> historyOfFingers;
  private int numOfPlays;
  private String playerName;

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    this.playerName = options[0];
    numOfPlays = 0;
  }

  public int getNumOfPlays() {
    return numOfPlays;
  }

  public boolean checkIfValidRange(String[] inputStrArray) {
    
    // if the first string in the array is in matches the range of 1-5 and the second string in the array matches in between the range of 1-10
    if (inputStrArray[0].matches("[1-5]+") && inputStrArray[1].matches("[1-9]|10")) {
      return true;
    }
    return false;
  }

  public List<Integer> lastFourHistory() {
    List<Integer> lastFourHistory = new ArrayList<>();

    for (int i = (historyOfFingers.size() - 5); i < historyOfFingers.size(); i++) {
      lastFourHistory.add(historyOfFingers.get(i));
    }

    return lastFourHistory;
  }

  public void play() {
    historyOfFingers = new ArrayList<>();
    numOfPlays++;
    MessageCli.START_ROUND.printMessage(Integer.toString(numOfPlays));
    MessageCli.ASK_INPUT.printMessage();

    String input = Utils.scanner.nextLine();
    String[] inputStrArray = input.split(" ");

    while (!checkIfValidRange(inputStrArray)) {
      MessageCli.INVALID_INPUT.printMessage();
      MessageCli.ASK_INPUT.printMessage();
      inputStrArray = (Utils.scanner.nextLine()).split(" ");
      checkIfValidRange(inputStrArray);
    }
    
    MessageCli.PRINT_INFO_HAND.printMessage(playerName, inputStrArray[0], inputStrArray[1]); 

    RandomStrategy randomStrategy = new RandomStrategy();
    Integer numOfFingers = randomStrategy.numOfFingers();
    historyOfFingers.add(numOfFingers);

    Integer sumOfFingers = randomStrategy.sumOfFingers(numOfFingers);
    MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", Integer.toString(numOfFingers), Integer.toString(sumOfFingers));
    
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(randomStrategy.outcome(inputStrArray[1], sumOfFingers, inputStrArray[0], numOfFingers));

  }

  public void showStats() {}
}
