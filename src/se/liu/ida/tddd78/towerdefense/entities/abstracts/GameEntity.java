package se.liu.ida.tddd78.towerdefense.entities.abstracts;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

/**
 * Base for everything being placed on the board
 */
public interface GameEntity {
    int getSize();

    void update(Board board);

    boolean isRemoved();

    void setRemoved(boolean removed);

    Command remove();

    ThemeableType getType();

    Point getPosition();

    void setPosition(double x, double y);

    void setPosition(Point position);
}
