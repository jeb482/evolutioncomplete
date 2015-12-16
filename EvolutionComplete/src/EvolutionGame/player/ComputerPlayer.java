package EvolutionGame.player;

import EvolutionGame.Board;

public class ComputerPlayer extends Player{		
		public ComputerPlayer(float x, float y, Board parent) {
			super(x, y, parent);
		}
		
		@Override
		public void onFrame() {
			this.turnDir = 1;
			this.moveDir = 1;
			this.missileReady = true;
			this.fire();
			super.onFrame();
			
			
		}

}
