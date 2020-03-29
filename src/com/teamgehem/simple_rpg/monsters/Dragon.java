package com.teamgehem.simple_rpg.monsters;

import com.teamgehem.simple_rpg.utility.Dice;

/**
 * Created by DongHee Kim on 2018-02-02 002.
 */
public class Dragon extends Monster{
    public Dragon() {
        super("드래곤", 100, 10, 999, 999, new Dice(8,3));
    }
}
