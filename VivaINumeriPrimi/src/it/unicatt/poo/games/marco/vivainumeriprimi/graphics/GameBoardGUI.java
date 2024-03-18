package it.unicatt.poo.games.marco.vivainumeriprimi.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unicatt.poo.games.marco.vivainumeriprimi.beans.Casella;
import it.unicatt.poo.games.marco.vivainumeriprimi.controller.GameEventListener;
import it.unicatt.poo.games.marco.vivainumeriprimi.core.Griglia;
import it.unicatt.poo.games.marco.vivainumeriprimi.main.Main;

public class GameBoardGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private Griglia gameBoard;

    private static JFrame frame = new JFrame( "2048" );
    
    private GameEventListener eventListener;
    
    
    public GameBoardGUI (Griglia gameBoard ) {
    	this.gameBoard = gameBoard;
    	this.eventListener = new GameEventListener();
    }

	/**
     * imposta la GUI con dimensioni appropriate e aggiunge un Key Listener
     */
    public void setupGUI() {
        frame.addKeyListener( eventListener );
        frame.getContentPane().add( this );
        frame.setSize( 600, 400 );
        frame.setVisible( true );
        frame.setResizable( false );
    }

    /**
     *  Dipinge la GUI con una serie di stringhe, la griglia, le caselle e garantisce
     * che essi vengono ridipinti alla fine del gioco
     */
    public void paint( Graphics g ) {
        super.paint( g );
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString( "Viva i numeri primi", 250, 20 );
        g2.drawString( "Punteggio: " + gameBoard.getPunteggio(), 200 - 4 * String.valueOf( gameBoard.getPunteggio() ).length(), 40 );
        g2.drawString( "Casella massima: " + gameBoard.getCasellaMassima(), 280 - 4 * String.valueOf( gameBoard.getCasellaMassima() ).length(), 40 );
        g2.drawString( "Premi 'Invio' per iniziare/resettare", 210, 315 );
        g2.drawString( "Usa 'wasd' o le frecce per muovere", 180, 335 );

        g2.setColor( Color.gray );
        g2.fillRect( 140, 50, 250, 250 );
        for ( int i = 0; i < 4; i++ ) {
            for ( int j = 0; j < 4; j++ ) {
                drawTiles( g, gameBoard.griglia[i][j], j * 60 + 150, i * 60 + 60 );
            }
        }
        if ( Main.getGame().isGameOver() ) {
            g2.setColor( Color.gray );
            g2.fillRect( 140, 50, 250, 250 );
            for ( int i = 0; i < 4; i++ ) {
                for ( int j = 0; j < 4; j++ ) {
                    g2.setColor( Color.RED );
                    g2.fillRoundRect( j * 60 + 150, i * 60 + 60, 50, 50, 5, 5 );
                    g2.setColor( Color.black );
                    g.drawString( "GAME", j * 60 + 160, i * 60 + 75 );
                    g.drawString( "OVER", j * 60 + 160, i * 60 + 95 );
                }
            }
        }
    }


    /**
     * disegna una singola casella - chiamata dal metodo paint
     */
    public void drawTiles( Graphics g, Casella tile, int x, int y ) {
        int tileValue = tile.getValore();
        int length = String.valueOf( tileValue ).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor( Color.lightGray );
        g2.fillRoundRect( x, y, 50, 50, 5, 5 );
        g2.setColor( Color.black );
        if ( tileValue > 0 ) {
            g2.setColor( tile.getColore() );
            g2.fillRoundRect( x, y, 50, 50, 5, 5 );
            g2.setColor( Color.black );
            g.drawString( "" + tileValue, x + 25 - 3 * length, y + 25 );
        }
    }

    public void setGriglia(Griglia griglia) {
    	this.gameBoard = griglia;
    }
}

