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


public class BoardView extends JPanel implements Runnable{
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
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ArrayList<Player> players = board.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			g.setColor(this.playerColors[i]);
			g.fillOval(p.getPixelX(), p.getPixelY(), 50, 50);
			System.out.println(p.getPixelX());
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
