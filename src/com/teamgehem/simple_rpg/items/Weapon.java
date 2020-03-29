package com.teamgehem.simple_rpg.items;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public interface Weapon {
    // 당장은 Player 클래스에서도 충분히 구현해주어도 크게 문제가 없다.
//    void EquipWeapon(Player targetPlayer);
//    void ReleaseWeapon(Player targetPlayer);
    int getWeaponPoint();
}
