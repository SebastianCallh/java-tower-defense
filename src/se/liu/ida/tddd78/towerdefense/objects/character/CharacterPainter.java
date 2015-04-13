package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;

import java.awt.*;

/**
 * Contains methods for painting characters.
 */
public final class CharacterPainter implements Painter {
    private static CharacterPainter INSTANCE = new CharacterPainter();

    private Character character;

    private CharacterPainter() {

    }

    public static CharacterPainter instanceFor(Character character) {
        INSTANCE.setCharacter(character);
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) {
        Theme.Element element;
        switch (this.character.getType()) {
            case PLAYER:
                element = Theme.Element.CHARACTER_PLAYER;
                break;
            case SOMEDUDE:
                element = Theme.Element.CHARACTER_OTHER;
                break;
            default:
                throw new RuntimeException("Unrecognized character type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval((int) ((character.getPosition().x - character.getSize()) * scale),
                (int) ((character.getPosition().y - character.getSize()) * scale),
                character.getSize() * 2 * scale,
                character.getSize() * 2 * scale);
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
