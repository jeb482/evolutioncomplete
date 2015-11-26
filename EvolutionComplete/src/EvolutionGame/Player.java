package EvolutionGame;
import java.awt.Color;


public class Player {
	public float x;
	public float y;
	private Board parent;
//	public Vector2 forward = 
	
	public Player(float x, float y, Board parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
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
