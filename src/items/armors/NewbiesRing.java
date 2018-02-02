package items.armors;

import items.Armor;
import items.Item;
import utility.ArmorType;

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
