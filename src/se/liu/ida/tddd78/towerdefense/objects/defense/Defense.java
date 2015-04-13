package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.projectile.Projectile;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Defense extends GameObject {
    DefenseType getType();

    int getCost();

    Projectile getProjectile();

    void coolDown();

    boolean isCoolingDown();
}