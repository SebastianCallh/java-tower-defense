package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme.Element;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
 */
public final class DefensePainter implements Painter {
    private static final DefensePainter INSTANCE = new DefensePainter();

    private Defense defense;

    private DefensePainter() {
        this.defense = null;
    }

    public static Painter instanceFor(Defense defense) {
        INSTANCE.defense = defense;
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) throws TypeNotSupportedException {
        Element element;
        switch (this.defense.getType()) {
            case SMALL:
                element = Element.DEFENSE_SMALL;
                break;
            case BIG:
                element = Element.DEFENSE_BIG;
                break;
            case FAST:
            default:
                throw new TypeNotSupportedException("Unrecognized defense type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval((int) ((defense.getPosition().getX() - defense.getSize()) * scale),
                (int) ((defense.getPosition().getY() - defense.getSize()) * scale),
                defense.getSize() * 2 * scale,
                defense.getSize() * 2 * scale);
    }

}
