package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme.Element;

import java.awt.*;

/**
 * Handles the painting of all projectiles.
 */
public final class ProjectilePainter implements Painter {
    private static final ProjectilePainter INSTANCE = new ProjectilePainter();

    private Projectile projectile;

    private ProjectilePainter() {
        this.projectile = null;
    }

    public static ProjectilePainter instanceFor(Projectile projectile) {
        INSTANCE.projectile = projectile;
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) {
        Element element;
        switch (this.projectile.getType()) {
            case NORMAL:
                element = Element.PROJECTILE_NORMAL;
                break;
            default:
                throw new RuntimeException("Unrecognized projectile type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillOval((int) ((projectile.getPosition().x - projectile.getSize()) * scale),
                (int) ((projectile.getPosition().y  - projectile.getSize())  * scale),
                projectile.getSize() * 2 * scale,
                projectile.getSize() * 2 * scale);
    }
}
