package items.armors;

import items.Armor;
import items.Item;
import utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class NewbiesHelmet extends Item implements Armor {
    public NewbiesHelmet() {
        super("초보용 헬멧", 1, 1);
    }

    @Override
    public ArmorType getArmorType() {
        return ArmorType.HELMET;
    }

    @Override
    public int getArmorPoint() {
        return getValue();
    }
}
