package EvolutionGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import EvolutionGame.player.HumanPlayer;
import EvolutionGame.player.Player;

public class Board implements KeyListener {
	public final float REAL_WIDTH = 2.0f;
	public final float REAL_HEIGHT = 2.0f;	
	
	private boolean humanPlayer = true;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	public Board() {
		// Create Players
		if (humanPlayer) {
			players.add(new HumanPlayer(-0.75f, 0, this));
		} else {
			players.add(new Player(-0.75f, 0, this));
		}
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("A key has been typed");
		// Pass
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
				System.out.println("Fire");
				break;
			default: 
				//System.out.println("A key has been pressed");
		
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
				//System.out.println("A key has been pressed");
			}		
		}
	}
}
