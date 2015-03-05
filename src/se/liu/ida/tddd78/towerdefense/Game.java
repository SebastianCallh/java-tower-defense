package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.basic.HorizontalDirection;
import se.liu.ida.tddd78.towerdefense.objects.basic.VerticalDirection;
import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.monster.*;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;

public class Game {
	private Board board;
	private Collision collisionHandler;
    private Input inputHandler;
	private int round;
	private int lives;
	private int monstersRemaining;

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

        this.roundTimer = new Timer(2000);
        this.spawnTimer = new Timer(500);

        this.state = State.PRE_ROUND;
    }

    public void update() {
        this.board.update();
        this.checkState();
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
        if (this.inputHandler.isKeyPressed(Input.Action.LEFT)) {
            this.board.getPlayer().setHorizontalDirection(HorizontalDirection.LEFT);
        } else if (this.inputHandler.isKeyPressed(Input.Action.RIGHT)) {
            this.board.getPlayer().setHorizontalDirection(HorizontalDirection.RIGHT);
        } else {
            this.board.getPlayer().setHorizontalDirection(null);
        }

        if (this.inputHandler.isKeyPressed(Input.Action.UP)) {
            this.board.getPlayer().setVerticalDirection(VerticalDirection.UP);
        } else if (this.inputHandler.isKeyPressed(Input.Action.DOWN)) {
            this.board.getPlayer().setVerticalDirection(VerticalDirection.DOWN);
        } else {
            this.board.getPlayer().setVerticalDirection(null);
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
