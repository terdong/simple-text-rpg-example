package items.potions;


import items.Item;
import player.BattleUnit;

import static utility.Messages.GAME_HEAL_TARGET;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class HealingPotion extends Item {

    public HealingPotion(String name, int value, int price) {
        super(name, value, price);
    }

    @Override
    public boolean use(BattleUnit unit) {
        int heal = getValue();
        unit.increaseHealthPoint(heal);
        System.out.printf(GAME_HEAL_TARGET, unit.getName(), heal);
        return true;
    }
}
