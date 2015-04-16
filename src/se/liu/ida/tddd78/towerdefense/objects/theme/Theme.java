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
public final class Theme {
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

        public Theme build() {
            return theme;
        }

    }

}
