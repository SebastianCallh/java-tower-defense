package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.GameObjectType;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public class BasicDefense extends GameObject implements Defense {
    private DefenseType type;
    public BasicDefense(Point position, Dimension size, DefenseType type) {
        super(position, size, GameObjectType.DEFENSE);
        this.type = type;
    }

    @Override
    public void paint(Graphics2D g2d) {

    }
}
