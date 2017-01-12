package lifegame.model.listeners;

import lifegame.model.*;

/**
 * <b>PoissonListener:</b><br>
 * <p>Interface permettant de gérer un poisson sur la mer.</p>
 * @author Jesus GARNICA OLARRA, Hivinau GRAFFE
 * @version 1.0
 */
public interface PoissonListener {

	/**
	 * Indique que le poisson est sur le point d'être déplacé.
	 * @param poisson poisson à mettre à jour.
	 * @param x nouvelle position horizontale.
	 * @param y nouvelle position verticale.
	 */
	public void update(final Poisson poisson, final int x, final int y);
	
	/**
	 * Identifie le prochain mouvement du poisson.
	 * @param poisson poisson dont il faut identifer le prochain mouvement.
	 * @return le prochain {@link lifegame.model.Movement} du poisson.
	 */
	public int availableDirection(final Poisson poisson);
}
