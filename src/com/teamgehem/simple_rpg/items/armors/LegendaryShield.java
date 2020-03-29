package com.teamgehem.simple_rpg.items.armors;

import com.teamgehem.simple_rpg.items.Armor;
import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-02-03 003.
 */
public class LegendaryShield extends Item implements Armor {
    public LegendaryShield () {
        super("전설의 방패", 10, 1);
    }

    @Override
    public ArmorType getArmorType() {
        return ArmorType.SHIELD;
    }

    @Override
    public int getArmorPoint() {
        return getValue();
    }
}
