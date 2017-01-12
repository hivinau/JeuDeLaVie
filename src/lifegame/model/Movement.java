package lifegame.model;

import java.util.*;

/**
 * <b>Movement:</b><br>
 * <p>Utilitaire pour les mouvements.</p>
 * @author Jesus GARNICA OLARRA, Hivinau GRAFFE
 * @version 1.0
 */
public class Movement {
	
	/**
	 * Représente un mouvement vers le haut.<br>
	 * <p>Valeur: <b>0</b></p>
	 */
	public static final int UP = 0;
	
	/**
	 * Représente un mouvement vers le bas.<br>
	 * <p>Valeur: <b>1</b></p>
	 */
	public static final int DOWN = 1;
	
	/**
	 * Représente un mouvement vers la droite.<br>
	 * <p>Valeur: <b>2</b></p>
	 */
	public static final int RIGHT = 2;
	
	/**
	 * Représente un mouvement vers la gauche.<br>
	 * <p>Valeur: <b>3</b></p>
	 */
	public static final int LEFT = 3;

	/**
	 * Génère aléatoirement un mouvement.
	 * @return un {@link lifegame.model.Movement} pouvant être un mouvement suivant:<br>
	 * <ul>
	 * <li>UP</li>
	 * <li>DOWN</li>
	 * <li>RIGHT</li>
	 * <li>LEFT</li>
	 * </ul>
	 */
	public static int random() {
		
		Random rn = new Random();
		int range = Movement.LEFT - Movement.UP + 1;
		
		return rn.nextInt(range) + Movement.UP;
	}
}
