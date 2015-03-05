package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.HorizontalDirection;
import se.liu.ida.tddd78.towerdefense.objects.basic.VerticalDirection;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-03-03.
 */
public class BasicCharacter extends AbstractMovable implements Character {
    private CharacterType type;
    private HorizontalDirection horizontalDir;
    private VerticalDirection verticalDir;

    public BasicCharacter(Point position, int size, int speed, CharacterType type) {
        super(position, size, speed);
        this.type = type;
    }

    @Override public CharacterType getType() {
        return this.type;
    }

    @Override
    public void setHorizontalDirection(HorizontalDirection direction) {
        this.horizontalDir = direction;
    }

    @Override
    public void setVerticalDirection(VerticalDirection direction) {
        this.verticalDir = direction;
    }

    @Override
    public Painter getPainter() {
        return CharacterPainter.instanceFor(this);
    }

    @Override
    public void update(Board board) {
        int horizontalSpeed = 0;
        int verticalSpeed = 0;

        if (this.horizontalDir == HorizontalDirection.LEFT) {
            horizontalSpeed = -this.getSpeed();
        } else if (this.horizontalDir == HorizontalDirection.RIGHT) {
            horizontalSpeed = this.getSpeed();
        }

        if (this.verticalDir == VerticalDirection.DOWN) {
            verticalSpeed = this.getSpeed();
        } else if (this.verticalDir == VerticalDirection.UP) {
            verticalSpeed = -this.getSpeed();
        }

        if (Math.abs(horizontalSpeed) > 0 && Math.abs(verticalSpeed) > 0) {
            horizontalSpeed /= 2;
            verticalSpeed /= 2;
        }

        this.move(horizontalSpeed, verticalSpeed);
    }
}