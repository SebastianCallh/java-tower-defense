package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme.Element;

import java.awt.*;

/**
 * Handles painting the monsters.
 */
public final class MonsterPainter implements Painter{
    private static final MonsterPainter INSTANCE = new MonsterPainter();

    private Monster monster;

    private MonsterPainter() {
        this.monster = null;
    }

    public static Painter instanceFor(Monster monster) {
        INSTANCE.monster = monster;
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) throws TypeNotSupportedException {
        Element element;
        switch (this.monster.getType()) {
            case SMALL:
                element = Element.MONSTER_SMALL;
                break;
            case BIG:
                element = Element.MONSTER_BIG;
                break;
            default:
                throw new TypeNotSupportedException("Unrecognized monster type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval((int) ((monster.getPosition().getX() - monster.getSize()) * scale),
                (int) ((monster.getPosition().getY() - monster.getSize()) * scale),
                monster.getSize() * 2 * scale,
                monster.getSize() * 2 * scale);
    }
}
