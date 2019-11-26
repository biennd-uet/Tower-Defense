package townerdefense.engine.entity.tile.map;

import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.TypeOfEntity;
import townerdefense.engine.entity.tile.Mountain;
import townerdefense.engine.entity.tile.Road;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Map implements Serializable {
    private final static String urlToMapFolder = "/map/";
    private List<Entity> tileList;
    private final int[][] map;
    private final WayPoint wayPoint;

    public Map() throws IOException, URISyntaxException {
        this(loadMapFromFile(Map.class.getResource("/map/Stage1.txt")));
    }

    private Map(int[][] map) {
        if (map == null
                || map.length != GameConfig.NUMBER_TILE_IN_VERTICAL
                || map[0].length != GameConfig.NUMBER_TILE_IN_HORIZONTAL) {
            throw new IllegalArgumentException("Not is map in tower defense");
        }

        this.map = map;
        this.wayPoint = new WayPoint();
        this.tileList = new ArrayList<>();

        for (int i = 0; i < GameConfig.NUMBER_TILE_IN_VERTICAL; i++) {
            for (int j = 0; j < GameConfig.NUMBER_TILE_IN_HORIZONTAL; j++) {
                final int posX = j * GameConfig.SIZE_TILE_WIDTH;
                final int posY = i * GameConfig.SIZE_TILE_HEIGHT;
                final int width = GameConfig.SIZE_TILE_WIDTH;
                final int height = GameConfig.SIZE_TILE_HEIGHT;
                switch (Objects.requireNonNull(TypeOfEntity.getTypeOfEntityByType(map[i][j]))) {
                    case MOUNTAIN:
                        this.tileList.add(new Mountain(posX, posY, width, height));
                        break;
                    case ROAD0:
                    case ROAD2:
                    case ROAD3:
                    case ROAD4:
                    case ROAD5:
                        this.tileList.add(new Road(posX, posY, width, height));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static int[][] loadMapFromFile(URL url) throws URISyntaxException, IOException {
        File inputMap = new File(url.toURI());
        FileReader fileReader = new FileReader(inputMap);

        BufferedReader reader = new BufferedReader(fileReader);

        int[] data = Stream.of(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int column = data[0];
        int row = data[1];

        int[][] map = new int[row][column];
        for (int i = 0; i < row; i++) {
            data = Stream.of(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            map[i] = data;
        }

        reader.close();

        System.out.println("Load map finished...");

        return map;
    }

    public int[][] getMap() {
        return map;
    }

    public Collection<Entity> getListTile() {
        return this.tileList;
    }

    public WayPoint getWayPoint() {
        return wayPoint;
    }
}
