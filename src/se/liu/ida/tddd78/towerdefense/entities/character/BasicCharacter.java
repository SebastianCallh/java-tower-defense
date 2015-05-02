package se.liu.ida.tddd78.towerdefense.entities.character;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.entities.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;

/**
 * Basic implementation of a character that can be placed upon the board.
 */
public class BasicCharacter extends AbstractMovable implements Character {
    private CharacterType type;

    public BasicCharacter(Point position, int size, int speed, CharacterType type) {
        super(position, size, speed);
        assert size > 0;
        assert speed > 0;

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