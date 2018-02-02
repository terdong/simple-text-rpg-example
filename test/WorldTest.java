
import maps.World;

import java.util.Random;

/**
 * Created by DongHee Kim on 2018-01-25 025.
 */
public class WorldTest {
    public static void main(String[] args) {
        World world = new World(new Random());

        world.initializeMap();
        world.printMapInfo();
    }
}
