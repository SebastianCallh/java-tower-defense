package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster extends GameObject {
    public int getHp();

    public void setHp(int hp);

    public MonsterType getType();
}
