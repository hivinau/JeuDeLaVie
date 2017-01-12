package lifegame.librairies.xml;

import java.util.*;
import javax.xml.parsers.*;

public abstract class ResourcesReader {

	protected final DocumentBuilder builder;
	
	public ResourcesReader() throws FactoryConfigurationError, ParserConfigurationException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}
	
	public abstract Map<String, Object> configuration();
	public abstract String value(String attribute);
}
