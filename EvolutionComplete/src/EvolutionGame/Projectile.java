package EvolutionGame;

import EvolutionGame.player.Player;
import util.Vector2;

public class Projectile {
	int x;
	int y;
	final Vector2 direction;
	private Board parent;
	Player owner;

	public Projectile(Player owner) {
		this.owner = owner;
		this.direction = new Vector2(owner.dir);
		this.x = owner.getPixelX();
		this.y = owner.getPixelY();
	}
	
	public void onFrame() {
		
	}
}
