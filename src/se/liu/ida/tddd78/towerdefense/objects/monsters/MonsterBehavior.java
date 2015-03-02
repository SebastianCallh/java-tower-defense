package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Collision;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;

/**
 * Created by Seba on 2015-03-01.
 */
public class MonsterBehavior {
    private Board board;

    public MonsterBehavior(Board board) {
        this.board = board;
    }

    public void behave() {
        for (Monster monster : this.board.getGameObjects().getMonsters()) {
            if (!monster.isAlive()) {
                monster.setRemoved(true);
            }
        }
    }
}
