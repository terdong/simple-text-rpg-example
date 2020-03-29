package com.teamgehem.simple_rpg.items.potions;


import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.player.BattleUnit;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class DiceUpgradePotion extends Item {

    public DiceUpgradePotion(String name, int value, int price) {
        super(name, value, price);
    }

    @Override
    public boolean use(BattleUnit unit) {
        return false;
    }
}
