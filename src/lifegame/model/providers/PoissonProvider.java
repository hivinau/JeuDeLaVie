package lifegame.model.providers;

import lifegame.model.*;

/**
 * <b>PoissonUtil:</b><br>
 * <ul>
 * <li><b>methods</b>
 * 	<ul>
 * 	<li>randomAge() : <p>Calcule un âge compris un âge minimum et un âge maximum de façon aléatoire.</p></li>
 * 	<li>beBornFrom() : <p>Crée une nouvelle instance de {@link jeudelavie.librairies.Poisson} en définissant la position de cette instance.</p></li>
 * 	</ul>
 * </li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class PoissonProvider {
	
	/**
	 * <p>Calcule un âge compris un âge minimum et un âge maximum de façon aléatoire.</p>
	 * @param minAge âge minimum.
	 * @param maxAge âge maximum.
	 * @return
	 */
	public static double randomAge(double minAge, double maxAge) {
		
		return minAge + Math.random() * (maxAge - minAge);
	}

	/**
	 * <p>Crée une nouvelle instance de {@link jeudelavie.librairies.Poisson} en définissant la position de cette instance.</p>
	 * @param mum poisson qui donne naissance à un nouveau poisson.
	 * @return nouvelle instance de {@link jeudelavie.librairies.Poisson}.
	 */
	public static Poisson beBornFrom(Poisson mum) {
		
		Poisson poisson = mum instanceof Sardine ? new Sardine(mum.getPositionX(), mum.getPositionY()) : new Requin(mum.getPositionX(), mum.getPositionY());
		
		return poisson;
	}
}
