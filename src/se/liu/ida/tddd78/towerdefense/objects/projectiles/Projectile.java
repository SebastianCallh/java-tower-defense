package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.interfaces.Moveable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

public interface Projectile extends GameObject, Moveable {

    public int getDamage();

    public void setDamage(int damage);

    public void setTarget(Point target);

    public Point getTarget();

    public int getSpeed();

    public ProjectileType getType();

}
