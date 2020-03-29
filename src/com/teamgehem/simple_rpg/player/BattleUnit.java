package com.teamgehem.simple_rpg.player;

import com.teamgehem.simple_rpg.utility.Dice;

/**
 * Created by DongHee Kim on 2018-01-27 027.
 * 만약 더 상위 개념의 클래스가 필요한 경우는 BattleUnit 과 Unit(상위 클래스)으로 나누고, BattleUnit이 Unit을 상속 받는다.
 */
public abstract class BattleUnit {

    // 유닛 이름
    protected String name;
    // 유닛의 남은 체력
    protected int healthPoint;
    // 유닛의 방어력
    protected int armorPoint;
    // 유닛의 소지 골드
    protected int gold;
    // 유닛의 경험치
    protected int exp;

    protected Dice diceForBattle;

    protected BattleUnit(String name, int healthPoint, int armorPoint, int gold, int exp, Dice diceForBattle) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.armorPoint = armorPoint;
        this.gold = gold;
        this.exp = exp;
        this.diceForBattle = diceForBattle;
    }

    public void getDamage(int damage) {
        int computedDamage = Math.max(damage - armorPoint, 0);
        healthPoint = Math.max(healthPoint - computedDamage, 0);
        System.out.printf("# %s은(는) 실제로 %d 만큼의 피해를 입었습니다.\n", name, computedDamage);
    }

    public Dice getDiceForBattle() {
        return diceForBattle;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public boolean isDead() {
        return healthPoint <= 0;
    }

    public int getExp(){
        return exp;
    }

    public void increaseHealthPoint(int value){
        healthPoint += value;
    }

    public void printUnitInfo() {
        System.out.printf("[유닛정보] 이름: %s, 체력: %d, 공격력: %dd%d, 방어력: %d", name, healthPoint, diceForBattle.getDiceRollingCount(), diceForBattle.getDiceNumber(), armorPoint);
    }
}
