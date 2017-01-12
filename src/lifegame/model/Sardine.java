package lifegame.model;


/**
 * <b>Sardine <u>extends</u> {@link lifegame.librairies.Poisson}</b><br>
 * <ul>
 * <li><b>methods</b>
 * <ul>
 * 	<li>move: <p>Déplace la sardine d'une case dans une direction définie.</p></li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class Sardine extends Poisson {

	public Sardine(int positionX, int positionY) {
		super(positionX, positionY);
	}
	
	public Sardine(Poisson poisson) {
		
		this(poisson.positionX, poisson.positionX);
		age = poisson.age;
	}
}
