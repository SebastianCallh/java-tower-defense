package se.liu.ida.tddd78.towerdefense.utils;

import java.awt.*;

/**
 * Handles scaling the graphics for different sized monitors
 */
// TODO: Remove?
public final class GraphicsUtil {
    private static final int SCREEN_WIDTH_LARGEST = 4000;
    private static final int SCREEN_WIDTH_LARGER = 3200;
    private static final int SCREEN_WIDTH_LARGE = 2560;

    private static int scale = 0;

    private GraphicsUtil() {

    }

    public static int getScale() {
        if (scale == 0) {
            scale = calculateScale();
        }

        return scale;
    }

    private static int calculateScale() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        switch (screenWidth) {
            case SCREEN_WIDTH_LARGEST:
                return 4;
            case SCREEN_WIDTH_LARGER:
                return 3;
            case SCREEN_WIDTH_LARGE:
                return 2;
            default:
                return 1;
        }
    }

}
