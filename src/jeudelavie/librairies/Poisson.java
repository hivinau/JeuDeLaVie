package jeudelavie.librairies;

import jeudelavie.librairies.exceptions.PoissonException;
import jeudelavie.librairies.utils.*;

/**
 * <b>Poisson:</b><br>
 * <ul>
 * <li><b>attributes</b>
 * 	<ul>
 * 	<li>age</li>
 * 	<li>position (x et y)</li>
 *	<li>dieAgeAverage</li>
 *	</ul>
 * </li>
 * <li><b>methods</b>
 * 	<ul>
 * 	<li>born() : <p>Donne naissance à un nouveau Poisson.</p></li>
 * 	<li>moveTo(x, y) : <p>Déplace le poisson à la position spécifiée.</p></li>
 * 	</ul>
 * </li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA</li><li>Hivinau GRAFFE</li></ul>
 * @version 1.0
 */
public class Poisson {

	private double age;
	private int positionX;
	private int positionY;
	private double dieAgeAverage;
	
	public Poisson(double dieAgeAverage) {
		
		this.age = 0;
		this.dieAgeAverage = PoissonUtil.randomAge(0.8 * dieAgeAverage, 1.8 * dieAgeAverage);
	}
	
	/**
	 * Modifie l'âge du poisson.
	 * @param age âge du poisson.
	 * @throws PoissonException l'âge doit être inférieure à l'âge moyenne auquel le poisson mourra.
	 */
	public void setAge(double age) throws PoissonException {
		
		if(age >= dieAgeAverage) {
			
			String message = String.format("%.1f doit être inférieure à %.1f", age, dieAgeAverage);
			throw new PoissonException(message);
		} 
		
		this.age = age;
	}
	
	/**
	 * Donne naissance à un nouveau poisson.
	 * @return nouveau {@link jeudelavie.librairies.Poisson}
	 */
	public Poisson born() {
		
		return PoissonUtil.beBornFrom(this);
	}
	
	/**
	 * Déplace le poisson à la position X et Y.
	 * @param positionX
	 * @param positionY
	 */
	public void moveTo(int positionX, int positionY) {
		
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	/**
	 * Définie si le poisson est vivant ou mort.
	 * @return si <b>true</b>, le poisson est encore vivant.
	 */
	public boolean isAlive() {
		
		return age < dieAgeAverage;
	}
}
