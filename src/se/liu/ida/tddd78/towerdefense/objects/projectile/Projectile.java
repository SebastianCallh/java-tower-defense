package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;

/**
 * Represents a projectile that can be fired from a defense upon a monster.
 */
public interface Projectile extends GameObject, Movable {

    public int getDamage();

    public void setDamage(int damage);

    public void setTarget(Monster target);

    public Monster getTarget();

    public int getSpeed();

    public ProjectileType getType();

}
