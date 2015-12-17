package EvolutionGame.player;

import EvolutionGame.Board;

public class ConstructedPlayer extends ComputerPlayer {
	private double cosAlphaArc = 0.1;
	private double maxRange;
	
	public ConstructedPlayer(float x, float y, Board parent) {
		super(x, y, parent);
	}
	
	@Override
	public void onFrame() {
		Player enemy = parent.getEnemy(this);
		if (this.missileReady &&  withinArc(enemy, cosAlphaArc) 
				&& withinDistance(enemy, maxRange)) {
			
		}
		
		
		
		
		
		
		
		
		
		super.onFrame();
	}
	
	
	

}
