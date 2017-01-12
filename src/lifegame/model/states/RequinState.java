package lifegame.model.states;

/**
 * <b>RequinState:</b><br>
 * <p>Interface représentant l'état d'un requin.</p>
 * @author Jesus GARNICA OLARRA & Hivinau GRAFFE
 * @version 1.0
 */
public interface RequinState {
	
	/**
	 * Indique que le requin est enfant.<br>
	 * <p>Valeur: <b>0x01</b></p>
	 */
	public static final int CHILD = 0x01;
	
	/**
	 * Indique que le requin est adolescent.<br>
	 * <p>Valeur: <b>0x02</b></p>
	 */
	public static final int YOUNG = 0x02;
	
	/**
	 * Indique que le requin est adulte.<br>
	 * <p>Valeur: <b>0x03</b></p>
	 */
	public static final int ADULT = 0x03;
	
	/**
	 * Indique que l'état du requin à évoluer.
	 * @param state nouvel état du requin
	 */
	public void stateChanged(int state);
}
