package lifegame.model;

import java.util.List;
import java.util.ArrayList;
import lifegame.model.states.*;

/**
 * <b>Requin <u>extends</u> {@link lifegame.librairies.Poissonn}</b><br>
 * <ul>
 * <li><b>methods</b>
 * <ul>
 * 	<li>stateChanged: <p>Indique que l'état du requin à évoluer.</p></li>
 * 	<li>move: <p>Déplace le requin d'une case dans une direction définie.</p></li>
 * 	<li>eat: <p>Ajoute une sardine dans le décompte totale de sardines mangés.</p></li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class Requin extends Poisson {
	
	/////////////////////////PROPERTIES/////////////////////////
	
	private int state;
	private final List<Sardine> sardines;
	private RequinState requinStateListener = null;
	
	/////////////////////////INIT/////////////////////////

	public Requin(int positionX, int positionY) {
		super(positionX, positionY);

		this.state = RequinState.CHILD;
		this.sardines = new ArrayList<Sardine>();
	}
	
	/////////////////////////PUBLIC METHODS/////////////////////////
	
	public void setRequinState(RequinState requinStateListener) {
		
		this.requinStateListener = requinStateListener;
	}
	
	@Override
	public void move() {
		
		switch(state) {
		case RequinState.YOUNG:
			//TODO 1- s'il y a une sardine dans la case voisine, déplacer le requin vers cette case.
			//TODO 2- sinon, Rgénérer aléatoirement la direction du mouvement du requin.
			break;
		default:
			
			if(state == RequinState.CHILD) {
				//TODO générer aléatoirement la direction du mouvement du requin.
				
			} else if(state == RequinState.CHILD) {
				//TODO déplacer le requin vers la sardine la plus proche.
			}
		}
		
		super.move();
	}

	/**
	 * Ajoute une sardine dans le décompte totale de sardines mangés.
	 * @param sardine {@link lifegame.librairies.Sardine} à ajouter.
	 */
	public void eat(Sardine sardine) {
		
		sardines.add(sardine);
		
		if(sardines.size() <= 2) {
			
			this.state = RequinState.CHILD;
			
		} else if(sardines.size() <= 4) {
			
			this.state = RequinState.YOUNG;
			
		} else {
			
			this.state = RequinState.ADULT;
		}
		
		if(requinStateListener != null) {

			requinStateListener.stateChanged(state);
		}
		
	}
	
	/**
	 * <p>Récupère l'état du requin.</p>
	 * @return l'état du requin.
	 */
	public int getState() {
		
		return state;
	}
}
