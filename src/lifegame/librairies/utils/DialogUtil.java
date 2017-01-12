package lifegame.librairies.utils;

import javax.swing.*;

/**
 * <b>DialogUtil</b><br>
 * <b>methods</b>
 * <ul>
 * 	<li>canAppClosed: <p>Demande à l'utilisateur si l'application doit être fermée.</p></li>
 * </ul>
 * @author Jesus GARNICA OLARRA, Hivinau GRAFFE
 * @version 1.0
 */
public final class DialogUtil {

	/**
	 * Demande à l'utilisateur si l'application doit être fermée.
	 * @param title titre de la fenêtre.
	 * @param message message de la fenêtre.
	 * @return si <b>true</b>, l'application peut être fermée.
	 */
	public static boolean canAppClosed(String title, String message) {
		
		int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	
		return result == JOptionPane.YES_OPTION;
	}
}
