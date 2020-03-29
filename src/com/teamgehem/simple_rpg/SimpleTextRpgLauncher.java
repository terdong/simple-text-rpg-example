package com.teamgehem.simple_rpg;

import com.teamgehem.simple_rpg.managers.GameManager;

/**
 * Created by DongHee Kim on 2018-01-22 022.
 */
public class SimpleTextRpgLauncher {
    public static void main(String[] args) {

        GameManager gameManager = new GameManager();
        gameManager.startGame();

    }
}
