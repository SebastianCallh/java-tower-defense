package se.liu.ida.tddd78.towerdefense.objects.monsters;

import com.sun.org.apache.bcel.internal.generic.ObjectType;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.GameObjectType;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seba on 2015-01-24.
 */
public class BasicMonster extends GameObject implements Monster {
    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public BasicMonster(int hp, int size, GameObjectType type) {
        super(new Point(0,0), new Dimension(size, size), type);
        this.hp = hp;
    }

    @Override
    public void paint(Graphics2D g2d) {

    }
}
