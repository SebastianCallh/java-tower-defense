package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.Theme;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
 */
public final class MonsterPainter implements Painter{
    private static MonsterPainter INSTANCE;

    private Monster monster;

    private MonsterPainter() {

    }

    public static MonsterPainter instanceFor(Monster monster) {
        if (INSTANCE == null) {
            INSTANCE = new MonsterPainter();
        }

        INSTANCE.setMonster(monster);
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme) {
        Theme.Element element;
        switch (monster.getType()) {
            case SMALL:
                element = Theme.Element.MONSTER_SMALL;
                break;
            case BIG:
                element = Theme.Element.MONSTER_BIG;
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

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
