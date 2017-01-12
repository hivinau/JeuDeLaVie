package lifegame_tests;

import lifegame.model.*;
import lifegame.model.exceptions.*;

/**
 * <b>TestUtil:</b><br>
 * <ul>
 * <li><b>methods</b>
 * <ul>
 * 	<li>setPoissonAge: <p>Modifie l'âge d'un poisson.</p></li>
 * 	<li>poissonDieAutomaticallyBeforePotentialMaxAge: <p>Vérifie si un poisson meurt de façon "naturelle".</p></li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public class TestUtil {
	
	/**
	 * Distinction concernant le type poisson.
	 */
	public interface PoissonType {
		
		/**
		 * Indique que le poisson est une sardine.<br>
		 * <p>Valeur: <b>0x01</b></p>
		 */
		public static final int SARDINE = 0x01;
		
		/**
		 * Indique que le poisson est un requin.<br>
		 * <p>Valeur: <b>0x02</b></p>
		 */
		public static final int REQUIN = 0x02;
	}

	/**
	 * Modifie l'âge d'un poisson.
	 * @param type type de poisson à modifier.
	 * @param age nouvel âge du poisson.
	 * @throws PoissonException l'âge doit être compris entre {@link lifegame.librairies.Poisson.MINIMAL_AGE} et {@link lifegame.librairies.Poisson.MAXIMAL_AGE}.
	 */
	public static void setPoissonAge(int type, double age) throws PoissonException {
		
		Poisson poisson = TestUtil.createPoissonByType(type);
		
		poisson.setAge(age);
	}
	
	/**
	 * Vérifie si un poisson meurt de façon "naturelle".
	 * @param type type de poisson à vérifier.
	 * @return si <b>true</b>, le poisson meurt naturellement.
	 */
	public static boolean poissonDieAutomaticallyBeforePotentialMaxAge(int type)  {
		
		Poisson poisson = TestUtil.createPoissonByType(type);
	
		double currentAge = 0.0d;
		do {
			
			poisson.incrementAge();
			currentAge++;
			
		} while(poisson.isAlive());
		
		double potentialMaximumAge = 1.8d * Poisson.MAXIMAL_AGE;
		
		return currentAge < potentialMaximumAge;
	}
	
	/**
	 * Crée une nouvelle instance de {@link lifegame.librairies.Poisson}.
	 * @param type type de poisson à instantier.
	 * @return nouvelle instance de {@link lifegame.librairies.Poisson}.
	 */
	private static Poisson createPoissonByType(int type) {
		
		final Poisson poisson;
		final int position = 0;
		
		if(type == PoissonType.SARDINE) {
			
			poisson = new Sardine(position, position);
		} else {
			
			poisson = new Requin(position, position);
		}
		
		return poisson;
	}
}
