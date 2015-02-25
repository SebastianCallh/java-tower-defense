package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Defense extends GameObject {
    public DefenseType getType();

    public Monster getTarget();

    public void setTarget(Monster monster);

    public boolean isCoolingDown();
}

