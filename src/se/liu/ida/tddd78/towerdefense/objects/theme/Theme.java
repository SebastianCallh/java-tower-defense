package se.liu.ida.tddd78.towerdefense.objects.theme;

import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the appearance of the board and the game objects.
 */

@SuppressWarnings("MagicNumber")
public class Theme {
    private Map<Element, Color> elementStyleMap = new EnumMap<>(Element.class);
    private Map<ThemeableType, Color> elementColorMap = new HashMap<>();
    private Map<ThemeableType, Image> elementSpriteMap = new HashMap<>();
    private Image placeholder;

    private Theme() {
        this.placeholder = createPlaceholder();
    }

    private Image createPlaceholder() {
        Image image = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, 40, 40);

        return image;
    }

    public Image getSprite(ThemeableType element) {
        if (!elementSpriteMap.containsKey(element)) {
            return placeholder;
        } else {
            return elementSpriteMap.get(element);
        }
    }

    public enum Element {
        TILE_PATH,
        TILE_BLOCKED,
        TILE_SPAWN,
        TILE_GOAL,
        CHARACTER_PLAYER,
        CHARACTER_OTHER,
        MONSTER_BIG,
        MONSTER_SMALL,
        PROJECTILE_NORMAL,
        DEFENSE_BIG,
        DEFENSE_SMALL
    }

    protected static class ThemeFactory {
        private Theme theme;

        public ThemeFactory() {
            this.theme = new Theme();
        }

        public ThemeFactory addSpriteMapping(ThemeableType type, Image image) {
            assert type != null;
            theme.elementSpriteMap.put(type, image);

            return this;
        }

        public ThemeFactory addColorMapping(ThemeableType type, Color color) {
            assert type != null;
            theme.elementColorMap.put(type, color);

            return this;
        }

        public Theme build() {
            return theme;
        }

    }

}
