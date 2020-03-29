package com.teamgehem.simple_rpg.items.weapons;


import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.items.Weapon;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class Shortsword extends Item implements Weapon {
    public Shortsword() {
        super("숏 소드", 2, 10);
    }

    @Override
    public int getWeaponPoint() {
        return getValue();
    }
}
