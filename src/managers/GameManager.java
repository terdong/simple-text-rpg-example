package managers;


import items.Item;
import maps.World;
import monsters.Monster;
import player.BattleUnit;
import player.Player;
import utility.Dice;

import java.util.Random;
import java.util.Scanner;

import static maps.World.*;
import static utility.Messages.*;

/**
 * Created by DongHee Kim on 2018-01-23 023.
 */
public class GameManager {

    private Player player;
    private World world;
    private DiceManager diceManager;
    private MonsterManager monsterManager;
    private ItemManager itemManager;
    private Random random;

    private boolean isPlay = true;

    private Scanner scanner = new Scanner(System.in);

    public GameManager() {

        random = new Random();
        diceManager = new DiceManager(random);
        itemManager = new ItemManager(random);
        monsterManager = new MonsterManager(random, itemManager);
        world = new World(random);

    }

    public void startGame() {
        intro();
        for (; isPlay; ) {

            play();

        }
    }

    private void intro() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(INTRO_00);
        System.out.println(INTRO_01);
        System.out.print(INTRO_02);

//        String playerName = scanner.next();
        String playerName = "동희";
        System.out.printf(INTRO_03, playerName);
        player = new Player(playerName);

        player.addItem(itemManager.getItem("회복물약(소)"));
        player.addItem(itemManager.getItem("숏 소드"));
        player.addItem(itemManager.getItem("초보용 방패"));
        player.addItem(itemManager.getItem("초보용 신발"));

