package townerdefense.entity.tile.map;

import townerdefense.GameConfig;
import townerdefense.TypeOfEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.tile.Mountain;
import townerdefense.entity.tile.Road;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Map {
    int[][] map;
    List<Entity> tileList;

    public Map() {
        //this.map = new int[GameConfig.NUMBER_TILE_IN_HORIZONTAL][GameConfig.NUMBER_TILE_IN_VERTICAL];
        //TODO: load map from something...
        this(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2},
                {0, 2, 2, 2, 2, 2, 2, 0, 0, 0},
        });
    }

    public Map(int[][] map) {
        if (map == null
                || map.length != GameConfig.NUMBER_TILE_IN_VERTICAL
                || map[0].length != GameConfig.NUMBER_TILE_IN_HORIZONTAL) {
            throw new IllegalArgumentException("Not is map in tower defense");
        }

        this.tileList = new ArrayList<>();
        this.map = map;

        for(int i = 0; i < GameConfig.NUMBER_TILE_IN_VERTICAL; i++) {
            for(int j = 0; j < GameConfig.NUMBER_TILE_IN_HORIZONTAL; j++) {
                final int posX = j * GameConfig.SIZE_TILE_WIDTH;
                final int posY = i * GameConfig.SIZE_TILE_HEIGHT;
                final int width = GameConfig.SIZE_TILE_WIDTH;
                final int height = GameConfig.SIZE_TILE_HEIGHT;
                switch (map[i][j]) {
                    case TypeOfEntity.ROAD:
                        this.tileList.add(new Road(posX, posY, width, height));
                        break;
                    case TypeOfEntity.MOUNTAIN:
                        this.tileList.add(new Mountain(posX, posY, width, height));
                        break;
                    case TypeOfEntity.TOWER:
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
