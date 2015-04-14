package se.liu.ida.tddd78.towerdefense.objects.abstracts;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

/**
 * Base for everything being places on the board
 */
public interface GameObject extends Positionable, Paintable {
    int getSize();

    void update(Board board);

    boolean isRemoved();

    void setRemoved(boolean removed);

    Command remove();

    ThemeableType getType();
}
