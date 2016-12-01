package jeudelavie.librairies;

/**
 * <b>Sardine <u>extends</u> {@link jeudelavie.librairies.Poisson}</b><br>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public final class Sardine extends Poisson {

	public Sardine(int positionX, int positionY) {
		super(positionX, positionY);
	}
	
	/////////////////////////PUBLIC METHODS/////////////////////////

	@Override
	public void move() {

		//TODO 1- générer aléatoirement la direction du mouvement de la sardine.
		//TODO 2- vérifier que le déplacement est possible.
		//TODO 2- a) si possible, déplacer d'une case vers la direction générée
		//TODO 2- b) sinon, regénérer la direction
	}
}
