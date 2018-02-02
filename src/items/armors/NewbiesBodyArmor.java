package items.armors;

import items.Armor;
import items.Item;
import player.Player;
import utility.ArmorType;

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
