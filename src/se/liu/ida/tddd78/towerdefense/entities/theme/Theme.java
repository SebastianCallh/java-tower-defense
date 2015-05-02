package se.liu.ida.tddd78.towerdefense.entities.theme;

import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the appearance of the board and the game entities.
 */

public final class Theme {
    private String name;
    private Map<ThemeableType, Image> elementSpriteMap = new HashMap<>();
    private Image placeholder;

    private Theme(String name) {
        this.name = name;
        this.placeholder = createPlaceholder();
    }
    private final static int PLACEHOLDER_SIZE = 40;

    private Image createPlaceholder() {
        Image image = new BufferedImage(PLACEHOLDER_SIZE, PLACEHOLDER_SIZE , BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, PLACEHOLDER_SIZE, PLACEHOLDER_SIZE);

        return image;
    }

    public String getName() {
        return this.name;
    }

    public Image getSprite(ThemeableType element) {
        if (!elementSpriteMap.containsKey(element)) {
            return placeholder;
        } else {
            return elementSpriteMap.get(element);
        }
    }

    protected static class ThemeFactory {
        private Theme theme;

        public ThemeFactory(String name) {
            assert name != null && !name.isEmpty();

            this.theme = new Theme(name);
        }

        public ThemeFactory addSpriteMapping(ThemeableType type, Image image) {
            assert type != null;
            theme.elementSpriteMap.put(type, image);

            return this;
        }

        public Theme build() {
            return theme;
        }

    }
}
