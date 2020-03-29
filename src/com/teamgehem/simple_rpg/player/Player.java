package com.teamgehem.simple_rpg.player;

import com.teamgehem.simple_rpg.items.Armor;
import com.teamgehem.simple_rpg.items.Boots;
import com.teamgehem.simple_rpg.items.Item;
import com.teamgehem.simple_rpg.items.Weapon;
import com.teamgehem.simple_rpg.utility.ArmorType;
import com.teamgehem.simple_rpg.utility.Dice;

import java.util.ArrayList;

/**
 * Created by DongHee Kim on 2018-01-22 022.
 */
public class Player extends BattleUnit {
    private static final int MAX_INVENTORY_COUNT = 10;

    private ArrayList<Item> inventory = new ArrayList<>();

    private Dice diceForMove;
    private Dice originalDiceForBattle;

    private Weapon weapon;

    private int additionalDamage = 0;

    //TODO: 배열을 사용하면 null 체크를 해야하는 수고가 있기 때문에, 추후 ArrayList 타입으로 교체해준다.
    private Armor[] armors = new Armor[6];


    public Player(String name) {
        super(name, 100, 0, 0, 12, new Dice(6, 1));
        diceForMove = new Dice(6, 1);
        originalDiceForBattle = new Dice(diceForBattle);
    }

    public boolean addItem(Item item) {
        if (inventory.size() < MAX_INVENTORY_COUNT) {
            return inventory.add(item);
        } else {
            System.out.println("인벤토리가 꽉 찼습니다. 더 이상 아이템을 추가할 수 없습니다.");
            return false;
        }
    }

    private Item getItem(int index, String wrongMessage) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        } else {
            System.out.println(wrongMessage);
            return null;
        }
    }

    public boolean hasItem(int index) {
        return index < inventory.size();
    }

    public void useItem(int index) {
        useItem(index, this);
    }

    public void useItem(int index, BattleUnit unit) {
        Item item = getItem(index, "사용할 아이템이 없습니다.");
        if (item != null) {
            if (item.use(unit)) {
                inventory.remove(index);
            }
        }
    }

    public void equipItem(int index) {
        Item item = getItem(index, "장비할 아이템이 없습니다.");
        if (item != null) {
            if (item instanceof Armor) {
                Armor armor = (Armor) item;
                armors[armor.getArmorType().ordinal()] = armor;
                armorPoint = getAllArmorPoint();
                if(armor.getArmorType() == ArmorType.BOOTS){
                    Boots boots = (Boots)armor;
                    diceForMove.increaseDiceNumber(boots.getMoveValue());
                }
            } else if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;
                this.weapon = weapon;

                additionalDamage = weapon.getWeaponPoint();
            }else{
                System.out.println("장비할 아이템이 없습니다.");
                return;
            }
            System.out.printf("[%s]를 장착헀습니다. 기존 아이템은 버려집니다.\n", item.getName());
            inventory.remove(index);
        }
    }

    public void increaseGold(int gold) {
        this.gold += gold;
    }

    public Dice getDiceForMove() {
        return diceForMove;
    }

    // 레벨 구하는 공식
    // x = level, y = exp
    // y = (x * x ) * 2 + 100
    // x = sqrt(y/2 - 100/2)
    public int getLevel() {
        return (int) Math.sqrt(exp / 2 - 10 / 2);
    }

    public int getAdditionalDamage(){
        return additionalDamage;
    }

    public void increaseExp(int exp) {
        this.exp += exp;
    }

    public int getAllArmorPoint() {
        int armorSum = 0;
        for (int i = 0; i < armors.length; ++i) {
            Armor armor = armors[i];
            if (armor != null) {
                armorSum += armor.getArmorPoint();
            }
        }
        return armorSum;
    }

    public void printInventory() {
        System.out.println("[인벤토리] - (아이템이름: 아이템번호)");
        if (inventory.isEmpty()) {
            System.out.println("인벤토리에 아이템이 없습니다.");
        } else {
            for (int i = 0; i < inventory.size(); ++i) {
                System.out.printf("%d: %s\n", i + 1, inventory.get(i));
            }
        }
    }

    @Override
    public void printUnitInfo() {
        Dice diceForBattle = getDiceForBattle();
        System.out.printf("[유저정보] 이름: %s, 체력: %d, 이동력: %s, 공격력: %s, 방어력: %d", name, healthPoint, diceForMove, diceForBattle, armorPoint);

        System.out.printf(", 레벨: %d, 경험치: %d, 소지금: %d \n", getLevel(), exp, gold);

        int allWeaponPoint = 0;
        int allArmorPoint = getAllArmorPoint();
        boolean isEquippedWeapon = weapon != null;

        if(isEquippedWeapon || allArmorPoint > 0) {
            System.out.println("[장비정보]");

            int indexForPrint = 0;
            for (int i = 0; i < armors.length; ++i) {
                Armor armor = armors[i];
                if (armor != null) {
                    ArmorType armorType = armor.getArmorType();
                    System.out.printf("%d. %s: %s\n", ++indexForPrint,armorType.name(), ((Item)armor).getName());
                }
            }

            if (isEquippedWeapon) {
                System.out.printf("%d. %s: %s\n", ++indexForPrint, "WEAPON", ((Item) weapon).getName());
                allWeaponPoint = weapon.getWeaponPoint();
            }

            System.out.printf("추가 공격력: %d, 추가 방어력: %d\n", allWeaponPoint, allArmorPoint);
        }
    }

    @Override
    public Dice getDiceForBattle() {

        int diceNumber = originalDiceForBattle.getDiceNumber();
        int diceRollingCount = originalDiceForBattle.getDiceRollingCount();

        int level = getLevel();

        diceForBattle.setDice(diceNumber + (level / 2), diceRollingCount + (level / 5));

        return super.getDiceForBattle();
    }
}
