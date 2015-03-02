package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.defenses.DefenseBehavior;
import se.liu.ida.tddd78.towerdefense.objects.monsters.*;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileBehavior;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileMover;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

public class Game {
	private Board board;
	private Collision collisionHandler;
    private Input inputHandler;
	private int round;
	private int lives;
	private int monstersRemaining;

    private MonsterMover monsterMover;
    private ProjectileMover projectileMover;

    private MonsterBehavior monsterBehavior;
    private DefenseBehavior defenseBehavior;
    private ProjectileBehavior projectileBehavior;

    private Timer roundTimer;
    private Timer spawnTimer;

    private State state;

	public Game(Board board, Collision collisionHandler, Input inputHandler) {
		this.board = board;
		this.collisionHandler = collisionHandler;
        this.inputHandler = inputHandler;
		this.round = 1;
		this.lives = 30;
		this.monstersRemaining = 10;

        this.monsterBehavior = new MonsterBehavior(board);
        this.projectileMover = new ProjectileMover(board);
        this.monsterMover = new MonsterMover(board);

        this.defenseBehavior = new DefenseBehavior(board);
        this.projectileBehavior = new ProjectileBehavior(board);

        this.roundTimer = new Timer(2000);
        this.spawnTimer = new Timer(500);

        this.state = State.PRE_ROUND;
    }

    public void update() {
        this.board.update();
        this.monsterMover.move();
        this.projectileMover.move();
        this.checkState();
        this.monsterBehavior.behave();
        this.defenseBehavior.behave();
        this.projectileBehavior.behave();
        this.board.getGameObjects().removeObsoleteObjects();
    }

    private void checkState() {
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

    }
    public void processInput() {
        // Testing purposes, not actual usage pattern
        for (Input.Action action : Input.Action.values()) {
            if (inputHandler.isKeyPressed(action)) {
                System.out.println(action);
            }
        }
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
			if (collisionHandler.isAtCenter(monster, goalTile)) {
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
