package lifegame.view.ui;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.awt.*;
import javax.swing.*;
import lifegame.librairies.utils.*;
import lifegame.model.*;
import lifegame.model.listeners.*;

/**
 * <b>Mer:</b><br>
 * <p>Contient une gridview.</p>
 * @author Jesus GARNICA OLARRA & Hivinau GRAFFE
 * @version 1.0
 */
public class Mer extends JPanel implements PoissonListener {

	private static final long serialVersionUID = 3547286219604869107L;

	private final Set<Poisson> poissons;
	private final java.util.List<Square> squares;
	private final Lock lock = new ReentrantLock();

	@Override
	public int availableDirection(Poisson poisson) {

		//TODO 1- générer aléatoirement la direction du mouvement de la sardine.
		//TODO 2- vérifier que le déplacement est possible.
		//TODO 2- a) si possible, déplacer d'une case vers la direction générée
		//TODO 2- b) sinon, regénérer la direction

		int available = -1;

		lock.lock();

		try {

			synchronized(this) {

				boolean occupied = false;
				boolean isSardine = poisson instanceof Sardine;
				do {

					int movement = Movement.random();

					for(Poisson p: poissons) {

						switch (movement) {
						case Movement.UP:

							int moveUp = poisson.getPositionY() - 1;

							if(!isSardine && p instanceof Sardine) {

								occupied = p.getPositionY() == moveUp;
							} else {

								occupied = (p.getPositionX() == poisson.getPositionX() && p.getPositionY() == moveUp);
							}
							break;
						case Movement.DOWN:

							int moveDown = poisson.getPositionY() + 1;

							if(!isSardine && p instanceof Sardine) {

								occupied = p.getPositionY() == moveDown;
							} else {

								occupied = (p.getPositionX() == poisson.getPositionX() && p.getPositionY() == moveDown);
							}
							break;
						case Movement.LEFT:

							int moveLeft = poisson.getPositionX() - 1;

							if(!isSardine && p instanceof Sardine) {

								occupied = p.getPositionX() == moveLeft;
							} else {

								occupied = (p.getPositionX() == moveLeft && p.getPositionY() == poisson.getPositionY());
							}
							break;
						case Movement.RIGHT:

							int moveRight = poisson.getPositionX() + 1;

							if(!isSardine && p instanceof Sardine) {

								occupied = p.getPositionX() == moveRight;
							} else {

								occupied = (p.getPositionX() == moveRight && p.getPositionY() == poisson.getPositionY());
							}
							break;
						}

						if(!occupied) {

							break;
						}
					}

					available = movement;

				} while(occupied && isSardine && available != -1);
			}
		} catch(Exception ignored) {

			ignored.printStackTrace();
		}
		finally {

			lock.unlock();
		}

		return available;
	}

	@Override
	public void update(Poisson poisson, int x, int y) {

		lock.lock();

		try {

			synchronized(this) {

				poissons.remove(poisson);
				remove(poisson);

				if(poisson.isAlive()) {

					String log = String.format("move %s from [%d,%d] to [%d,%d]", poisson instanceof Sardine ? "sardine" : "requin", poisson.getPositionX(), poisson.getPositionY(), x, y);
					System.out.println(log);

					poisson.setPositionX(x);
					poisson.setPositionY(y);
					poisson.setPoissonListener(this);

					poissons.add(poisson);
					add(poisson);
					
					if(poisson instanceof Requin) {
						
						Requin requin = (Requin) poisson;
						
						Iterator<Poisson> iterator = poissons.iterator();
						
						while(iterator.hasNext()) {
							
							Poisson p = iterator.next();
							
							if(p instanceof Sardine && p.getPositionX() == x && p.getPositionY() == y) {
								
								remove(p);
								requin.eat((Sardine) p);

								log = String.format("requin eats sardine at [%d,%d]", x, y);
								System.out.println(log);
								
								iterator.remove();
								break;
							}
						}
					}
				} else {

					String log = String.format("%s at [%d,%d] is died", poisson instanceof Sardine ? "sardine" : "requin", poisson.getPositionX(), poisson.getPositionY());
					System.out.println(log);
				}
			}

		} catch(Exception ignored) {

			ignored.printStackTrace();
		}
		finally {

			lock.unlock();
		}
	}

