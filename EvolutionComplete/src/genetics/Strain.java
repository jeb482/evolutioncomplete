package genetics;

import java.util.ArrayList;

import EvolutionGame.Behavior;
import EvolutionGame.percept.Percept;

public class Strain {
	private String filename;
	private ArrayList<Behavior> behaviors;
	
	public Strain() {
		this.behaviors = new ArrayList<Behavior>();
	}
	
	public Strain(String fname) {
		filename = fname;
	}
	
	public void write() {

	}
	
	public ArrayList<Behavior> getBehaviors() {
		return this.behaviors;
	}
	
	public void addRandomBehavior() {
		this.behaviors.add(Behavior.createRandomBehavior());
	}
}
