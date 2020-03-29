package com.teamgehem.simple_rpg.items;

import com.teamgehem.simple_rpg.player.BattleUnit;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public abstract class Item {

    private String name;
    private int value;
    private int price;

    public Item(String name, int value, int price){
        this.name = name;
        this.value = value;
        this.price = price;
    }

    public boolean use(BattleUnit unit) {
        System.out.println("이 아이템은 사용할 수 없습니다. 장착용 아이템입니다.");
        return false;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s - 가치: %d, 가격: %d", name, value, price);
    }
}
