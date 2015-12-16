package EvolutionGame.percept;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public  class Percept {
	public boolean percieve() { return true;};
	public Element toXML(Document doc) {return doc.createElement("la");}

	
	public static Percept LoadPercept(String type, HashMap<String, Object> properties) {
		
	}
}
