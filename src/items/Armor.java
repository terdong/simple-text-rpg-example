package items;

import utility.ArmorType;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public interface Armor {
//    void equipArmor(Player targetPlayer);
//    void releaseArmor(Player targetPlayer);
    ArmorType getArmorType();
    int getArmorPoint();
}
