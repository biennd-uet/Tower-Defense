package townerdefense;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameConfig {
    //TODO: All settings
    //Size of Screen
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 890;
    //Size of Stage
    public static final int STAGE_WIDTH = 1080;
    public static final int STAGE_HEIGHT = 720;
    //Name of Game
    public static final String GAME_TITLE = "Tower Defense";
    //Number of tile in
    public static final int NUMBER_TILE_IN_HORIZONTAL = 10;
    public static final int NUMBER_TILE_IN_VERTICAL = 10;
    //Size of Tile
    public static final int SIZE_TILE_WIDTH = STAGE_WIDTH / NUMBER_TILE_IN_HORIZONTAL;
    public static final int SIZE_TILE_HEIGHT = STAGE_HEIGHT / NUMBER_TILE_IN_VERTICAL;
    //Unit
    public static final int SIZE_UNIT = SIZE_TILE_WIDTH * 6 / 10;
    //FPS
    public static final int FPS = 60;
    //nanosecond per second
    public static final int NPS = 1000000000;
    //nanosecond per frame
    public static final int NPF = NPS / FPS;
    //Enemy Config
    public static final int NORMAL_ENEMY_WIDTH = SIZE_UNIT;
    public static final int NORMAL_ENEMY_HEIGHT = SIZE_UNIT;
    public static final int NORMAL_ENEMY_HEALTH_MAX = 100;
    public static final int NORMAL_ENEMY_SPEED = 300;
    public static final int NORMAL_ENEMY_ARMOR = 20;
    public static final int NORMAL_ENEMY_REWARD = 100; //Gold ?
    //Spawner Config
    public static final int SPAWNER_WIDTH = SIZE_UNIT;
    public static final int SPAWNER_HEIGHT = SIZE_UNIT;
    public static final int SPAWNER_DEFAULT_POSX = 0;
    public static final int SPAWNER_DEFAULT_POSY = SIZE_TILE_HEIGHT;
    //Target Config
    public static final int TARGET_WIDTH = SIZE_UNIT;
    public static final int TARGET_HEIGHT = SIZE_UNIT;
    public static final int TARGET_DEFAULT_POSX = 9 * SIZE_TILE_WIDTH;
    public static final int TARGET_DEFAULT_POSY = 9 * SIZE_TILE_HEIGHT;
    //Tower Config
    public static final int TOWER_DEFAULT_POSX = 1 * SIZE_TILE_WIDTH;
    public static final int TOWER_DEFAULT_POSY = 4 * SIZE_TILE_HEIGHT;
    public static final int TOWER_WIDTH = SIZE_TILE_WIDTH;
    public static final int TOWER_HEIGHT = SIZE_TILE_HEIGHT;
    public static final double TOWER_SPEED = 0.5; //Attack per Second
    public static final int TOWER_DAMAGE = 20;
    public static final int TOWER_RANGE = SIZE_UNIT;

    //Image
    public static Image IM0, IM1, IM2, IM3, IM4, IM5, IM6, IMEnemy;

    static {

        try {
            IM0 = new Image(new FileInputStream("assets/0.png"));
            IM1 = new Image(new FileInputStream("assets/1.png"));
            IM2 = new Image(new FileInputStream("assets/2.png"));
            IM3 = new Image(new FileInputStream("assets/3.png"));
            IM4 = new Image(new FileInputStream("assets/4.png"));
            IM5 = new Image(new FileInputStream("assets/5.png"));
            IM6 = new Image(new FileInputStream("assets/6.png"));
            IMEnemy = new Image(new FileInputStream("assets/enemy.png"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Make it can not declaration
    private GameConfig() {

    }

}
