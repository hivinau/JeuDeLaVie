package lifegame.controller;

import java.util.*;

public class CycleTask extends TimerTask {
	
	private final CycleTaskListener listener;
	private int currentCycle = 1;
	
	public CycleTask(CycleTaskListener listener) {
		
		this.listener = listener;
		this.currentCycle = 1;
	}

	@Override
	public void run() {
		
		if(listener != null) {
			
			if(!listener.schedule(currentCycle++)) {
				
				listener.cancel();
			}
		}
	}

	
}
