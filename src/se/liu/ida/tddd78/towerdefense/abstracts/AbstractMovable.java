package se.liu.ida.tddd78.towerdefense.abstracts;

import se.liu.ida.tddd78.towerdefense.interfaces.Movable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Direction;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.basic.VerticalDirection;

/**
 * Created by Seba on 2015-03-04.
 */
public abstract class AbstractMovable extends AbstractGameObject implements Movable {
    private int speed;

    public AbstractMovable(Point position, int size, int speed) {
        super(position, size);
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move(Direction direction) {
        Point pos = this.getPosition();
        int xModifier = (direction == Direction.NORTHWEST ||
                        direction == Direction.WEST ||
                        direction == Direction.SOUTHWEST) ? -1:
                        (direction == Direction.NORTHEAST||
                        direction == Direction.EAST||
                        direction == Direction.SOUTHEAST) ? 1 : 0;

        int yModifier = (direction == Direction.NORTH ||
                        direction == Direction.NORTHEAST ||
                        direction == Direction.NORTHWEST) ? -1:
                        (direction == Direction.SOUTH||
                        direction == Direction.SOUTHWEST||
                        direction == Direction.SOUTHEAST) ? 1 : 0;

        int speed = (xModifier != 0 && yModifier != 0) ?
                this.getSpeed() / 2: this.getSpeed();

        this.setPosition(pos.x + (xModifier * speed), pos.y + (yModifier * speed));
    }
}