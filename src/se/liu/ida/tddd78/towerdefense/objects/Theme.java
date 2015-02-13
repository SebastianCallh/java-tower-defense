package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.objects.GameObjectType;
import se.liu.ida.tddd78.towerdefense.objects.defenses.Defense;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seba on 2015-01-23.
 */

public class Theme {
    private Map<GameObjectType, Color> gameObjectStyleMap = new HashMap<GameObjectType, Color>();
    private Map<TileType, Color> tileStyleMap = new HashMap<TileType, Color>();

    public Theme(ThemeType type) {
        switch(type) {
            case GREEN_IS_GOOD:
                this.tileStyleMap.put(TileType.PATH, new Color(50, 160, 50));
                this.tileStyleMap.put(TileType.BLOCKED, new Color(50, 70, 50));
                this.tileStyleMap.put(TileType.SPAWN, new Color(150, 10, 10));
                this.tileStyleMap.put(TileType.GOAL, new Color(150, 10, 150));
                this.gameObjectStyleMap.put(GameObjectType.MONSTER_BIG, new Color(150, 10, 10));
                this.gameObjectStyleMap.put(GameObjectType.MONSTER_SMALL, new Color(110, 10, 10));
                this.gameObjectStyleMap.put(GameObjectType.DEFENSE, new Color(60, 10, 10));
                break;
            case BLACK_AND_WHITE:
                this.tileStyleMap.put(TileType.PATH, new Color(255, 255, 255));
                this.tileStyleMap.put(TileType.BLOCKED, new Color(50, 70, 50));
                this.tileStyleMap.put(TileType.SPAWN, new Color(150, 10, 150));
                this.tileStyleMap.put(TileType.GOAL, new Color(150, 10, 150));
                this.gameObjectStyleMap.put(GameObjectType.MONSTER_BIG, new Color(50, 150, 150));
                this.gameObjectStyleMap.put(GameObjectType.MONSTER_SMALL, new Color(50, 110, 110));
                this.gameObjectStyleMap.put(GameObjectType.DEFENSE, new Color(0, 10, 10));
                break;
            default:
                throw new IllegalArgumentException("Invalid theme type");
        }
    }

    public Color getTileStyle(TileType type) {
       return this.tileStyleMap.get(type);
    }

    public Color getGameObjectStyle(GameObjectType type) {
        return this.gameObjectStyleMap.get(type);
    }
}
