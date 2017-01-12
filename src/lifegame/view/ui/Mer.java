package lifegame.view.ui;

import java.util.*;
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
	public boolean canMoveToPosition(Poisson poisson, int x, int y) {
		
		Resources resources = Resources.getInstance();
		
		final int items = resources.getInt("grid__items");
		
		//check if location is bounded on limits
		boolean occupied = !((0 <= x && x <= items) && (0 <= y && y <= items));
		
		for(Poisson p: poissons) {
			
			if(p.getPositionX() == x && p.getPositionY() == y) {
				
				occupied = true;
				break;
			}
		}
		
		String log = String.format("%s can %s move to case [%d,%d]", poisson instanceof Sardine ? "sardine" : "requin", occupied ? "not" : "", x, y);
		System.out.println(log);
		
		return occupied;
	}
	
	@Override
	public void lastPosition(Poisson poisson, int x, int y) {
		
		Square square = getSquare(x, y);
		
		if(square != null) {
			
			square.removePoisson();
			
			String log = String.format("remove %s from case [%d,%d] ", poisson instanceof Sardine ? "sardine" : "requin", x, y);
			System.out.println(log);
		}
	}
	
	@Override
	public void update(Poisson poisson, int x, int y) {
		
		lock.lock();
		
		try {
			
			synchronized(this) {
				
				poissons.remove(poisson);
				
				if(poisson.isAlive()) {
					
					Poisson p = poisson instanceof Sardine ? new Sardine(poisson) : new Requin(poisson);
					p.setPositionX(x);
					p.setPositionY(y);
					p.setPoissonListener(this);
					
					poissons.add(p);
				} else {
					
					String log = String.format("%s at case [%d,%d] is died", poisson instanceof Sardine ? "sardine" : "requin", x, y);
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
	
	@Override
	public void currentPosition(Poisson poisson, int x, int y) {
		
		if(poisson.isAlive()) {
			
			Square square = getSquare(x, y);
			
			if(square != null) {
				
				square.setPoisson(poisson);
				
				String log = String.format("move %s to case [%d,%d]", poisson instanceof Sardine ? "sardine" : "requin", x, y);
				System.out.println(log);
			}
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
			
 			Iterator<Poisson> iterator = poissons.iterator();
			
		    while(iterator.hasNext()) {
		    	
		    	final Poisson poisson = iterator.next();
		    	
		    	Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {

				    	poisson.move();
				    	//poisson.born();
					}
				});
		    	
		    	thread.setPriority(Thread.MIN_PRIORITY);
		    	thread.start();
		    }
		    
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
