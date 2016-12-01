package jeudelavie.librairies;

import java.util.*;

/**
 * <b>Requin <u>extends</u> {@link jeudelavie.librairies.Poisson}</b><br>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class Requin extends Poisson {
	
	public static final int CHILD = 0x01;
	public static final int YOUNG = 0x02;
	public static final int ADULT = 0x03;
	
	private int state;
	private final List<Sardine> sardines;

	public Requin() {

		this.state = Requin.CHILD;
		this.sardines = new ArrayList<Sardine>();
	}
	
	@Override
	public void move() {
		
	}

	public void eat(Sardine sardine) {
		
		sardines.add(sardine);
	}
}
