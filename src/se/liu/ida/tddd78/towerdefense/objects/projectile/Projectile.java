package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;

/**
 * Represents a projectile that can be fired from a defense upon a monster.
 */
public interface Projectile extends GameObject, Movable {

    void setDamage(int damage);

    void setTarget(Monster target);

    ProjectileType getType();

}
