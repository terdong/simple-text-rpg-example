package managers;

import utility.Dice;

import java.util.Random;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class DiceManager {
    private Random random;

    public DiceManager(Random random) {
        this.random = random;
    }

    public int rollDice(Dice dice) {
        int sum = 0;
        int diceNumber = dice.getDiceNumber();
        int diceCount = dice.getDiceRollingCount();

        for(int i=0; i<diceCount; ++i){
            sum += random.nextInt(diceNumber) + 1;
        }
        return sum;
    }
}
