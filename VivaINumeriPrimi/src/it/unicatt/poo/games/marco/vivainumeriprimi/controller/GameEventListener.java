package it.unicatt.poo.games.marco.vivainumeriprimi.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unicatt.poo.games.marco.vivainumeriprimi.core.Game;
import it.unicatt.poo.games.marco.vivainumeriprimi.graphics.GameBoardGUI;
import it.unicatt.poo.games.marco.vivainumeriprimi.main.Main;

public class GameEventListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Game game = Main.getGame();
		GameBoardGUI gameGUI = Main.getGameBoardGUI();
		
	    if ( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP ) {
            game.onUpInput();
        }
        else if ( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN ) {
            game.onDownInput();
        }
        else if ( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT ) {
            game.onLeftInput();
        }
        else if ( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            game.onRightInput();
        }
        else if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
            game.StartGame();
            gameGUI.setGriglia(game.getGriglia());
        }
	    // Dopo ogni evento, disegna il gioco.
	    gameGUI.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}

