package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;
import se.liu.ida.tddd78.towerdefense.objects.projectile.Projectile;

/**
 * Represents a defense on the board.
 */
public interface Defense extends GameObject {
    public DefenseType getType();

    public Monster getTarget();

    public int getRange();

    public int getCost();

    public Projectile getProjectile();

    public void coolDown();

    public void setTarget(Monster monster);

    public boolean isCoolingDown();
}