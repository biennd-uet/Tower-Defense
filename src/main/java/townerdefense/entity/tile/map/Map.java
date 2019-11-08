package townerdefense.entity.tile;

import townerdefense.GameConfig;
import townerdefense.TypeOfEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.tile.Mountain;
import townerdefense.entity.tile.Road;

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
                {6,6,6,6,6,6,6,6,6,6},
                {0,0,0,0,0,0,0,0,0,2},
                {6,6,6,6,6,6,6,6,6,1},
                {4,0,0,0,0,0,0,0,0,3},
                {1,6,6,6,6,6,6,6,6,6},
                {5,0,0,0,0,0,0,0,0,2},
                {6,6,6,6,6,6,6,6,6,1},
                {4,0,0,0,0,0,0,0,0,3},
                {1,6,6,6,6,6,6,6,6,6},
                {5,0,0,0,0,0,0,0,0,0}
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
                    case TypeOfEntity.ROAD0:
                        this.tileList.add(new Road(GameConfig.IM0,posX, posY, width, height));
                        break;
                    case TypeOfEntity.ROAD1:
                        this.tileList.add(new Road(GameConfig.IM1,posX, posY, width, height));
                        break;
                    case TypeOfEntity.ROAD2:
                        this.tileList.add(new Road(GameConfig.IM2,posX, posY, width, height));
                        break;
                    case  TypeOfEntity.ROAD3:
                        this.tileList.add(new Road(GameConfig.IM3,posX, posY, width, height));
                        break;
                    case  TypeOfEntity.ROAD4:
                        this.tileList.add(new Road(GameConfig.IM4,posX, posY, width, height));
                        break;
                    case  TypeOfEntity.ROAD5:
                        this.tileList.add(new Road(GameConfig.IM5,posX, posY, width, height));
                        break;
                    case  TypeOfEntity.ROAD6:
                        this.tileList.add(new Road(GameConfig.IM6,posX, posY, width, height));
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
