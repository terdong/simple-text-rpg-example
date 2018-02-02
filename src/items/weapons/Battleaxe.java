package items.weapons;

import items.Item;
import items.Weapon;
import player.Player;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class Battleaxe extends Item implements Weapon {
    public Battleaxe() {
        super("베틀 엑스", 2, 10);
    }

    @Override
    public int getWeaponPoint() {
        return getValue();
    }
}
