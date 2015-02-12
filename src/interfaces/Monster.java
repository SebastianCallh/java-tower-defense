package interfaces;

import objects.BasicMonster;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster {
    public BasicMonster.Type getType();

    public int getHp();

    public int getSize();

    public void setHp(int hp);
}
