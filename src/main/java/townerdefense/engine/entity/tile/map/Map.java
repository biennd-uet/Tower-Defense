package townerdefense.engine.entity.tile.map;

import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.TypeOfEntity;
import townerdefense.engine.entity.tile.Road;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Map {
    private final static String urlToMapFolder = "/map/";
    public static int[][] map;
    private List<Entity> tileList;

    public Map() throws IOException, URISyntaxException {
        //this.map = new int[GameConfig.NUMBER_TILE_IN_HORIZONTAL][GameConfig.NUMBER_TILE_IN_VERTICAL];

        //TODO: load map from something...
        this(loadMapFromFile(Map.class.getResource("/map/Stage1.txt")));
    }

    private Map(int[][] map) {
        if (map == null
                || map.length != GameConfig.NUMBER_TILE_IN_VERTICAL
                || map[0].length != GameConfig.NUMBER_TILE_IN_HORIZONTAL) {
            throw new IllegalArgumentException("Not is map in tower defense");
        }

        this.tileList = new ArrayList<>();
        Map.map = map;

        for (int i = 0; i < GameConfig.NUMBER_TILE_IN_VERTICAL; i++) {
            for (int j = 0; j < GameConfig.NUMBER_TILE_IN_HORIZONTAL; j++) {
                final int posX = j * GameConfig.SIZE_TILE_WIDTH;
                final int posY = i * GameConfig.SIZE_TILE_HEIGHT;
                final int width = GameConfig.SIZE_TILE_WIDTH;
                final int height = GameConfig.SIZE_TILE_HEIGHT;
                switch (Objects.requireNonNull(TypeOfEntity.getTypeOfEntityByType(map[i][j]))) {
                    case ROAD6:
                        this.tileList.add(new Road(GameConfig.IM6, posX, posY, width, height));
                        break;
                    case ROAD0:
                    case ROAD2:
                    case ROAD3:
                    case ROAD4:
                    case ROAD5:
                        this.tileList.add(new Road(GameConfig.IM0, posX, posY, width, height));
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

    public Collection<Entity> getListTile() {
        return this.tileList;
    }
}
