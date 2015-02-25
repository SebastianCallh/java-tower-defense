package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

public class MonsterMover {
    private Board board;

    public MonsterMover(final Board board) {
        this.board = board;
    }

    public void move() {
        for (Monster monster : this.board.getGameObjects().getMonsters()) {
            Point position = monster.getPosition();
            Tile current = board.getTileUnderObject(monster);
            Tile next = this.board.getPath().get(current);

            double angle = Math.atan2(next.getCenter().y - position.y,
                                      next.getCenter().x - position.x);

            monster.setPosition(position.x + Math.cos(angle) * monster.getSpeed(),
                                position.y + Math.sin(angle) * monster.getSpeed());
        }
    }
}
