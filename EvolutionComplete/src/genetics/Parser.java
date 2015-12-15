package genetics;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import EvolutionGame.percept.Percept;

public class Parser {
	public static Strain loadStrain(String filename) {
		
		return new Strain();
	}
	
	/**
	 * 
	 * Skeleton lifted from http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	 * Consulted code in Wakame, a port of Nori (by Wensel Jakob) to Java by Pramook Khungurn under GNU GPL
	 * 
	 * 
	 * @param s
	 * @param filename
	 */
	public static void save(Strain s, String filename) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("strain");
			
			//for (Percept p : s.getPercepts()) {
			//	rootElement.appendChild(p.toXML());
			//}
		}
			
		catch (ParserConfigurationException pce) {
			

		}
	}

}
