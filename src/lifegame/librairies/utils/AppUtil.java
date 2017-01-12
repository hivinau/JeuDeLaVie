package lifegame.librairies.utils;

import java.awt.*;
import javax.swing.*;

/**
 * <b>AppUtil</b><br>
 * <ul>
 * <li><b>methods</b>
 * <ul>
 * 	<li>closeApp: <p>Ferme l'application.</p></li>
 * 	<li>runOnUIThread: <p>Exécute une closure dans le thread principale.</p></li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class AppUtil {

	/**
	 * Ferme l'application.
	 */
	public static void closeApp() {
		
		Thread process = new Thread(new Runnable() {
			
			@Override
			public void run() {

				try {

					System.exit(0);
					
				} catch(SecurityException ex) {
					
					System.err.format("Erreur survenue lors de la fermeture de l'application : %s%n", ex.getMessage());
				}
			}
		});
		
		process.setPriority(Thread.MIN_PRIORITY);
		process.start();
	}
	
	/**
	 * Exécute une closure dans le thread principale.
	 * @param runnable closure à exécuter.
	 */
	public static void runOnUIThread(Runnable runnable) {
		
		SwingUtilities.invokeLater(runnable);
	}
	
	/**
	 * Génère de façon arbitraire un entier compris dans un intervalle défini.
	 * @param min début de l'intervalle.
	 * @param max fin de l'intervalle.
	 * @return entier généré aléatoirement.
	 */
	public static int random(double min, double max) {
		
		return (int) (min + Math.random() * (max - min));
	}
	
	/**
	 * Génère de façon arbitraire un point selon des coordonnées limites.
	 * @param minX coordonnée x minimum.
	 * @param maxX coordonnée x maximum.
	 * @param minY coordonnée y minimum.
	 * @param maxY coordonnée y maximum.
	 * @return point généré aléatoirement.
	 */
	public static Point randomLocation(int minX, int maxX, int minY, int maxY) {
		
		Point point = new Point();
		
		point.x = AppUtil.random(minX, maxX);
		point.y = AppUtil.random(minY, maxY);
		
		return point;
	}
}
