package townerdefense.engine.entity.tile.map;

import townerdefense.engine.GameConfig;
import townerdefense.engine.TypeOfEntity;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.tile.Road;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Map {
    public static int[][] map;
    List<Entity> tileList;

    public Map() {
        //this.map = new int[GameConfig.NUMBER_TILE_IN_HORIZONTAL][GameConfig.NUMBER_TILE_IN_VERTICAL];
        //TODO: load map from something...
        this(new int[][] {
                { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 6, 6, 6, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 6, 6, 6},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6},
                { 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 4, 0, 0, 0, 0, 0, 3, 0, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6},
                { 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0},
                { 6, 6, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 6, 6, 6, 6, 6, 6, 6, 0, 5, 0, 0, 0, 0},
                { 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0},
                { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
        });
    }

    public Map(int[][] map) {
        if(map == null
                || map.length != GameConfig.NUMBER_TILE_IN_VERTICAL
                || map[0].length != GameConfig.NUMBER_TILE_IN_HORIZONTAL) {
            throw new IllegalArgumentException("Not is map in tower defense");
        }

        this.tileList = new ArrayList<>();
        Map.map = map;

        for(int i = 0; i < GameConfig.NUMBER_TILE_IN_VERTICAL; i++) {
            for(int j = 0; j < GameConfig.NUMBER_TILE_IN_HORIZONTAL; j++) {
                final int posX = j * GameConfig.SIZE_TILE_WIDTH;
                final int posY = i * GameConfig.SIZE_TILE_HEIGHT;
                final int width = GameConfig.SIZE_TILE_WIDTH;
                final int height = GameConfig.SIZE_TILE_HEIGHT;
                switch (map[i][j]) {
                   
                    case TypeOfEntity.ROAD6:
                        this.tileList.add(new Road(GameConfig.IM6, posX, posY, width, height));
                        break;
                    case TypeOfEntity.ROAD0:case  2: case 3: case 4: case 5:
                        this.tileList.add(new Road(GameConfig.IM0, posX, posY, width, height));
                        break;
                   
                    default:
                        break;
                }
            }
        }
    }

    public Collection<Entity> getListTile() {
        return this.tileList;
    }
}
