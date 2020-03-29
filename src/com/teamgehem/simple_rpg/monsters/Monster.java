package com.teamgehem.simple_rpg.monsters;


import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.player.BattleUnit;
import com.teamgehem.simple_rpg.utility.Dice;

/**
 * Created by DongHee Kim on 2018-01-22 022.
 */
public abstract class Monster extends BattleUnit implements Cloneable{

    protected Item reward;

    protected Monster(String name, int healthPoint, int armorPoint, int gold, int exp, Dice diceForBattle){
        super(name,healthPoint,armorPoint,gold,exp, diceForBattle);
    }

    public Item getReward(){
        return reward;
    }

    public boolean isReward(){
        return reward != null;
    }

    public Monster setReward(Item reward){
        this.reward = reward;
        return this;
    }

    public Object clone() throws CloneNotSupportedException {
            return super.clone();
    }

    @Override
    public void printUnitInfo() {
        super.printUnitInfo();
        System.out.println();
    }
}
