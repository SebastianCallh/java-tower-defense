package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;

import java.awt.*;

/**
 * Handles painting all defenses.
 */
public final class DefensePainter implements Painter {
    private static DefensePainter INSTANCE = new DefensePainter();

    private Defense defense;

    private DefensePainter() {

    }

    public static DefensePainter instanceFor(Defense defense) {
        INSTANCE.setDefense(defense);
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) {
        Theme.Element element;
        switch (this.defense.getType()) {
            case SMALL:
                element = Theme.Element.DEFENSE_SMALL;
                break;
            case BIG:
                element = Theme.Element.DEFENSE_BIG;
                break;
            default:
                throw new RuntimeException("Unrecognized defense type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval((int) ((defense.getPosition().x - defense.getSize()) * scale),
                (int) ((defense.getPosition().y - defense.getSize()) * scale),
                defense.getSize() * 2 * scale,
                defense.getSize() * 2 * scale);
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }
}
