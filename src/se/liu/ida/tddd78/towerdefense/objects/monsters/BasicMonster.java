package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-01-24.
 */
public class BasicMonster extends AbstractGameObject implements Monster {
    private int hp;
    private MonsterType type;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public MonsterType getType() {
        return type;
    }

    public BasicMonster(int hp, int size, MonsterType type) {
        super(new Point(0,0), new Dimension(size, size));
        this.hp = hp;
        this.type = type;
    }

    @Override
    public Painter getPainter() {
        return MonsterPainter.instanceFor(this);
    }
}
