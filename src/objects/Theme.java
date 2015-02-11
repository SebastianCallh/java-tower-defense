package objects;

import java.awt.*;

/**
 * Created by Seba on 2015-01-23.
 */

public class Theme {
    private Color clearStyle;
    private Color blockedStyle;
    private Color spawnStyle;
    private Color goalStyle;
    private Color bigMonsterStyle;
    private Color smallMonsterStyle;

    public enum Type {
        GREEN_IS_GOOD, BLACK_AND_WHITE
    }

    public Theme(Theme.Type type) {
        switch(type) {
            case GREEN_IS_GOOD:
                this.clearStyle = new Color(50, 160, 50);
                this.blockedStyle = new Color(50, 70, 50);
                this.spawnStyle = new Color(150, 10, 10);
                this.blockedStyle = new Color(50, 70, 50);
                this.goalStyle = new Color(150, 10, 150);
                this.bigMonsterStyle = new Color(150, 10, 10);
                this.smallMonsterStyle = new Color(110, 10, 10);
                break;
            case BLACK_AND_WHITE:
                this.clearStyle = new Color(255, 255, 255);
                this.blockedStyle = new Color(0, 0, 0);
                this.blockedStyle = new Color(50, 70, 50);
                this.goalStyle = new Color(150, 10, 150);
                this.bigMonsterStyle = new Color(50, 150, 150);
                this.smallMonsterStyle = new Color(50, 110, 110);
                break;
            default:
                throw new IllegalArgumentException("Invalid theme type");
        }
    }

    public Color getTileStyle(Tile.Type type) {
        switch(type) {
            case PATH:
                return this.clearStyle;
            case BLOCKED:
                return this.blockedStyle;
            case SPAWN:
                return this.spawnStyle;
            case GOAL:
                return this.goalStyle;
            default:
                throw new IllegalArgumentException("Not yet implemented monster type");
        }
    }

    public Color getMonsterStyle(Monster.Type type) {
        switch(type) {
            case BIG:
                return this.bigMonsterStyle;
            case SMALL:
                return this.smallMonsterStyle;
            default:
                throw new IllegalArgumentException("Not yet implemented monster type");
        }
    }
}
