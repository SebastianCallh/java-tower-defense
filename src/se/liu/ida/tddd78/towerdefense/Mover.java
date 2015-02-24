package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

public class Mover {
    private Board board;

    public Mover(final Board board) {
	this.board = board;
    }

    public void move() {
        for (Monster monster : this.board.getGameObjects().getMonsters()) {
            Point position = monster.getPosition();
            Tile current = this.board.getTile(Math.floorDiv(position.x, Tile.TILE_SIZE),
                                           Math.floorDiv(position.y, Tile.TILE_SIZE));

            Tile next = this.board.getPath().get(current);
            double angle = Math.atan2(next.getPosition().y - monster.getPosition().y,
                                      next.getPosition().x - monster.getPosition().x);
            int x = (int)(monster.getPosition().x + Math.cos(angle) * monster.getSpeed());
            int y = (int)(monster.getPosition().y + Math.sin(angle) * monster.getSpeed());
            monster.setPosition(x, y);
        }
    }
}
