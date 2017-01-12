package lifegame.view.ui;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.awt.*;
import javax.swing.*;
import lifegame.librairies.utils.*;
import lifegame.model.*;
import lifegame.model.listeners.*;

public class Mer extends JPanel implements PoissonListener {
	
	private static final long serialVersionUID = 3547286219604869107L;
	
	private final Set<Poisson> poissons;
	private final java.util.List<Square> squares;
	private final Lock lock = new ReentrantLock();
	
	@Override
	public int availableDirection(Poisson poisson) {
		
		Resources resources = Resources.getInstance();
		final int items = resources.getInt("grid__items");

		boolean occupied = false;
		
		int available = -1;
		do {

			int movement = Movement.random();
			
			for(Poisson p: poissons) {
				
				switch (movement) {
				case Movement.UP:
					
					int moveUp = poisson.getPositionY() - 1;

					occupied = (p.getPositionX() == poisson.getPositionX() && p.getPositionY() == moveUp) || (0 > moveUp);
					break;
				case Movement.DOWN:
					
					int moveDown = poisson.getPositionY() + 1;

					occupied = (p.getPositionX() == poisson.getPositionX() && p.getPositionY() == moveDown) || (moveDown > items);
					break;
				case Movement.LEFT:
					
					int moveLeft = poisson.getPositionX() - 1;
					
					occupied = (p.getPositionX() == moveLeft && p.getPositionY() == poisson.getPositionY()) || (0 > moveLeft);
					break;
				case Movement.RIGHT:
					
					int moveRight = poisson.getPositionX() + 1;

					occupied = (p.getPositionX() == moveRight && p.getPositionY() == poisson.getPositionY()) || (moveRight > items);
					break;
				}
				
				if(!occupied) {
					
					break;
				}
			}
			
			available = movement;
		
		} while(occupied && available != -1);
		
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
					
					poissons.add(poisson);
					add(poisson);
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

	public Mer(int sardinesCount, int requinsCount) {
		
		poissons = new HashSet<>();
		squares = new ArrayList<>();
		
		initComponents(sardinesCount, requinsCount);
	}
	
	public void update() {
		
		lock.lock();
		try {

			ExecutorService executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
			
 			Iterator<Poisson> iterator = poissons.iterator();
			
		    while(iterator.hasNext()) {
		    	
		    	final Poisson poisson = iterator.next();
		    	
		    	executor.submit(new Runnable() {
					
					@Override
					public void run() {

				    	poisson.move();
					}
				});
		    }
		    
		    executor.shutdown();
		    executor.awaitTermination(300, TimeUnit.SECONDS);
		    
		} catch (Exception ignored) {}
		finally {
			
			lock.unlock();
		}
	}

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
	
	private void add(Poisson poisson) {
		
		Square square = getSquare(poisson.getPositionX(), poisson.getPositionY());
		
		if(square != null) {
			
			square.setPoisson(poisson);
		}
	}
	
	private void remove(Poisson poisson) {
		
		Square square = getSquare(poisson.getPositionX(), poisson.getPositionY());
		
		if(square != null) {
			
			square.removePoisson();
		}
	}
	
	private Square getSquare(int col, int row) {
		
		Resources resources = Resources.getInstance();
		
		final int items = resources.getInt("grid__items");
		
		int index = row * items  + col;
		
		Square square = null;
		
		if(index < squares.size()) {
			
			square = squares.get(index);
		}
		
		return square;
	}
}
