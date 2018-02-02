package monsters;

import items.Item;
import utility.Dice;

/**
 * Created by DongHee Kim on 2018-02-02 002.
 */
public class Orc extends Monster{
    public Orc() {
        super("오크", 30, 2, 10, 10, new Dice(5,2));
    }
}
