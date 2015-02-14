package se.liu.ida.tddd78.towerdefense.objects;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seba on 2015-01-23.
 */

public class Theme {
    private Map<GameObjectType, Color> gameObjectStyleMap = new HashMap<GameObjectType, Color>();
    private Map<TileType, Color> tileStyleMap = new HashMap<TileType, Color>();
    private Map<Element, Color> elementStyleMap = new HashMap<Element, Color>();

    public Theme(ThemeType type) {
        switch(type) {
            case GREEN_IS_GOOD:
                this.elementStyleMap.put(Element.TILE_PATH, new Color(50, 160, 50));
                this.elementStyleMap.put(Element.TILE_BLOCKED, new Color(50, 70, 50));
                this.elementStyleMap.put(Element.TILE_SPAWN, new Color(150, 10, 10));
                this.elementStyleMap.put(Element.TILE_GOAL, new Color(150, 10, 150));
                this.elementStyleMap.put(Element.MONSTER_BIG, new Color(150, 10, 10));
                this.elementStyleMap.put(Element.MONSTER_SMALL, new Color(110, 10, 10));
                this.elementStyleMap.put(Element.DEFENSE_NORMAL, new Color(60, 10, 10));
                break;
            case BLACK_AND_WHITE:
                this.elementStyleMap.put(Element.TILE_PATH, new Color(255, 255, 255));
                this.elementStyleMap.put(Element.TILE_BLOCKED, new Color(50, 70, 50));
                this.elementStyleMap.put(Element.TILE_SPAWN, new Color(150, 10, 150));
                this.elementStyleMap.put(Element.TILE_GOAL, new Color(150, 10, 150));
                this.elementStyleMap.put(Element.MONSTER_BIG, new Color(50, 150, 150));
                this.elementStyleMap.put(Element.MONSTER_SMALL, new Color(50, 110, 110));
                this.elementStyleMap.put(Element.DEFENSE_NORMAL, new Color(0, 10, 10));
                break;
            default:
                throw new IllegalArgumentException("Invalid theme type");
        }
    }

    public Color getStyle(Element element) {
        return this.elementStyleMap.get(element);
    }

    public enum Element {
        TILE_PATH,
        TILE_BLOCKED,
        TILE_SPAWN,
        TILE_GOAL,
        MONSTER_BIG,
        MONSTER_SMALL,
        DEFENSE_NORMAL
    }

}
