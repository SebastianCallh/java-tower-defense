package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Basic implementation of a character that can be placed upon the board.
 */
public class BasicCharacter extends AbstractMovable implements Character {
    private CharacterType type;

    public BasicCharacter(Point position, int size, int speed, CharacterType type) {
        super(position, size, speed);
        this.type = type;
    }

    @Override public CharacterType getType() {
        return this.type;
    }

    @Override
    public void update(Board board) {

    }

    @Override
    public Command remove() {
        return null;
    }
}