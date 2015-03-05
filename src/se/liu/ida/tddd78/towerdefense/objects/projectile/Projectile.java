package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.interfaces.Movable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;

public interface Projectile extends GameObject, Movable {

    public int getDamage();

    public void setDamage(int damage);

    public void setTarget(Monster target);

    public Monster getTarget();

    public int getSpeed();

    public ProjectileType getType();

}
