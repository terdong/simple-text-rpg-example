package com.teamgehem.simple_rpg.items.armors;

import com.teamgehem.simple_rpg.items.Armor;
import com.teamgehem.simple_rpg.items.Boots;
import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class NewbiesBoots extends Item implements Armor, Boots {
    public NewbiesBoots() {
        super("초보용 신발", 1, 1);
    }

    @Override
    public ArmorType getArmorType() {
        return ArmorType.BOOTS;
    }

    @Override
    public int getArmorPoint() {
        return getValue();
    }

    @Override
    public int getMoveValue() {
        return 2;
    }
}
