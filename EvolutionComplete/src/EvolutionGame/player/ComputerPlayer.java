package EvolutionGame.player;

import javax_.vecmath.Vector2d;

import EvolutionGame.Actor;
import EvolutionGame.Board;

public class ComputerPlayer extends Player{		
	public ComputerPlayer(float x, float y, Board parent) {
		super(x, y, parent);
	}
	
	@Override
	public void onFrame() {
		//this.turnDir = 1;
		//this.moveDir = 1;
		//this.missileReady = true;
		//this.fire();
		//aim(this.parent.getPlayers().get(0));
		super.onFrame();
		
		
	}
	
	public void strafe(Actor a) {
		Vector2d distanceToA = new Vector2d();
		distanceToA.sub(a.pos, pos);
		if ((dir.dot(a.dir) < 1) && (a.dir.dot(distanceToA) < 0)) { 
			if (util.Vectors.signCross(dir, a.dir) > 0) {
				this.turnDir = -1;
			} else {
				this.turnDir = 1;
			}
		} else if ((dir.dot(a.dir) < 1) && (a.dir.dot(distanceToA) < 0)) {
			if (util.Vectors.signCross(dir, a.dir) > 0) {
				this.turnDir = 1;
			} else {
				this.turnDir = -1;
			}
		}
		moveDir = 1;
	}
			
	
	public void aim(Actor a) {
		Vector2d aLocal = new Vector2d();
		
		aLocal.sub(a.pos, this.pos);
		aLocal.normalize();
//		System.out.println(util.Vectors.cross(dir, aLocal));
		if (util.Vectors.sinTheta(dir, aLocal) > .1) {
			turnDir = -1;
			System.out.println(util.Vectors.cross(dir, aLocal));
		} else if (util.Vectors.sinTheta(dir, aLocal) < -.1) {
			turnDir = 1;
		}
	}
	
	public void seek(Actor a) {
		aim(a);
		moveDir = 1;
	}
	
	public void backpedal(Actor a) {
		aim(a);
		moveDir = -1;
	}
	
	/**
	 * Returns true iff the target actor is within the specified angle of this.dir
	 * 
	 * @param a
	 * @param cosAlpha the cosine of the maximum angle
	 * @return
	 */
	public boolean withinArc(Actor a, double cosAlpha) {
		Vector2d dirToA = new Vector2d();
		dirToA.sub(a.pos, this.pos);
		dirToA.normalize();
		double cosTheta = dirToA.dot(this.dir); 
		return (cosTheta > cosAlpha);
	}
	
	/**
	 * Returns true iff this is within rho of a
	 * @param a
	 * @param rho
	 * @return
	 */
	public boolean withinDistance(Actor a, double rho) {
		return this.pos.distance(a.pos) < rho;
	}
	
}
