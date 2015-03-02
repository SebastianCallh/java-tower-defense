package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.interfaces.Moveable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

public interface Projectile extends GameObject, Moveable {

    public int getDamage();

    public void setDamage(int damage);

    public void setTarget(Monster target);

    public Monster getTarget();

    public int getSpeed();

    public ProjectileType getType();

}
