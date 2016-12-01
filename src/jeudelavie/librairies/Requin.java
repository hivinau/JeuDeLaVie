package jeudelavie.librairies;

import java.util.*;

/**
 * <b>Requin <u>extends</u> {@link jeudelavie.librairies.Poisson}</b><br>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class Requin extends Poisson implements RequinState {
	
	/////////////////////////PROPERTIES/////////////////////////
	
	private int state;
	private final List<Sardine> sardines;
	
	/////////////////////////DELEGATE METHODS/////////////////////////
	
	@Override
	public void stateChanged(int state) {
		
		this.state = state;
	}
	
	/////////////////////////INIT/////////////////////////

	public Requin() {

		this.state = RequinState.CHILD;
		this.sardines = new ArrayList<Sardine>();
	}
	
	/////////////////////////PUBLIC METHODS/////////////////////////
	
	@Override
	public void move() {
		
		switch(state) {
		case RequinState.CHILD:
			break;
		case RequinState.YOUNG:
			break;
		case RequinState.ADULT:
			break;
		default:
		}
		
		//TODO: 
	}

	public void eat(Sardine sardine) {
		
		sardines.add(sardine);
	}
}
