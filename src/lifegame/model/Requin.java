package lifegame.model;

import java.util.*;
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
public final class Requin extends Poisson implements RequinState {
	
	/////////////////////////PROPERTIES/////////////////////////
	
	private int state;
	private final List<Sardine> sardines;
	
	/////////////////////////DELEGATE METHODS/////////////////////////
	
	/**
	 * {@inheritDoc}
	 */
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
	
	public Requin(Poisson poisson) {
		
		this(poisson.positionX, poisson.positionX);
		age = poisson.age;
	}
	
	/////////////////////////PUBLIC METHODS/////////////////////////
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move() {
		
		switch(state) {
		case RequinState.CHILD:
			//TODO générer aléatoirement la direction du mouvement du requin.
			break;
		case RequinState.YOUNG:
			//TODO 1- s'il y a une sardine dans la case voisine, déplacer le requin vers cette case.
			//TODO 2- sinon, Rgénérer aléatoirement la direction du mouvement du requin.
			break;
		case RequinState.ADULT:
			//TODO déplacer le requin vers la sardine la plus proche.
			break;
		default:
		}
	}

	/**
	 * Ajoute une sardine dans le décompte totale de sardines mangés.
	 * @param sardine {@link lifegame.librairies.Sardine} à ajouter.
	 */
	public void eat(Sardine sardine) {
		
		sardines.add(sardine);
	}
	
	public int getState() {
		
		return state;
	}
}
