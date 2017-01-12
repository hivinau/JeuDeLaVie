package lifegame.controller;

import java.util.*;

/**
 * <b>CycleTask <u>extends</u> {@link java.util.TimerTask}</b><br>
 * <p>Permet de notifier de façon répétitive un cycle.
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public class CycleTask extends TimerTask {
	
	private final CycleTaskListener listener;
	private int currentCycle = 1;
	
	/**
	 * {@inheritDoc}
	 */
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
