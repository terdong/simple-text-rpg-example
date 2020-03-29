package com.teamgehem.simple_rpg.items.armors;

import com.teamgehem.simple_rpg.items.Armor;
import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class NewbiesShield extends Item implements Armor {
    public NewbiesShield() {
        super("초보용 방패", 1, 1);
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
