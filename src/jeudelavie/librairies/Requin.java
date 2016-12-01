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

	public Requin(int positionX, int positionY) {
		super(positionX, positionY);

		this.state = RequinState.CHILD;
		this.sardines = new ArrayList<Sardine>();
	}
	
	/////////////////////////PUBLIC METHODS/////////////////////////
	
	@Override
	public void move() {
		
		switch(state) {
		case RequinState.CHILD:
			//TODO générer aléatoirement la direction du mouvement du requin.
			break;
		case RequinState.YOUNG:
			//TODO 1- s'il y a une sardine dans la case voisine, déplacer le requin vers cette case.
			//TODO 2- sinon, générer aléatoirement la direction du mouvement du requin.
			break;
		case RequinState.ADULT:
			//TODO déplacer le requin vers la sardine la plus proche.
			break;
		default:
		}
	}

	public void eat(Sardine sardine) {
		
		sardines.add(sardine);
	}
}
