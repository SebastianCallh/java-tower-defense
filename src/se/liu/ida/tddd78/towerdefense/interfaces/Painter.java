package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
 */
public interface Painter {
    void paint(Graphics2D g2d, Theme theme, int scale) throws TypeNotSupportedException;
}
