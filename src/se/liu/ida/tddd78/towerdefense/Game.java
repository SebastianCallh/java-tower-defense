package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.monster.*;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;

import java.util.List;
import java.util.Queue;

public class Game implements Observer {
	private Board board;
    private Player player;
    private InputHandler inputHandler;
	private int round;
	private int monstersRemaining;
    private GameObserver scoreObserver;

    private Timer roundTimer;
    private Timer spawnTimer;

    private State state;
    private int STARTING_LIVES = 10;
    private int STARTING_MONEY = 50000;
    private Point STARTING_POSITION = new Point(100, 100);

	public Game(Board board,
                Player player,
                InputHandler inputHandler,
                GameObserver scoreObserver) {
		this.board = board;
        this.player = player;
        this.player.addScoreObserver(this);
        this.scoreObserver = scoreObserver;
        this.player.setLives(STARTING_LIVES);
        this.player.setMoney(STARTING_MONEY);
        this.player.getCharacter().setPosition(STARTING_POSITION);

        this.inputHandler = inputHandler;
		this.monstersRemaining = 10;

        this.roundTimer = new Timer(2000);
        this.spawnTimer = new Timer(500);

        this.state = State.PRE_ROUND;

        setRound(1);
    }

    public int getRound() {
        return this.round;
    }

    public Player getPlayer() {
        return this.player;
    }

    private void setRound(int round) {
        this.round = round;
        notifyScoreChanged();
    }

    public void update() {
        this.board.update();
        this.checkState();
        List<Command> removeCommands = this.board.getGameObjects().removeObsoleteObjects();
        removeCommands.stream().filter(command -> command != null).forEach(command -> {
            command.execute(this.player, this.board);
        });
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
        Queue<Command> commandQueue = this.inputHandler.getCommands();
        while(!commandQueue.isEmpty()) {
            commandQueue.remove().execute(this.player, this.board);
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

        setRound(this.round + 1);
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
            if (goalTile== this.board.getTileUnderObject(monster)) {
                monster.setRemoved(true);
                removeLife(monster.getDamage());
            }
		}
	}

    private void removeLife(int amount) {
        this.player.removeLives(amount);
        if (this.player.getLives() == 0) {
            this.state = State.GAME_OVER;
        }
    }

    private void notifyScoreChanged() {
        if (this.scoreObserver != null) {
            scoreObserver.onNotify(this);
        }
    }

    @Override
    public void onNotify() {
        notifyScoreChanged();
    }

    private enum State {
        PRE_ROUND,
        ROUND,
        GAME_OVER
    }
}
