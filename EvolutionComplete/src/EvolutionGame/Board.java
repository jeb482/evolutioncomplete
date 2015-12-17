package EvolutionGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import EvolutionGame.player.ComputerPlayer;
import EvolutionGame.player.HumanPlayer;
import EvolutionGame.player.Player;

public class Board implements KeyListener {
	public final float REAL_WIDTH = 2.0f;
	public final float REAL_HEIGHT = 2.0f;	
	
	private boolean humanPlayer = true;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	private Lock projectileLock;
	
	
	
	public Board() {
		// Create Players
		projectileLock = new ReentrantLock();
		
		if (humanPlayer) {
			players.add(new HumanPlayer(-0.75f, 0, this));
		} else {
			players.add(new Player(-0.75f, 0, this));
		}
		players.add(new ComputerPlayer( 0.75f, 0, this));
	}
	
	/**
	 * Returns the first player in the player list that is not the input player.
	 * A lot of assumptions, eh?
	 * 
	 * @param a the player
	 * @return the enemy
	 */
	public Player getEnemy(Player a) {
		for (Player b : players) {
			if (b != a)
				return b;
		}
		return null;
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

		ArrayList<Projectile> deadPs = new ArrayList<Projectile>(); 
		projectileLock.lock();
		try {
			for (Projectile p : projectiles) {			
				p.onFrame();
			
				if (p.pos.x < -2 || p.pos.y< -2 || p.pos.x > 2 || p.pos.y > 2) {
					deadPs.add(p);
					//System.out.println(p.pos + " " + projectiles.size());
				} else {
					for (Player player : this.players) {
						if (p.pos.distance(player.pos) < p.radius + player.radius && p.owner != player) {
							deadPs.add(p);
							player.health -= 1;
						}
					}
				}
			}
		} finally {
			projectileLock.unlock();
		}
		
		for (Projectile p : deadPs) {
			projectileLock.lock();
			try {
				projectiles.remove(p);
			} finally {
				projectileLock.unlock();
			}
		}
	}
	
	
	/**
	 * Thread safe addition of projectile
	 * 
	 * @param p
	 */
	public void addProjectile(Projectile p) {
		projectileLock.lock();
		try {
			projectiles.add(p);
		} finally {
			projectileLock.unlock();
		}
	}
	
	public void removeProjectile(Projectile p) {
		projectileLock.lock();
		try {
			projectiles.remove(p);
		} finally {
			projectileLock.unlock();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (humanPlayer) {
			HumanPlayer human = (HumanPlayer) this.players.get(0);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				human.moveDir = 1;
				break;
			case KeyEvent.VK_A:
				human.turnDir = 1;
				break;
			case KeyEvent.VK_S:
				human.moveDir = -1;
				break;
			case KeyEvent.VK_D:
				human.turnDir = -1;
				break;
			case KeyEvent.VK_SPACE:
				human.fire();
				break;
			default:
				return;
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if (humanPlayer) {
			
			HumanPlayer human = (HumanPlayer) this.players.get(0);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				if (human.moveDir > 0) {
					human.moveDir = 0;
				}
				break;
			case KeyEvent.VK_A:
				if (human.turnDir > 0) {
					human.turnDir = 0;
				};
				break;
			case KeyEvent.VK_S:
				if (human.moveDir < 0) {
					human.moveDir = 0;
				}
				break;
			case KeyEvent.VK_D:
				if (human.turnDir < 0) {
					human.turnDir = 0;
				};
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("Fire released");
				break;
			default: 
				return;
			}		
		}
	}
}
