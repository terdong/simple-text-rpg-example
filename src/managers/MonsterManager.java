package managers;

import monsters.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class MonsterManager {

    private Random random;
    private ArrayList<Monster> monsterArrayList;
    private ItemManager itemManager;
    private Monster boss = new Dragon();

    public MonsterManager(Random random, ItemManager itemManager){
        this.random = random;
        this.itemManager = itemManager;
        monsterArrayList = new ArrayList<>();

        monsterArrayList.add(new Goblin());
        monsterArrayList.add(new Orc());
        monsterArrayList.add(new Golem());
    }

    /**
     * 맵의 진행도에 따라 몬스터 리스폰 난이도가 점점 높아짐.
     * @param mapProgressPercent 현재 맵의 진행율
     * @return
     * @throws CloneNotSupportedException
     */
    public Monster getRandomMonster(int mapProgressPercent) throws CloneNotSupportedException {

        int randomRange;

        if(mapProgressPercent < 20){
            randomRange = 1;
        }else if(mapProgressPercent < 50) {
            randomRange = 2;
        }else{
            randomRange = monsterArrayList.size();
        }

        return ((Monster)monsterArrayList.get(random.nextInt(randomRange)).clone()).setReward(itemManager.getRandomItem());
    }


    public Monster getBoss(){
        return boss;
    }

}
