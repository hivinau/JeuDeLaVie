package lifegame.model.listeners;

import lifegame.model.*;

public interface PoissonListener {

	//public void poissonDied(Poisson poisson);
	//public void move(final Poisson poisson, final Point point);
	public boolean canMoveToPosition(final Poisson poisson, final int x, final int y);
	public void lastPosition(final Poisson poisson, final int x, final int y);
	public void currentPosition(final Poisson poisson, final int x, final int y);
	public void update(final Poisson poisson, final int x, final int y);
}
