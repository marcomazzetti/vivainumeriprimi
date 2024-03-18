package it.unicatt.poo.games.marco.vivainumeriprimi.core;


import javax.swing.JPanel;


public class Game extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6922523827180013209L;

	private Griglia griglia;

	private String gameName;

	private boolean isInputEnabled = true; // Flag per abilitazione input

	public Game(String gameName) {
		this.griglia = new Griglia();
		this.gameName = gameName;
	}

	public Griglia getGriglia(){
		return griglia;
	}

	public String gameName() {
		return gameName;
	}

	public void setIsInputEnabled( boolean isInputEnabled ) {
		this.isInputEnabled = isInputEnabled;
	}

	public void StartGame(){
		this.griglia = new Griglia();
		griglia.spawn();
		griglia.spawn();
	}


	public boolean isGameOver() {
		setIsInputEnabled(false);

		int count = griglia.contaCelleNonCombinabili();
		boolean gameOver = count == 16 || griglia.contaNumeriNonPrimi()>= 3 ;

		// Riabilita gli input se non è game over
		if (!gameOver) {
			setIsInputEnabled(true);
		} 
		return gameOver;
	}


	public void onUpInput() {
		if (isInputEnabled) {
			griglia.up();
			griglia.spawn();
		}
	}

	public void onDownInput() {
		if (isInputEnabled) {
			griglia.down();
			griglia.spawn();
		}
	}


	public void onLeftInput() {
		if (isInputEnabled) {
			griglia.left();
			griglia.spawn();
		}
	}

	public void onRightInput() {
		if (isInputEnabled) {
			griglia.right();
			griglia.spawn();
		}
	}
}
