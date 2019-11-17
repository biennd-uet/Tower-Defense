package townerdefense.engine;

public class TypeOfEntity {
    //Type of tile
    public static final int ROAD0 = 0;
    public static final int ROAD1 = 1;
    public static final int ROAD2 = 2;
    public static final int ROAD3 = 3;
    public static final int ROAD4 = 4;
    public static final int ROAD5 = 5;
    public static final int ROAD6 = 6;
    public static final int ROAD = 0;
    public static final int TOWER = 7;
    public static final int MOUNTAIN = 8;

    private TypeOfEntity() {

    }

    public static enum TypeOfEnemy {
        NORMAL_ENEMY;
    }
}
