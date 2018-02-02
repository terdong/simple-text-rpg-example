package items.armors;

import items.Armor;
import items.Item;
import utility.ArmorType;

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
