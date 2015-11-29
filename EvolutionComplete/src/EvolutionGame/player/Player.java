package EvolutionGame.player;
import EvolutionGame.Board;
import util.Vector2;


public class Player {
	public float x;
	public float y;
	private int health;
	private Board parent;
	public Vector2 dir = new Vector2(); 
	
	public Player(float x, float y, Board parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.health = 10;
	}
	
	public void onFrame() {
		x += .005;
		y += .005;
	}
	
	public int getPixelX() {
		// TODO: Using a temporary value
		return (int) ((0.5f*x+1)*250);
	}
	
	public int getPixelY() {
		// TODO: Using a temporary value
		return (int) ((0.5f*y+1)*250);
	}
}
