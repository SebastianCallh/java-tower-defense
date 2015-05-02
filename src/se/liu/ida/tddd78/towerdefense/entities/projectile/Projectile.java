package se.liu.ida.tddd78.towerdefense.entities.projectile;

import se.liu.ida.tddd78.towerdefense.entities.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.entities.monster.Monster;

/**
 * Represents a projectile that can be fired from a defense upon a monster.
 */
public interface Projectile extends Movable {

    void setDamage(int damage);

    void setTarget(Monster target);

    ProjectileType getType();

}
