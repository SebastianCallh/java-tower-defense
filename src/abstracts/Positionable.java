package abstracts;


import objects.Point;

/**
 * Created by Seba on 2015-02-09.
 */

//TODO: Figure the inheritance hierarchy out.
public abstract class Positionable {
    private Point position;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Positionable(Point position) {
        this.position = position;
    }
}
