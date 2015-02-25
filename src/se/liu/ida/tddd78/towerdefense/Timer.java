package se.liu.ida.tddd78.towerdefense;

public class Timer {
	private int startTime;
	private int timeRemaining;

	public Timer(int startTime) {
		this.startTime = startTime;
		this.timeRemaining = startTime;
	}

	public void reset() {
		this.timeRemaining = startTime;
	}

	public void reset(int startTime) {
		this.startTime = startTime;
		reset();
	}

	public void tick() {
		if (this.timeRemaining > 0) {
			this.timeRemaining--;
		}
	}

	public boolean hasCompleted() {
		return (this.timeRemaining == 0);
	}

}
