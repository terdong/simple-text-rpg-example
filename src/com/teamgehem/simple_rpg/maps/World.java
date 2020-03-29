package com.teamgehem.simple_rpg.maps;


import java.util.Random;

/**
 * Created by DongHee Kim on 2018-01-23 023.
 */
public class World {

    public static final int BLOCK_STATUS_LAND = 0; // 일반 땅
    public static final int BLOCK_STATUS_PLAYER = 1; // 플레이어
    public static final int BLOCK_STATUS_GOLD = 2; // 해당 위치로 이동시 골드 획득
    public static final int BLOCK_STATUS_MONSTER = 3; // 몬스터
    public static final int BLOCK_STATUS_BOSS = 4; // 보스
    public static final int BLOCK_STATUS_POTAL = 5; // 목적지
    //private static final int BLOCK_STATUS_MUD= 1; // 밟으면 (다음 주사위 합 / 2)
    //private static final int BLOCK_STATUS_DICE = 2; // 밟으면 주사위 눈개수 3 ~ 8 사이로 변경

    /**
     * 맵 정보를 문자로 출력하기 위한 문자 테이블
     */
    private static final char[] BLOCK_DISPLAY_ARRAY = {'.', 'P', 'g', 'm', 'B', '@'};

    private Random random;

    /**
     * 맵 최대 길이
     */
    private int mapLength = 100;

    /**
     * 맵 정보 데이터
     */
    private int[] gameMap;

    private int previousMapData;
    private int playerPosition;

    private boolean isBattle;

    public World(Random random) {
        this.random = random;
        initializeMap();
    }

    /**
     * 맵 초기화
     */
    public void initializeMap() {
        gameMap = new int[mapLength];

        for (int i = 1; i < gameMap.length - 2; ++i) {
            // 40% 확률로 골드 획득
            if (random.nextInt(4) == 0) {
                gameMap[i] = BLOCK_STATUS_GOLD;

            }
            // 30% 확률로 몬스터와 전투
            else if (random.nextInt(3) == 0) {
                gameMap[i] = BLOCK_STATUS_MONSTER;
            }
        }
        gameMap[0] = BLOCK_STATUS_PLAYER;
        gameMap[mapLength - 2] = BLOCK_STATUS_BOSS;
        gameMap[mapLength - 1] = BLOCK_STATUS_POTAL;

        playerPosition = 0;
        previousMapData = BLOCK_STATUS_LAND;
    }

    /**
     * 현재 플레이어가 위치한 맵의 상태 정보 가져오기
     * @return
     */
    public int getEncounterNumber() {
        return previousMapData;
    }

    /**
     * 현재 이벤트 발생 조건인지 확인
     * @return
     */
    public boolean isEncounter() {
        return isBattle && previousMapData > BLOCK_STATUS_LAND;
    }

    /**
     * 전투에서 이겼을 경우 전투 인카운터 끄기
     */
    public void changeBattleOff() {
        isBattle = false;
    }

    /**
     * 현재 맵의 진행 상태를 퍼센트로 반환
     * @return
     */
    public int getMapProgress(){
        int mapProgress = playerPosition * 100 / mapLength;
        //System.out.printf("진행:%d, 퍼센트: %d\n", playerPosition, mapProgress);
        return mapProgress;
    }

    /**
     * 플레이어를 이동
     * @param movingDistance 이동할 거리
     */
    public void movePlayer(int movingDistance) {

        // 플레이어가 움직이면 언제든지 전투 가능상태로 전환
        isBattle = true;

        gameMap[playerPosition] = BLOCK_STATUS_LAND;
        playerPosition += movingDistance;

        if (previousMapData != BLOCK_STATUS_BOSS && playerPosition > mapLength - 2) {
            playerPosition = mapLength - 2;
        } else if (playerPosition > mapLength - 1) {
            playerPosition = mapLength - 1;
        }

        previousMapData = gameMap[playerPosition];
        gameMap[playerPosition] = BLOCK_STATUS_PLAYER;
    }

    /**
     * 현재 맵 정보 출력
     */
    public void printMapInfo() {
        System.out.print("지도: ");
        int maxDistance = Math.min(playerPosition + 10, mapLength - 1);
        for (int i = Math.max(playerPosition - 10, 0); i <= maxDistance; ++i) {
            System.out.printf("[%c]", BLOCK_DISPLAY_ARRAY[gameMap[i]]);
        }
        System.out.printf(" (진행률: %d/100%%)\n",getMapProgress());
    }
}
