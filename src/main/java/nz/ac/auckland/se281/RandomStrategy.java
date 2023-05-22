package nz.ac.auckland.se281;

public class RandomStrategy extends AIStrategy{

    @Override
    public int sumOfFingers(int numOfFingers) {
        int min = numOfFingers + 1;
        int max = numOfFingers + 5;
        return Utils.getRandomNumber(min, max);
    }
    
}
