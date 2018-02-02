package monsters;


import items.Item;
import utility.Dice;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class Goblin extends Monster{
    public Goblin() {
        super("고블린", 15, 1, 5, 5, new Dice(3,2));
    }
}
