package lifegame.controller;

public interface CycleTaskListener {

	public boolean schedule(int currentCycle);
	public void cancel();
}
