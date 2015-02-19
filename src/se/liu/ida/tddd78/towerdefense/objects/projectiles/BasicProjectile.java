package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

public class BasicProjectile extends AbstractGameObject implements Projectile {
    private int damage;
    private ProjectileType type;

    public BasicProjectile(int damage, int size, ProjectileType type) {
        super(new Point(0, 0), new Dimension(size, size));
        this.damage = damage;
        this.type = type;
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

}
