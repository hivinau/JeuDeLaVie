package jeudelavie.librairies;

import jeudelavie.librairies.exceptions.*;
import jeudelavie.librairies.utils.*;

/**
 * <b>Poisson:</b><br>
 * <ul>
 * <li><b>attributes</b>
 * 	<ul>
 * 	<li>age : <p>âge courant du poisson.</p></li>
 *	<li>maxAge : <p>âge maximum du poisson.</p></li>
 * 	<li>positionX : <p>position horizontale du poisson.</p></li>
 * 	<li>positionY : <p>position verticale du poisson.</p></li>
 *	</ul>
 * </li>
 * <li><b>methods</b>
 * 	<ul>
 * 	<li>getPositionX() : <p>Récupère la position horizontale du poisson.</p></li>
 * 	<li>getPositionY() : <p>Récupère la position verticale du poisson.</p></li>
 * 	<li>setAge(age) : <p>Modifie l'âge du poisson.</p></li>
 * 	<li>born() : <p>Donne naissance à un nouveau Poisson.</p></li>
 * 	<li>moveTo(x, y) : <p>Déplace le poisson aux nouvelles positions horizontale et verticale.</p></li>
 * 	<li>isAlive() : <p>Définie si le poisson est vivant ou mort.</p></li>
 * 	</ul>
 * </li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public class Poisson {
	
	public static double someAge = 20.d;

	private double age;
	private double maxAge;
	private int positionX;
	private int positionY;
	
	/**
	 * <p>Crée une nouvelle instance de {@link jeudelavie.librairies.Poisson}: <br>
	 * <ul>
	 * 	<li>Initialise l'âge du poisson à 0.</li>
	 * 	<li>Initialise l'âge maximum de façon aléatoire en tenant compte d'un âge certain.</li>
	 * </ul>
	 * <p>
	 */
	public Poisson() {
		
		this.age = 0;
		this.maxAge = PoissonUtil.randomAge(0.8 * Poisson.someAge, 1.8 * Poisson.someAge);
	}
	
	/**
	 * <p>Récupère la position horizontale du poisson.</p>
	 * @return position horizontale du poisson.
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * <p>Récupère la position verticale du poisson.</p>
	 * @return position verticale du poisson.
	 */
	public int getPositionY() {
		return positionY;
	}



	/**
	 * <p>Modifie l'âge du poisson.</p>
	 * @param age âge du poisson.
	 * @throws PoissonException l'âge doit être inférieure à un âge certain.
	 */
	public void setAge(double age) throws PoissonException {
		
		if(age >= Poisson.someAge) {
			
			String message = String.format("%.1f doit être inférieure à %.1f", age, Poisson.someAge);
			throw new PoissonException(message);
		} 
		
		this.age = age;
	}
	
	/**
	 * <p>Donne naissance à un nouveau poisson.</p>
	 * @return nouvelle instance de {@link jeudelavie.librairies.Poisson}.
	 */
	public Poisson born() {
		
		return PoissonUtil.beBornFrom(this);
	}
	
	/**
	 * <p>Déplace le poisson aux nouvelles positions horizontale et verticale.</p>
	 * @param positionX nouvelle position horizontale.
	 * @param positionY nouvelle position verticale.
	 */
	public void moveTo(int positionX, int positionY) {
		
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	/**
	 * <p>Définie si le poisson est vivant ou mort.</p>
	 * @return si <b>true</b>, le poisson est encore vivant.
	 */
	public boolean isAlive() {
		
		return age < maxAge;
	}
}
