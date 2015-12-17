package EvolutionGame;

import javax_.vecmath.Point2d;
import javax_.vecmath.Vector2d;

import EvolutionGame.player.Player;

public class Projectile extends Actor {
	final Vector2d velocity;
	final double radius = 0.01;
	
	private Board parent;
	Player owner;

	public Projectile(Player owner) {
		this.owner = owner;
		this.dir = new Vector2d(owner.dir);
		
		this.pos = new Point2d(dir);
		pos.scale(radius);
		pos.add(owner.pos);
		
		velocity = new Vector2d(dir);
		velocity.scale(0.04);
	}
	
	public void onFrame() {
		this.pos.add(velocity);
	}
}
