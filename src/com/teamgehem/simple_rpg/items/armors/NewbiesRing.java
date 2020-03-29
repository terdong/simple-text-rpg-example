package com.teamgehem.simple_rpg.items.armors;

import com.teamgehem.simple_rpg.items.Armor;
import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class NewbiesRing extends Item implements Armor {
    public NewbiesRing() {
        super("초보용 반지", 1, 1);
    }

    @Override
    public ArmorType getArmorType() {
        return ArmorType.RING;
    }

    @Override
    public int getArmorPoint() {
        return getValue();
    }
}
