package EvolutionGame;
import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * 
 * @author Jimmy Briggs
 *
 *	Started from tutorial at:
 *	http://zetcode.com/tutorials/javagamestutorial/basics/
 *
 */
public class MainGame extends JFrame {
	public MainGame() {
		add(new Board());
		setSize(500, 500);
		
		// Don't let this resize. Pack fits to child node
		setResizable(false);
		pack();
		
		
		setTitle("Evolution Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainGame game = new MainGame();
				game.setVisible(true);
			}
		});
		
	}
}
