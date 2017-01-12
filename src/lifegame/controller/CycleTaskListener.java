package lifegame.controller;

/**
 * <b>CycleTaskListener:</b><br>
 * <p>Interface représentant l'état d'un cycle.</p> 
 * @author Jesus GARNICA OLARRA, Hivinau GRAFFE
 * @version 1.0
 */
public interface CycleTaskListener {

	/**
	 * Notifie de façon périodique le nombre de cycles passés.
	 * @param currentCycle nombre de cycles passés.
	 * @return si <b>true</b>, tous les cycles seront fermés.
	 */
	public boolean schedule(int currentCycle);
	
	/**
	 * Arrête tous les cycles en cours.
	 */
	public void cancel();
}
