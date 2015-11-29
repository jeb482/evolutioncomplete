package EvolutionGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import EvolutionGame.player.Player;

/**
 * 
 * @author Jimmy Briggs
 *
 *	Started from tutorial at:
 *	http://zetcode.com/tutorials/javagamestutorial/basics/
 *
 */


public class BoardView extends JPanel implements Runnable, KeyListener{
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
		this.addKeyListener(this);
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
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ArrayList<Player> players = board.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			g.setColor(this.playerColors[i]);
			g.fillOval(p.getPixelX(), p.getPixelY(), 50, 50);
			//System.out.println(p.getPixelX());
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


	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("A key has been typed");
		// Pass
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				System.out.println("Up");
				break;
			case KeyEvent.VK_A:
				System.out.println("Left");
				break;
			case KeyEvent.VK_S:
				System.out.println("Down");
				break;
			case KeyEvent.VK_D:
				System.out.println("Right");
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("Fire");
				break;
			default: 
				System.out.println("A key has been pressed");
		}
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
	//	System.out.println("A key has been released");
		
	}
	
	
}
