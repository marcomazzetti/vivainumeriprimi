package it.unicatt.poo.games.marco.vivainumeriprimi.beans;

import java.awt.Color;

public class Casella {

	int valore;
	Color coloreCasella;

	/**
	 * Costruisce una casella vuota (valore 0)
	 */
	public Casella() {
		valore = 0;
	}


	/**
	 * Costruisce una casella con valore un certo numero
	 * 
	 * @param numero     
	 */
	public Casella(int numero) {
		valore = numero;
	}


	/**
	 * @getter
	 * 
	 * @return valore
	 */
	public int getValore() {
		return valore;
	}


	/**
	 * @setter (usato quando combino due caselle)
	 * 
	 * @param valore
	 */
	public void setValore(int valore) {
		this.valore = valore;
	}


	/**
	 * Rappresenta la casella come stringa (usata nella GUI)
	 */
	public String toString() {
		return "" + valore;
	}


	/**
	 * Modifica il colore di una casella basandosi sul valore.
	 */
	public void setColore() {
		int valore = this.getValore();

		switch (valore) {
		case 2:
			coloreCasella = new Color(238, 228, 218);
			break;
		case 3:
			coloreCasella = new Color(237, 224, 200);
			break;
		case 5:
			coloreCasella = new Color(242, 177, 121);
			break;
		case 11:
			coloreCasella = new Color(245, 149, 99);
			break;
		case 23:
			coloreCasella = new Color(246, 124, 95);
			break;
		case 47:
			coloreCasella = new Color(246, 94, 59);
			break;
		case 97:
			coloreCasella = new Color(237, 207, 114);
			break;
		case 193:
			coloreCasella = new Color(237, 204, 97);
			break;
		case 389:
			coloreCasella = new Color(237, 200, 80);
			break;
		case 787:
			coloreCasella = new Color(237, 197, 63);
			break;
		default: //default comprende tutti i numeri non primi, li coloro di blu per differenziarli
			coloreCasella = new Color( 0, 0, 255 ) ;
			break;
		}
	}


	/**
	 * @return coloreCasella
	 */
	public Color getColore() {
		this.setColore();
		return coloreCasella;
	}

}
