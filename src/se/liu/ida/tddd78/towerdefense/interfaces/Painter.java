package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;

import java.awt.*;

/**
 * Allows implementing class to paint on a component.
 */
public interface Painter {
    public void paint(Graphics2D g2d, Theme theme, int scale);
}
