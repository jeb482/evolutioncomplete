package genetics;

import java.util.ArrayList;

import EvolutionGame.behavior.Behavior;
import EvolutionGame.percept.Percept;

public class Strain {
	private ArrayList<Behavior> behaviors;
	
	public Strain() {
		this.behaviors = new ArrayList<Behavior>();
	}
	
	public ArrayList<Behavior> getBehaviors() {
		return this.behaviors;
	}
	
	public void addBehavior(Behavior b) {
		this.behaviors.add(b);
	}
	
	public void addRandomBehavior() {
		addBehavior(Behavior.createRandomBehavior());
	}
}
