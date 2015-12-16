package EvolutionGame.player;
import javax_.vecmath.Point2d;
import javax_.vecmath.Vector2d;
import util.Vectors;
import EvolutionGame.Board;
import EvolutionGame.Projectile;


public class Player {
	public Point2d pos = new Point2d();
	public Vector2d dir = new Vector2d(); 

	
	public float radius = 1.0f/20;
	public int health;
	private Board parent;
	
	public int turnDir = 0;  // positive for anticlockwise
	public int moveDir = 0;  // forward positive, backward negative
	public boolean missileReady = true;

	public Player(double x, double y, Board parent) {
		pos.x = x;
		pos.y = y;
		this.parent = parent;
		health = 10;
		dir = new Vector2d(1,0);
	}
	
	public void fire() {
		if (this.missileReady) {
			Projectile p = new Projectile(this);
			parent.addProjectile(p);
		}
	}
	
	public void onFrame() {
		if (turnDir > 0) {
			Vectors.rotate(dir,-0.15);
		} else if (turnDir < 0) {
			Vectors.rotate(dir, 0.15);
		}
		
		if (moveDir > 0) {
			pos.x += dir.x/30;
			pos.y += dir.y/30;
		} else if (moveDir < 0) {
			pos.x -= dir.x/30;
			pos.y -= dir.y/30;
		}
	}
}
