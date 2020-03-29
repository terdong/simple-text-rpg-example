package com.teamgehem.simple_rpg.items.potions;

import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.player.BattleUnit;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class PoisonPotion extends Item {

    public PoisonPotion(String name, int value, int price) {
        super(name, price, value);
    }

    @Override
    public boolean use(BattleUnit unit) {
        int damage = -getValue();
        unit.increaseHealthPoint(damage);
        System.out.printf("[%s]에게 %d의 피해를 주었습니다.\n", unit.getName(), damage);
        return true;
    }
}
