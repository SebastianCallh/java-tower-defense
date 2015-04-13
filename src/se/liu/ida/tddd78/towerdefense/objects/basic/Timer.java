package se.liu.ida.tddd78.towerdefense.objects.basic;

/**
 * Keeps track of if a certain amount of time has passed.
 */
public class Timer {
    private long timerDurationMillis;
    private long startTimeMillis;

    public Timer(long timerDurationMillis) {
        this.timerDurationMillis = timerDurationMillis;
        this.startTimeMillis = System.currentTimeMillis();
    }

    public void reset() {
        this.startTimeMillis = System.currentTimeMillis();
    }

    public void reset(long timerDuration) {
        this.timerDurationMillis = timerDuration;
        reset();
    }

    public boolean hasCompleted() {
        return (System.currentTimeMillis() - timerDurationMillis > startTimeMillis);
    }
}
