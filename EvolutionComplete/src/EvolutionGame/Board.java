package EvolutionGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * @author Jimmy Briggs
 *
 *	Started from tutorial at:
 *	http://zetcode.com/tutorials/javagamestutorial/basics/
 *
 */


public class Board extends JPanel implements Runnable{
	private final int HEIGHT = 500;
	private final int WIDTH = 500;
	private final Color BG_COLOR = Color.WHITE;
	private final int PERIOD = 33;
	private final int INITIAL_DELAY = 0;
	
	private Thread boardThread;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	
	public Board() {
		// Setup board
		setBackground(BG_COLOR);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDoubleBuffered(true);
		
		// Create Players
		players.add(new Player(Color.RED));
		players.add(new Player(Color.BLUE));
		
		// Make a thread
		boardThread = new Thread(this);
		boardThread.start();
	}
	
	
	public void onFrame(){
		for (Player p : players) {
			p.x += 1;
			p.y -= 1;
		}
		System.out.println("updating");
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Player p : players) {
			g.setColor(p.teamColor);
			g.drawOval(p.x, p.y, 100, 100s);
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
