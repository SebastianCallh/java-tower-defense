package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public class BasicDefense extends AbstractGameObject implements Defense {
    private DefenseType type;
    private int range;
    private double attackSpeed;
    private Monster target;

    @Override public DefenseType getType() {
        return type;
    }

    @Override public Monster getTarget() {
        return this.target;
    }

    @Override public void setTarget(final Monster monster) {

    }

    public BasicDefense(Dimension size, DefenseType type, int range, double attackSpeed) {
        super(new Point(0,0), size);
        this.type = type;
        this.range = range;
        this.attackSpeed = attackSpeed;
    }

    @Override
    public Painter getPainter() {
        return DefensePainter.instanceFor(this);
    }

    @Override public void update() {

    }
}