	/**
	 * <p>Crée une nouvelle instance de {@link lifegame.view.ui.Mer}: <br>
	 * <ul>
	 * 	<li>Initialise les composants.</li>
	 * </ul>
	 * <p>
	 * @param sardinesCount nombre de sardines.
	 * @param requinsCount nombre de requins.
	 */
	public Mer(int sardinesCount, int requinsCount) {

		poissons = new HashSet<>();
		squares = new ArrayList<>();

		initComponents(sardinesCount, requinsCount);
	}

	/**
	 * Délègue les déplacements de chaque poisson.
	 */
	public void update() {

		lock.lock();
		try {
			
			ExecutorService pool = Executors.newFixedThreadPool(poissons.size()); 

			Iterator<Poisson> iterator = poissons.iterator();

			while(iterator.hasNext()) {

				final Poisson poisson = iterator.next();

				pool.submit(new Runnable() {

					@Override
					public void run() {

						poisson.move();
					}
				});
				
			}
			
			pool.shutdown();

		} catch (Exception ignored) {}
		finally {

			lock.unlock();
		}
	}

	/**
	 * Initialise les composants.
	 * @param sardinesCount nombre de sardines.
	 * @param requinsCount nombre de requins.
	 */
	private void initComponents(int sardinesCount, int requinsCount) {

		Resources resources = Resources.getInstance();

		final int items = resources.getInt("grid__items");

		setLayout(new GridLayout(items, items));

		for(int i = 0; i < sardinesCount; i++) {

			do {

				Point location = AppUtil.randomLocation(0, items, 0, items);

				Poisson poisson = new Sardine(location.x, location.y);

				poisson.setPoissonListener(this);
				poissons.add(poisson);

			} while(poissons.size() < i);
		}

		System.out.println(String.format("%d sardines will added to Mer", poissons.size()));

		int buffer = poissons.size();
		for(int i = 0; i < requinsCount; i++) {

			do {

				Point location = AppUtil.randomLocation(0, items, 0, items);

				Poisson poisson = new Requin(location.x, location.y);

				poisson.setPoissonListener(this);
				poissons.add(poisson);

			} while(poissons.size() < (sardinesCount + i));
		}

		System.out.println(String.format("%d requins will added to Mer", poissons.size() - buffer));

		for(int i = 0; i < items * items; i++) {

			int row = i / items;
			int col = i % items;

			Square square = new Square();

			for(Poisson poisson: poissons) {

				if(poisson.getPositionX() == col && poisson.getPositionY() == row) {

					square.setPoisson(poisson);

					System.out.println(String.format("%s add at position %d,%d", (poisson instanceof Sardine? "sardine" : "requin"), poisson.getPositionX(), poisson.getPositionY()));
					break;
				}
			}

			squares.add(square);
			add(square);

		}
	}

	/**
	 * Ajoute un poisson à la mer.
	 * @param poisson poisson à ajouter.
	 */
	private void add(Poisson poisson) {

		Square square = getSquare(poisson.getPositionX(), poisson.getPositionY());

		if(square != null) {

			square.setPoisson(poisson);
		}
	}

	/**
	 * Retire un poisson de la mer.
	 * @param poisson poisson à retirer.
	 */
	private void remove(Poisson poisson) {

		Square square = getSquare(poisson.getPositionX(), poisson.getPositionY());

		if(square != null) {

			square.removePoisson();
		}
	}

	/**
	 * Récupère l'espace occupé à une position précise.
	 * @param col position x.
	 * @param row position y.
	 * @return espace occupé.
	 */
	private Square getSquare(int col, int row) {

		Resources resources = Resources.getInstance();

		final int items = resources.getInt("grid__items");

		int index = row * items  + col;

		Square square = null;

		if(index > 0 && index < squares.size()) {

			square = squares.get(index);
		}

		return square;
	}
}
