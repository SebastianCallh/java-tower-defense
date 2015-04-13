package se.liu.ida.tddd78.towerdefense.objects.abstracts;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;

/**
 * Base for everything being places on the board
 */
public interface GameObject extends Positionable, Paintable {
    public int getSize();

    public void update(Board board);

    public boolean isRemoved();

    public void setRemoved(boolean removed);

    public Command remove();
}
