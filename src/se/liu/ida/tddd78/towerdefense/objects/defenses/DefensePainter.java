package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.Theme;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
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
    public void paint(Graphics2D g2d, Theme theme) {
        Theme.Element element;
        switch (defense.getType()) {
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
        g2d.fillOval((int)defense.getPosition().x - defense.getSize(),
                (int)defense.getPosition().y - defense.getSize(),
                defense.getSize() * 2,
                defense.getSize() * 2);
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }
}
