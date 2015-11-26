package EvolutionGame;

import java.util.ArrayList;

public class Board {
	public final float REAL_WIDTH = 2.0f;
	public final float REAL_HEIGHT = 2.0f;	
	
	private boolean humanPlayer = false;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	
	
	public Board() {
		// Create Players
		players.add(new Player(-0.75f, 0, this));
		players.add(new Player( 0.75f, 0, this));
	
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	} 
	
	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}
	
	public void onFrame() {
		for (Player p : players) {
			p.onFrame();
		}
		
		for (Projectile p : projectiles) {
			p.onFrame();
		}
	}
}
