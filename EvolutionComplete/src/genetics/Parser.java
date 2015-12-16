package genetics;

import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import EvolutionGame.behavior.Behavior;
import EvolutionGame.percept.Percept;
import EvolutionGame.response.Response;

public class Parser {	

	
	public static Strain loadStrain(Path filepath) {
		Strain s = new Strain();
		try {
			// Open the XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();

			Document doc = docBuilder.parse(filepath.toFile());			
			doc.getDocumentElement().normalize();
			Element rootNode = doc.getDocumentElement();
			
			// Create behaviors
			NodeList bs = rootNode.getElementsByTagName("Behavior");
			for (int i = 0; i < bs.getLength(); i++) {
				s.addBehavior(parseBehavior((Element) bs.item(i)));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
		
	}
	
	/**
	 * Return a behavior read from the given XML element
	 * 
	 * @param e element
	 * @return behavior
	 */
	private static Behavior parseBehavior(Element e) {
		Element p  = (Element) e.getElementsByTagName("Percept").item(0);
		Element r = (Element) e.getElementsByTagName("Response").item(0);
		return new Behavior(parsePercept(p), parseResponse(r));
	}
	
	/**
	 * Return a percept read from the XML element
	 * 
	 * @param e
	 * @return
	 */
	private static Percept parsePercept(Element e) {
		String type = e.getAttribute("type");
		return new Percept();
	}
	
	/**
	 * Return a response read from the XML element
	 * @param e
	 * @return
	 */
	private static Response parseResponse(Element e) {
		String type = e.getAttribute("type");
		return new Response();
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
			// Open the XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();

		
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Strain");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
