package lifegame.librairies.utils;

import lifegame.librairies.xml.*;

public final class Resources {
	
	private static Resources instance = null;
	private static final String STRINGS_FILE = "strings_file";
	private static final String INTEGERS_FILE = "integers_file";
	
	private Resources() {
		
	}
	
	public static Resources getInstance() {
		
		if(instance == null) {
			
			instance = new Resources();
		}
		
		return instance;
	}
	
	public void initAppConfiguration() {
		
		System.setProperty(Resources.STRINGS_FILE, "res/strings.xml");
		System.setProperty(Resources.INTEGERS_FILE, "res/integers.xml");
	}

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
