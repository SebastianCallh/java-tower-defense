package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

import java.awt.*;

public class BasicProjectile extends AbstractGameObject implements Projectile {
    private int damage;
    private ProjectileType type;
    private Monster target;
    private int speed;

    public BasicProjectile(int damage, int size, int speed, ProjectileType type) {
        super(new Point(0, 0), size);
        this.damage = damage;
        this.speed = speed;
        this.type = type;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }

    @Override
    public Monster getTarget() {
        return this.target;
    }

    @Override
    public int getSpeed() {
        return this.speed;
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