        System.out.println(INTRO_04);
        System.out.println(INTRO_05);
        System.out.println(LINE_SEPARATOR);
    }

    private void play() {

        System.out.println();
        world.printMapInfo();

        System.out.println(GAME_PLAY_MENU);
        checkCommand(getCommandNumber());
        System.out.println(LINE_SEPARATOR);

        if (world.isEncounter()) {
            switch (world.getEncounterNumber()) {
                case BLOCK_STATUS_GOLD:
                    int increaseGold = random.nextInt(5) + 1;
                    player.increaseGold(increaseGold);
                    System.out.printf(GAME_GET_GOLD, increaseGold, player.getGold());
                    break;
                case BLOCK_STATUS_MONSTER:
                    try {
                        battleMonster(monsterManager.getRandomMonster(world.getMapProgress()));
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        System.out.println("몬스터 생성에 문제가 생겨 스킵합니다.");
                        return;
                    }
                    break;
                case BLOCK_STATUS_BOSS:
                    battleMonster(monsterManager.getBoss());
                    break;
                case BLOCK_STATUS_POTAL:
                    System.out.println("축하드립니다. 드디어 목적지에 도달했습니다. 당신은 당신이 왔던 세계로 무사히 돌아갔습니다.");
                    isPlay = false;
                    break;
            }
        }
    }

    private int getCommandNumber() {
        System.out.print(INPUT);
        int commandNumber = scanner.nextInt();
        System.out.println();
        return commandNumber;
    }

    private void battleMonster(Monster monster) {

        System.out.printf("%s이(가) 나타났다!. 잔뜩 화가난 %s은(는) 무작정 당신에게 달려듭니다.\n", monster.getName(), monster.getName());
        System.out.println("전투를 시작합니다.");
        boolean isBattle = true;
        String monsterName = monster.getName();
        for (; isBattle; ) {
            System.out.println();

            // 플레이어 정보
            player.printUnitInfo();
            System.out.println();
            // 몬스터 정보
            monster.printUnitInfo();

            // 플레이어 패배 체크
            if (player.isDead()) {
                isPlay = false;
                isBattle = false;
                System.out.println();
                System.out.println("몬스터와의 전투에서 패배했습니다.");
                System.out.println("마계로 떨어진 플레이어는 결국 탈출을 못하고 이곳에서 생을 마감했습니다.");
                System.out.println("################################## GAME OVER ########################################");
                System.out.println(LINE_SEPARATOR);
            }
            // 몬스터 패배 체크
            else if (monster.isDead()) {
                isBattle = false;
                System.out.println();
                System.out.println("몬스터와의 전투에서 승리했습니다.");
                System.out.println(LINE_SEPARATOR);

                // 전리품 획득
                int getExp = monster.getExp();
                player.increaseExp(getExp);
                System.out.printf("%d 경험치를 획득했습니다.(현재 레벨: %d, 현재 경험치: %d)\n", getExp, player.getLevel(), player.getExp());

                int getGold = monster.getGold();
                player.increaseGold(getGold);
                System.out.printf(GAME_GET_GOLD, getGold, player.getGold());

                if (monster.isReward()) {
                    Item reward = monster.getReward();
                    if (player.addItem(reward)) {
                        System.out.printf("[%s] 아이템을 획득했습니다.", reward.getName());
                    }
                }

                world.changeBattleOff();
            }

            System.out.println();

            if (isBattle) {
                // 플레이어 턴
                System.out.println(LINE_SEPARATOR);
                System.out.println("# [플레이어 턴] 전투메뉴: 1. 공격, 2. x2 크리티컬 공격(실패 확률 60%), 3. 아이템 사용");
                int commandNumber = getCommandNumber();

                int damage = 0;
                Dice battleDice;
                switch (commandNumber) {
                    case 1:
                        battleDice = player.getDiceForBattle();

                        damage = diceManager.rollDice(battleDice) + player.getAdditionalDamage();
                        System.out.printf("# %s에게 %d 만큼의 피해를 주었습니다.\n", monsterName, damage);
                        monster.getDamage(damage);
                        break;
                    case 2:
                        if (random.nextInt(10) < 4) {
                            battleDice = player.getDiceForBattle();
                            damage = diceManager.rollDice(battleDice) + diceManager.rollDice(battleDice) + player.getAdditionalDamage();
                            System.out.printf("# %s에게 %d 만큼의 크리티컬 피해를 주었습니다.\n", monsterName, damage);
                            monster.getDamage(damage);
                        } else {
                            System.out.println("크리티컬 공격에 실패 했습니다.");
                        }
                        break;
                    case 3:
                        useItem(player, monster);
                        break;
                }
                System.out.println(LINE_SEPARATOR);

                // 몬스터 턴
                System.out.println();
                System.out.println(LINE_SEPARATOR);
                System.out.println("# [몬스터 턴] ");
                damage = diceManager.rollDice(monster.getDiceForBattle());
                System.out.printf("# %s은(는) 플레이어에게 %d 만큼의 피해를 주었습니다.\n", monsterName, damage);
                player.getDamage(damage);
                System.out.println(LINE_SEPARATOR);
            }
        }
    }

    private void checkCommand(int commandNumber) {
        // 1. 이동, 2. 플레이어 정보, 3. 인벤토리 4. 장비, 5. 아이템사용, 6. 도움말, 7. 게임 종료
        switch (commandNumber) {
            case 1:
                int moveCount = diceManager.rollDice(player.getDiceForMove());
                System.out.printf("이동하기 위해 주사위를 굴립니다. %d이 나왔습니다.\n%d칸 이동합니다.\n", moveCount, moveCount);
                world.movePlayer(moveCount);
                break;
            case 2:
                player.printUnitInfo();
                break;
            case 3:
                player.printInventory();
                break;
            case 4:
                System.out.print("장비할 아이템 번호를 입력해주세요.\n입력: ");
                int itemIndex = scanner.nextInt() - 1;
                player.equipItem(itemIndex);
                break;
            case 5:
                useItem(player);
                break;
            case 6:
                System.out.println("상점에 오신걸 환영합니다.");
                System.out.println("당신이 10골드 이상 보유했다면, 보유한 골드당 체력 +1을 회복시켜드리겠습니다.");
                int playerGold = player.getGold();
                if(playerGold >= 10) {
                    player.increaseHealthPoint(playerGold);
                    System.out.printf(GAME_STORE_TARGET, player.getName(), playerGold, playerGold);
                    player.increaseGold(-playerGold);
                }else{
                    System.out.println("아직 골드가 부족하시군요. 골드를 더 모아 오세요.");
                }
                break;
            case 7:
                System.out.println(GAME_HELP);
                break;
            default:
                System.out.println("게임을 끝냅니다.");
                isPlay = false;
                break;
        }
    }

    private void useItem(Player player){
        useItem(player, null);
    }

    private void useItem(Player player, BattleUnit monster) {
        boolean isSelect = false;
        for (; !isSelect; ) {
            System.out.println("사용할 아이템을 선택해 주세요.");
            player.printInventory();
            System.out.print("입력: ");
            int itemIndex = scanner.nextInt() - 1;
            int target = 1;

            if (monster != null) {
                System.out.println("누구에게 사용 하시겠습니까? 1. 플레이어, 2. 몬스터");
                target = scanner.nextInt();
            }

            if (player.hasItem(itemIndex) && (target == 1 || target == 2)) {
                isSelect = true;
                BattleUnit targetUnit = target == 1 ? player : monster;
                player.useItem(itemIndex, targetUnit);
            } else {
                System.out.println("잘못 입력하셨습니다.");
            }

        }
    }

}
