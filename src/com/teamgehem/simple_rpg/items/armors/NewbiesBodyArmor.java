package com.teamgehem.simple_rpg.items.armors;

import com.teamgehem.simple_rpg.items.Armor;
import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class NewbiesBodyArmor extends Item implements Armor{
    public NewbiesBodyArmor() {
        super("초보용 갑옷", 1, 1);
    }

    @Override
    public ArmorType getArmorType() {
        return ArmorType.BODY;
    }

    @Override
    public int getArmorPoint() {
        return getValue();
    }
}
