package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;
import se.liu.ida.tddd78.towerdefense.objects.monsters.MonsterFactory;
import se.liu.ida.tddd78.towerdefense.objects.monsters.MonsterType;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

public class GameLogic {
    private Board board;
    private Collision collisionDetector;
    private int round;
    private int lives;
    private int monstersRemaining;

    private Timer roundTimer;
    private Timer spawnTimer;

    private State state;

    public GameLogic(Board board, Collision collisionDetector) {
        this.board = board;
        this.collisionDetector = collisionDetector;
        this.round = 1;
        this.lives = 30;
        this.monstersRemaining = 10;

        this.roundTimer = new Timer(2000);
        this.spawnTimer = new Timer(500);

        this.state = State.PRE_ROUND;
    }

    public void tick() {
        switch (state) {
            case PRE_ROUND:
                if (this.roundTimer.hasCompleted()) {
                    startRound();
                }

                break;
            case ROUND:
                if (this.monstersRemaining > 0) {
                    if (this.spawnTimer.hasCompleted()) {
                        spawnNewMonster();
                    }
                } else if (board.getGameObjects().getMonsters().size() == 0) {
                    nextRound();
                }

                checkForFinishedMonsters();

                break;
            case GAME_OVER:
                System.out.println("We lost");

                break;
        }

        this.board.getGameObjects().removeObsoleteObjects();
    }

    private void nextRound() {
        if (this.state == State.GAME_OVER) return;

        this.state = State.PRE_ROUND;
        this.roundTimer.reset();
    }

    private void startRound() {
        if (this.state == State.GAME_OVER) return;

        this.state = State.ROUND;
        this.spawnTimer.reset();
        this.monstersRemaining = 10;
        this.round++;
    }

    private void spawnNewMonster() {
        Monster monster = MonsterFactory.makeMonster(MonsterType.SMALL); // TODO: Pick monster from round
        monster.setPosition(this.board.getSpawn().getCenter());

        this.spawnTimer.reset();
        this.board.getGameObjects().add(monster);
        this.monstersRemaining--;
    }

    private void checkForFinishedMonsters() {
        Tile goalTile = this.board.getGoal();
        for (Monster monster : this.board.getGameObjects().getMonsters()) {
            if (collisionDetector.isAtCenter(monster, goalTile)) {
                monster.setRemoved(true);
                removeLife();
            }
        }
    }

    private void removeLife() {
        this.lives--;
        if (this.lives == 0) {
            this.state = State.GAME_OVER;
        }
    }

    private enum State {
        PRE_ROUND,
        ROUND,
        GAME_OVER
    }

}
