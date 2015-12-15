package EvolutionGame.percept;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DummyPercept extends Percept {
	int someInt = 5;
	String someString = "Hello world;";
	double someReal = 42.0;
	
	@Override
	public Element toXML(Document doc) {
		Element e = doc.createElement("DummyPercept");
		
		return null;
	}

	@Override
	public boolean percieve() {
		// TODO Auto-generated method stub
		return false;
	}

}
