package managers;


import items.Item;
import items.armors.*;
import items.potions.HealingPotion;
import items.potions.PoisonPotion;
import items.weapons.Battleaxe;
import items.weapons.Shortsword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by DongHee Kim on 2018-01-29 029.
 */
public class ItemManager {
    private Random random;
    private ArrayList<Item> itemArrayList;
    private HashMap<String, Item> itemHashMap;

    public ItemManager(Random random){
        this.random = random;
        itemArrayList = new ArrayList<>();
        itemHashMap = new HashMap<>();

        initialize();
    }

    private void initialize(){

        addItem(new HealingPotion("회복물약(대)", 10, 30));
        addItem(new HealingPotion("회복물약(소)", 10, 10));
        addItem(new PoisonPotion("독약(대)", 10, 20));
        addItem(new PoisonPotion("독약(소)", 10, 10));
        addItem(new Shortsword());
        addItem(new Battleaxe());
        addItem(new NewbiesBodyArmor());
        addItem(new NewbiesBoots());
        addItem(new NewbiesHelmet());
        addItem(new NewbiesRing());
        addItem(new NewbiesShield());
        addItem(new LegendaryShield());

    }

    public void addItem(Item item){
        itemArrayList.add(item);
        itemHashMap.put(item.getName(), item);
    }

    public Item getItem(String name){
        return itemHashMap.get(name);
    }

    /**
     * 3항 연산자를 이용해서 40% 확률로 랜덤 아이템을 생성해냄.
     * @return
     */
    public Item getRandomItem(){
        return getRandomItem(40);
    }

    /**
     *
     * @param percent 아이템을 생성할 확률: 0 ~ 100 사이의 값
     * @return
     */
    public Item getRandomItem(int percent){
        return random.nextInt(100) < percent ? itemArrayList.get(random.nextInt(itemArrayList.size())) : null;
    }
}
