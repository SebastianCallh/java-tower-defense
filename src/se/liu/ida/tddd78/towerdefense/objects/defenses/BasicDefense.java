package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public class BasicDefense extends AbstractGameObject implements Defense {
    private DefenseType type;
    public BasicDefense(Point position, Dimension size, DefenseType type) {
        super(position, size);
        this.type = type;
    }

    @Override
    public Painter getPainter() {
        return null;
    }
}
