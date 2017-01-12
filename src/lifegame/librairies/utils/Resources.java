package lifegame.librairies.utils;

import lifegame.librairies.xml.*;

/**
 * <b>Resources</b><br>
 * <b>methods</b>
 * <ul>
 * 	<li>getInstance: <p>Récupère un singleton de cette classe.</p></li>
 * 	<li>initAppConfiguration: <p>Charge les fichiers XML de configuration.</p></li>
 * 	<li>getString: <p>Récupère une chaîne de caractères à partir d'un identifiant depuis strings.xml.</p></li>
 * 	<li>getInt: <p>Récupère un entier à partir d'un identifiant depuis integers.xml.</p></li>
 * </ul>
 * @author Jesus GARNICA OLARRA, Hivinau GRAFFE
 * @version 1.0
 */
public final class Resources {
	
	private static Resources instance = null;
	private static final String STRINGS_FILE = "strings_file";
	private static final String INTEGERS_FILE = "integers_file";
	
	private Resources() {
		
	}
	
	/**
	 * Récupère un singleton de cette classe.
	 * @return {@link lifegame.librairies.utils.Resources}.
	 */
	public static Resources getInstance() {
		
		if(instance == null) {
			
			instance = new Resources();
		}
		
		return instance;
	}
	
	/**
	 * Charge les fichiers XML de configuration.
	 */
	public void initAppConfiguration() {
		
		System.setProperty(Resources.STRINGS_FILE, "res/strings.xml");
		System.setProperty(Resources.INTEGERS_FILE, "res/integers.xml");
	}

	/**
	 * Récupère une chaîne de caractères à partir d'un identifiant depuis strings.xml.
	 * @param id identifiant relatif à une chaîne de caractères.
	 * @return une chaîne de caractères.
	 */
	public String getString(String id) {
		
		String result = null;
		
		try {
			
			XmlReader reader = new XmlReader(System.getProperty(Resources.STRINGS_FILE));
			
			result = reader.value(id);
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Récupère un entier à partir d'un identifiant depuis integers.xml.
	 * @param id identifiant relatif à un entier.
	 * @return entier.
	 */
	public int getInt(String id) {
		
		int result = -1;
		
		try {
			
			XmlReader reader = new XmlReader(System.getProperty(Resources.INTEGERS_FILE));
			
			result = Integer.parseInt(reader.value(id));
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
		}
		
		return result;
	}
}
