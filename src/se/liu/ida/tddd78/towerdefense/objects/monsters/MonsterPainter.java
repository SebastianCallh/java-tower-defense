package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.Theme;
import se.liu.ida.tddd78.towerdefense.objects.Theme.Element;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
 */
public final class MonsterPainter implements Painter{
    private static final MonsterPainter INSTANCE = new MonsterPainter();

    private Monster monster;

    private MonsterPainter() {
        this.monster = null;
    }

    public static MonsterPainter instanceFor(Monster monster) {
        INSTANCE.monster = monster;
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme) {
        Element element;
        switch (monster.getType()) {
            case SMALL:
                element = Element.MONSTER_SMALL;
                break;
            case BIG:
                element = Element.MONSTER_BIG;
                break;
            default:
                throw new RuntimeException("Unrecognized monster type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval(monster.getPosition().x,
                monster.getPosition().y,
                monster.getSize().width,
                monster.getSize().height);
    }

}
