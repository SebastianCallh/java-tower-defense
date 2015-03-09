package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-03-03.
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
    public Painter getPainter() {
        return CharacterPainter.instanceFor(this);
    }

    @Override
    public void update(Board board) {

    }

    @Override
    public Command getOnRemovedCommand() {
        return null;
    }
}