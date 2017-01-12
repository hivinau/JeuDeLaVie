package lifegame.model;

import java.awt.*;
import lifegame.librairies.utils.*;
import lifegame.model.exceptions.*;
import lifegame.model.listeners.*;
import lifegame.model.providers.*;

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
 * 	<li>move() : <p>Déplace le poisson d'une case dans une direction définie.</p></li>
 * 	<li>getPositionX() : <p>Récupère la position horizontale du poisson.</p></li>
 * 	<li>getPositionY() : <p>Récupère la position verticale du poisson.</p></li>
 * 	<li>setAge(age) : <p>Modifie l'âge du poisson.</p></li>
 * 	<li>born() : <p>Donne naissance à un nouveau Poisson.</p></li>
 * 	<li>isAlive() : <p>Définie si le poisson est vivant ou mort.</p></li>
 * 	<li>moveTo(x, y) : <p>Déplace le poisson aux nouvelles positions horizontale et verticale.</p></li>
 * 	</ul>
 * </li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public abstract class Poisson {
	
	/////////////////////////PROPERTIES/////////////////////////

	/**
	 * Âge minimal légal.<br>
	 * <p>Valeur: <b>0</b></p>
	 */
	public static final double MINIMAL_AGE = 0.0d;
	
	/**
	 * Âge maximal légal (différent de l'âge maximum qui correspond à l'âge limite de vie du poisson).<br>
	 * <p>Valeur: <b>20</b></p>
	 */
	public static final double MAXIMAL_AGE = 3.0d;

	private double maxAge;

	protected PoissonListener listener = null;
	protected double age;
	protected int positionX;
	protected int positionY;
	
	/////////////////////////INIT/////////////////////////
	
	/**
	 * Crée une nouvelle instance de {@link lifegame.model.Poisson}: <br>
	 * <ul>
	 * 	<li>Initialise l'âge du poisson à 0.</li>
	 * 	<li>Initialise l'âge maximum de façon aléatoire en tenant compte d'un âge certain.</li>
	 * </ul>
	 * @param positionX position horizontale du poisson.
	 * @param positionY position verticale du poisson.
	 */
	public Poisson(int positionX, int positionY) {
		
		this.age = Poisson.MINIMAL_AGE;
		this.maxAge = PoissonProvider.randomAge(0.8d * Poisson.MAXIMAL_AGE, 1.8d * Poisson.MAXIMAL_AGE);
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Poisson(Poisson poisson) {
		
		this(poisson.positionX, poisson.positionX);
	}
	
	/////////////////////////PUBLIC METHODS/////////////////////////
	
	/**
	 * Déplace le poisson d'une case dans une direction définie.
	 */
	public void move() {
		
		if(listener != null) {
			
			Resources resources = Resources.getInstance();
			final int items = resources.getInt("grid__items");
			
			Point next = null;
			
			do {
				
				next = handlePosition();
				
			} while(next == null || 
					next.x < 0 || next.y < 0 || (next.x == 0 && next.y == 0) ||
					next.x >= items || next.y >= items || (next.x == items && next.y == items));
			
			Poisson baby = born();
			incrementAge();
			
			listener.update(Poisson.this, next.x, next.y);
			listener.update(baby, baby.positionX, baby.positionY);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		return (int)(2 * Poisson.MAXIMAL_AGE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof Poisson)) {
			
			return false;
		}
		
		Poisson poisson = (Poisson) object;
		
		return positionX == poisson.getPositionX() 
				&& positionY == poisson.getPositionY();
	}
	
	/**
	 * Mise en place d'un écouteur sur les différents évènements que ce poisson régit.
	 * @param listener écouteur.
	 */
	public void setPoissonListener(PoissonListener listener) {
		
		this.listener = listener;
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
	 * <p>Modifie la position horizontale du poisson</p>
	 * @param positionX position horizontale du poisson.
	 */
	public void setPositionX(int positionX) {
		
		this.positionX = positionX;
	}
	
	/**
	 * <p>Modifie la position verticale du poisson</p>
	 * @param positionY position verticale du poisson.
	 */
	public void setPositionY(int positionY) {
		
		this.positionY = positionY;
	}

	/**
	 * <p>Modifie l'âge du poisson.</p>
	 * @param age âge du poisson.
	 * @throws PoissonException l'âge doit être compris entre MINIMAL_AGE et MAXIMAL_AGE.
	 */
	public void setAge(double age) throws PoissonException {
		
		if(age > Poisson.MAXIMAL_AGE) {
			
			String message = String.format("%.1f doit être inférieure à %.1f", age, Poisson.MAXIMAL_AGE);
			throw new PoissonException(message);
			
		} else if(age <= Poisson.MINIMAL_AGE) {
			
			String message = String.format("%.1f doit être supérieure à %.1f", age, Poisson.MINIMAL_AGE);
			throw new PoissonException(message);
		}
		
		this.age = age;
	}
	
	/**
	 * <p>Récupère l'âge du poisson.</p>
	 * @return âge du poisson.
	 */
	public double getAge() {
		
		return age;
	}
	
	/**
	 * <p>Incrémente l'âge du poisson de <b>1.0</b>.</p>
	 */
	public void incrementAge() {
		
		this.age += 1.0d;
	}
	
	/**
	 * <p>Donne naissance à un nouveau poisson.</p>
	 * @return nouvelle instance de {@link lifegame.model.Poisson}.
	 */
	public Poisson born() {
		
		Point point = handlePosition();
		
		final Poisson poisson = PoissonProvider.beBornFrom(this);

		poisson.age = 0.d;
		
		AppUtil.runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				if(listener != null) {

					//listener.lastPosition(poisson, positionX, positionY);
					listener.update(poisson, point.x, point.y);
					//listener.currentPosition(poisson, point.x, point.y);
				}
			}
		});
		
		return poisson;
	}
	
	/**
	 * <p>Définie si le poisson est vivant ou mort.</p>
	 * @return si <b>true</b>, le poisson est encore vivant.
	 */
	public boolean isAlive() {
		
		return age < maxAge;
	}
	
	/////////////////////////PROTECTED METHODS/////////////////////////
	
	protected synchronized Point handlePosition() {

		Point point = null;
		
		if(listener != null) {
			
			int available = listener.availableDirection(this);
			
			switch (available) {
			case Movement.UP:
				
				point = new Point(positionX, positionY - 1);
				break;
			case Movement.DOWN:
				
				point = new Point(positionX, positionY + 1);
				break;
			case Movement.LEFT:
				
				point = new Point(positionX - 1, positionY);
				break;
			case Movement.RIGHT:
				
				point = new Point(positionX + 1, positionY);
				break;
			}
		}
		
		return point;
	}
}
