package lifegame.model.listeners;

import lifegame.model.*;

public interface PoissonListener {

	public void update(final Poisson poisson, final int x, final int y);
	public int availableDirection(final Poisson poisson);
}
