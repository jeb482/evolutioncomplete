package EvolutionGame.behavior;

import EvolutionGame.percept.Percept;
import EvolutionGame.response.Response;


public class Behavior {
	Percept p;
	Response r;
	
	public Behavior(Percept percept, Response response) {
		p = percept;
		r = response;
	}
	
	// TODO: Fill this in
	public static Behavior createRandomBehavior() {
		return new Behavior(null, null);
	}
	
	
	
}
