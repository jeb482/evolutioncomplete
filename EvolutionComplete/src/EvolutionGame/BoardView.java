package EvolutionGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import javax_.vecmath.Point2i;
import javax_.vecmath.Tuple2d;
import javax_.vecmath.Vector2d;
import EvolutionGame.player.Player;

/**
 * 
 * @author Jimmy Briggs
 *
 *	Started from tutorial at:
 *	http://zetcode.com/tutorials/javagamestutorial/basics/
 *
 */


public class BoardView extends JPanel implements Runnable {
	private final int PIXEL_HEIGHT = 500;
	private final int PIXEL_WIDTH = 500;
	private final Color BG_COLOR = Color.BLACK;
	private final int PERIOD = 33;
	private final int INITIAL_DELAY = 0;
	private final Color[] playerColors = {Color.RED, Color.BLUE};
	
	private Thread boardThread;	
	private Board board = new Board();
	
	public BoardView() {
		// Setup board
		System.out.println("Setting up board");
		this.addKeyListener(board);
		this.setFocusable(true);
		setBackground(BG_COLOR);
		setPreferredSize(new Dimension(PIXEL_HEIGHT, PIXEL_HEIGHT));
		setDoubleBuffered(true);
		
		// Make a thread
		boardThread = new Thread(this);
		boardThread.start();
	}
	
	
	public void onFrame(){
		board.onFrame();
		//System.out.println("updating");
	}
	
	public Point2i toPixel(Tuple2d p) {
		return new Point2i((int) ((p.x+1)*250),
		                   (int) ((p.y+1)*250));		 
	}
	
	public int pixelDistance(double l) {
		return (int) (l * 250);
	}
	
	public Point2i pixelVector(Vector2d dir, int numPixels) {
		Point2i out =new Point2i((int) (dir.x*numPixels), (int) (dir.y*numPixels));
		return out;
	}
	
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ArrayList<Player> players = board.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			g.setColor(this.playerColors[i]);
			
			Point2i center = toPixel(p.pos);
			int r = pixelDistance(p.radius);
			Point2i dir = pixelVector(p.dir, r);
			
			g.fillOval(center.x-r, center.y-r, 2*r, 2*r);
			g.setColor(Color.WHITE);
			
			
			g.drawLine(center.x, center.y, center.x+dir.x, center.y+dir.y);
			g.drawString("Player " + (i+1) + " " + p.health + " hp", 20, 20 + 20*i);
			//System.out.println(p.getPixelX());
		}
		
		
		
		for (Projectile p : board.getProjectiles()) {
			Point2i center = toPixel(p.pos);
			int r = pixelDistance(p.radius);
			Point2i dir = pixelVector(p.dir, r);

			g.fillOval(center.x-r, center.y-r, 2*r, 2*r);
			g.setColor(Color.GRAY);
		}
		
	}
	
	public void run() {
		long previousTime, elapsedTime, sleepTime;
		
		previousTime = System.currentTimeMillis();
		
		while (true) {
			onFrame();
			repaint();
			
			elapsedTime = System.currentTimeMillis() - previousTime;
			sleepTime = PERIOD - elapsedTime; // TODO: Scary
			
			
			if (sleepTime < 0) sleepTime = 2;
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println ("Interrupted while sleeping in BoardThread!");
			}
			
			previousTime = System.currentTimeMillis();
			
		}
	}	
	
}
