package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.Projectile;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileFactory;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public class BasicDefense extends AbstractGameObject implements Defense {
    private DefenseType type;
    private int range;
    private int damage;
    private long attackSpeed;
    private Monster target;
    private Timer cooldownTimer;

    @Override public DefenseType getType() {
        return type;
    }

    @Override public Monster getTarget() {
        return this.target;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public Projectile getProjectile() {
        Projectile projectile = ProjectileFactory.makeProjectile(ProjectileType.NORMAL);
        projectile.setDamage(this.damage);
        projectile.setPosition(this.getPosition().x, this.getPosition().y);
        projectile.setTarget(this.getTarget());
        return projectile;
    }

    @Override
    public void coolDown() {
        this.cooldownTimer.reset(this.attackSpeed);
    }

    @Override public void setTarget(final Monster target) {
        this.target = target;
    }

    @Override public boolean isCoolingDown() {
        return !this.cooldownTimer.hasCompleted();
    }

    public BasicDefense(int size, DefenseType type, int range, int damage, long attackSpeed) {
        super(new Point(0,0), size);
        this.type = type;
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.cooldownTimer = new Timer(this.attackSpeed);
    }

    @Override public void update() {
    }

    @Override
    public Painter getPainter() {
        return DefensePainter.instanceFor(this);
    }
}
