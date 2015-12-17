package EvolutionGame.player;
import javax_.vecmath.Point2d;
import javax_.vecmath.Vector2d;
import util.Constants;
import util.Vectors;
import EvolutionGame.Actor;
import EvolutionGame.Board;
import EvolutionGame.Projectile;


public class Player extends Actor {
	public float radius = 1.0f/20;
	public int health;
	protected Board parent;
	
	public int turnDir = 0;  // positive for anticlockwise
	public int moveDir = 0;  // forward positive, backward negative
	public long lastFired = 0;

	public Player(double x, double y, Board parent) {
		pos.x = x;
		pos.y = y;
		this.parent = parent;
		health = 10;
		dir = new Vector2d(1,0);
	}
	
	public boolean missileReady() {
		return (lastFired + Constants.COOLDOWN < System.currentTimeMillis()); 
	}
	
	public void fire() {
		if (missileReady()) {
			Projectile p = new Projectile(this);
			parent.addProjectile(p);
			lastFired = System.currentTimeMillis();
		}
	}
	
	public void onFrame() {
		if (turnDir > 0) {
			Vectors.rotate(dir,-0.2);
		} else if (turnDir < 0) {
			Vectors.rotate(dir, 0.2);
		}
		
		if (moveDir > 0) {
			pos.x = Math.min(Math.max(pos.x+dir.x/40,-1+radius), 1-radius);
			pos.y = Math.min(Math.max(pos.y+dir.y/40,-1+radius), 1-radius);
			
		} else if (moveDir < 0) {
			pos.x = Math.min(Math.max(pos.x+-dir.x/40,-1+radius), 1-radius);
			pos.y = Math.min(Math.max(pos.y-dir.y/40,-1+radius), 1-radius);
		}
	}
}
