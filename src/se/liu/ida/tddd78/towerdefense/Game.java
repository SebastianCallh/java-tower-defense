package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.ButtonObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.entities.ButtonType;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;
import se.liu.ida.tddd78.towerdefense.entities.basic.Timer;
import se.liu.ida.tddd78.towerdefense.entities.monster.*;
import se.liu.ida.tddd78.towerdefense.entities.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Keeps track of the board, the players stats, the input, game time and the spawning of monsters.
 */
public class Game implements Observer, ButtonObserver {
    private static final int STARTING_LIVES = 10;
    private static final int STARTING_MONEY = 500;
    private static final int NEW_ROUND_DELAY_MILLIS = 2000;
    private static final int MONSTER_SPAWN_INTERVAL_MILLIS = 500;

    private Board board;
    private Player player;
    private InputHandler inputHandler;
	private int round;
    private List<GameObserver> scoreObservers;
	private Spawner spawner;
    private Options options;
	private List<Monster> spawnList;

    private Timer roundTimer;
    private Timer spawnTimer;

    private State state;
    private static final Point STARTING_POSITION = new Point(100, 100);

	public Game(Board board,
                Player player,
                InputHandler inputHandler,
		        Spawner spawner,
                Options options) {

        assert board != null;
        assert player != null;
        assert inputHandler != null;
        assert spawner != null;
        assert options != null;
	    assert scoreObservers != null;

        this.board = board;
        this.player = player;
        this.player.addPlayerObserver(this);
        this.inputHandler = inputHandler;
        this.spawner = spawner;
        this.options = options;
        this.scoreObservers = new ArrayList<>();
        options.addOptionChangeObserver(this);

        resetGame();
        gotoMenu();

        assert roundTimer != null;
        assert spawnList != null;
        assert state != null;
    }

    public int getRound() {
        return this.round;
    }

    public Player getPlayer() {
        return this.player;
    }

    public State getState() {
        return this.state;
    }

    public Options getOptions() {
        return options;
    }

    private void setRound(int round) {
        this.round = round;
        notifyScoreChanged();
    }

    private void setState(State state) {
        this.state = state;

        notifyScoreChanged();
    }

    public void resetGame() {
        this.player.setLives(STARTING_LIVES);
        this.player.setMoney(STARTING_MONEY);
        this.player.getCharacter().setPosition(STARTING_POSITION);
        this.board.reset(this.player.getCharacter());
        this.roundTimer = new Timer(NEW_ROUND_DELAY_MILLIS);
        this.spawnTimer = new Timer(MONSTER_SPAWN_INTERVAL_MILLIS);
        this.board.setLayout(this.options.getLayout());
        this.board.setTheme(this.options.getTheme());

        setState(State.PRE_ROUND);
        setRound(1);
    }

    public void gotoMenu() {
        setState(State.MAIN_MENU);
    }

    public void update() {
        this.board.update();
        this.checkState();
        List<Command> removeCommands = this.board.getGameObjects().removeObsoleteObjects();
        removeCommands.stream().filter(command -> command != null).forEach(command -> command.execute(this.player, this.board));
    }

    private void checkState() {
        switch (state) {
            case PRE_ROUND:
                if (this.roundTimer.hasCompleted()) {
                    startRound();
                }

                break;
            case ROUND:
                if (!this.spawnList.isEmpty()) {
                    if (this.spawnTimer.hasCompleted()) {
                        spawnNewMonster();
                    }
                } else if (board.getGameObjects().getMonsters().isEmpty()) {
                    nextRound();
                }

                checkForFinishedMonsters();

                break;
            case GAME_OVER:
                break;
            case MAIN_MENU:
                break;
            case OPTIONS_MENU:
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

        setRound(this.round + 1);
        setState(State.PRE_ROUND);
        this.roundTimer.reset();
    }

    private void startRound() {
        if (this.state == State.GAME_OVER) return;

        setState(State.ROUND);
        this.spawnTimer.reset();

        this.spawnList = this.spawner.spawn(this.round);
    }

    private void spawnNewMonster() {
        Monster monster = this.spawnList.remove(this.spawnList.size() - 1);
        monster.setPosition(this.board.getSpawn().getCenter());
        this.spawnTimer.reset();
        this.board.getGameObjects().add(monster);
    }

	private void checkForFinishedMonsters() {
		Tile goalTile = this.board.getGoal();
        this.board.getGameObjects().getMonsters().stream()
                .filter(monster -> Objects.equals(goalTile, this.board.getTileUnder(monster)))
                .forEach(monster -> {
            monster.setRemoved(true);
            removeLife(monster.getDamage());
        });
	}

    private void removeLife(int amount) {
        this.player.removeLives(amount);
        if (this.player.getLives() <= 0) {
            setState(State.GAME_OVER);
        }
    }

    private void notifyScoreChanged() {
        for (GameObserver scoreObserver : scoreObservers) {
            scoreObserver.onNotify(this);
        }
    }

    @Override
    public void onNotify() {
        notifyScoreChanged();
    }

    public void addScoreObserver(GameObserver scoreObserver) {
        this.scoreObservers.add(scoreObserver);
        scoreObserver.onNotify(this);
    }

    @Override
    public void onButtonClicked(ButtonType buttonType) {
        switch (buttonType) {
            case NEW_GAME:
                resetGame();
                break;
            case MAIN_MENU:
                setState(State.MAIN_MENU);
                break;
            case OPTIONS:
                setState(State.OPTIONS_MENU);
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    public enum State {
        PRE_ROUND,
        ROUND,
        GAME_OVER,
        MAIN_MENU,
        OPTIONS_MENU
    }
}
