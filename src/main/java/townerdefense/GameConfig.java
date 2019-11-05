package townerdefense;

public class GameConfig {
    //Make it can not declaration
    private GameConfig(){

    }
    //TODO: All settings
    //Size of Screen
    public static final int SCREEN_WIDTH = 720;
    public static final int SCREEN_HEIGHT = 720;
    //Name of Game
    public static final String GAME_TITLE = "Tower Defense";
    //Number of tile in
    public static final int NUMBER_TILE_IN_HORIZONTAL = 10;
    public static final int NUMBER_TILE_IN_VERTICAL = 10;
    //Size of Tile
    public static final int SIZE_TILE_WIDTH = SCREEN_WIDTH / NUMBER_TILE_IN_HORIZONTAL;
    public static final int SIZE_TILE_HEIGHT = SCREEN_HEIGHT / NUMBER_TILE_IN_VERTICAL;

}
