package EvolutionGame.percept;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Percept {
	public abstract boolean percieve();
	public abstract Element toXML(Document doc);
}
