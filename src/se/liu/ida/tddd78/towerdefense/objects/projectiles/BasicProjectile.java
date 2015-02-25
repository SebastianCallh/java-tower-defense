package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

import java.awt.*;

public class BasicProjectile extends AbstractGameObject implements Projectile {
    private int damage;
    private ProjectileType type;
    private Point target;
    private int speed;
    private double direction;

    public BasicProjectile(int damage, int size, ProjectileType type) {
        super(new Point(0, 0), new Dimension(size, size));
        this.damage = damage;
        this.type = type;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    @Override
    public Point getTarget() {
        return this.target;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public ProjectileType getType() {
        return type;
    }

    @Override
    public Painter getPainter() {
        return ProjectilePainter.instanceFor(this);
    }

    @Override public void update() {

    }
}
