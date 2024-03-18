package it.unicatt.poo.games.marco.vivainumeriprimi.main;

import javax.swing.SwingUtilities;

import it.unicatt.poo.games.marco.vivainumeriprimi.core.Game;
import it.unicatt.poo.games.marco.vivainumeriprimi.graphics.GameBoardGUI;


public class Main {
	
	private static Game game;
	private static GameBoardGUI gameGUI;
	
    public static void main( String[] args ) {
    	game = new Game("Viva i numeri primi");
    	
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
		    	gameGUI = new GameBoardGUI(game.getGriglia());
		    	gameGUI.setupGUI();
			}
    	});
    }
    
    public static Game getGame() {
    	return game;
    }
    
    public static GameBoardGUI getGameBoardGUI() {
    	return gameGUI;
    }
}

