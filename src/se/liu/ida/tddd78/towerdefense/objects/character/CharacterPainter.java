package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme.Element;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
 */
public final class CharacterPainter implements Painter {
    private static final CharacterPainter INSTANCE = new CharacterPainter();

    private Character character;

    private CharacterPainter() {
        this.character = null;
    }

    public static Painter instanceFor(Character character) {
        INSTANCE.character = character;
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) throws TypeNotSupportedException {
        Element element;
        switch (this.character.getType()) {
            case PLAYER:
                element = Element.CHARACTER_PLAYER;
                break;
            case SOMEDUDE:
                element = Element.CHARACTER_OTHER;
                break;
            default:
                throw new TypeNotSupportedException("Unrecognized character type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval((int) ((character.getPosition().getX() - character.getSize()) * scale),
                (int) ((character.getPosition().getY() - character.getSize()) * scale),
                character.getSize() * 2 * scale,
                character.getSize() * 2 * scale);
    }

}
