package utility;

/**
 * Created by DongHee Kim on 2018-01-22 022.
 */
public class Dice {
    private int diceNumber;
    private int diceRollingCount;

    public Dice(int diceNumber, int diceRollingCount) {
        setDice(diceNumber, diceRollingCount);
    }

    public Dice(Dice dice){
        this.diceNumber = dice.getDiceNumber();
        this.diceRollingCount = dice.getDiceRollingCount();
    }

    public void setDice(int diceNumber, int diceRollingCount){
        this.diceNumber = diceNumber;
        this.diceRollingCount = diceRollingCount;
    }

    public void increaseDiceNumber(int diceNumber){
        this.diceNumber += diceNumber;
    }
    public void increaseDiceRollingCount(int diceRollingCount){
        this.diceRollingCount += diceRollingCount;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public int getDiceRollingCount() {
        return diceRollingCount;
    }

    @Override
    public String toString() {
        return String.format("%dd%d", diceNumber, diceRollingCount);
    }
}
