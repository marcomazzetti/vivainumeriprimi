package it.unicatt.poo.games.marco.vivainumeriprimi.core;

import it.unicatt.poo.games.marco.vivainumeriprimi.beans.Casella;
import it.unicatt.poo.games.marco.vivainumeriprimi.utils.Utils;

public class Griglia {
	public Casella[][] griglia;

	int righe = 4; //(anche colonne perchè è una griglia quadrata)

	int confine = 0;

	public int punteggio = 0;


	/**
	 * Costruttore base - crea una griglia 4x4
	 */
	public Griglia() {

		griglia = new Casella[righe][righe];

		for ( int i = 0; i < griglia.length; i++ ) {
			for ( int j = 0; j < griglia[i].length; j++ ) {
				griglia[i][j] = new Casella();
			}
		}
	}


	//	/**
	//	 * Costruttore base con 2 parametri che crea una griglia dove il giocatore
	//	 * ha già perso (per fare test)
	//	 * 
	//	 * @param lose
	//	 * @param grids
	//	 */
	//	public Griglia( int lose, int righe ) {
	//		this.righe = righe;
	//		griglia = new Casella[righe][righe];
	//		for ( int i = 0; i < griglia.length; i++ ) {
	//			for ( int j = 0; j < griglia[i].length; j++ ) {
	//				griglia[i][j] = new Casella( ( lose + i + j ) * ( i + j ) );
	//			}
	//		}
	//	}


	/**
	 * @Getter 
	 * 
	 * @return griglia
	 */
	public Casella[][] getGriglia() {
		return griglia;
	}


	/**
	 * @Getter
	 * 
	 * @return punteggio
	 */
	public int getPunteggio() {
		return punteggio;
	}


	/**
	 * 
	 * Trova la casella col valore maggiore e ritorna quel valore
	 * @return max
	 */
	public int getCasellaMassima() {
		int max = 0;
		for ( int i = 0; i < griglia.length; i++ ) {
			for ( int j = 0; j < griglia[i].length; j++ ) {
				int valore = griglia[i][j].getValore();
				//				System.out.println("("+ i + "," + j + ") = " + v );
				if ( valore > max ) {
					max = valore;
				}
			}
		}
		return max;
	}


	/**
	 * Stampa la griglia sulla console - per i test
	 * 
	 */
	//		public void print() {
	//			for ( int i = confine; i < griglia.length; i++ ) {
	//				for ( int j = confine; j < griglia[i].length; j++ ) {
	//					String s = griglia[i][j].toString() + " ";
	//					System.out.print( s );
	//				}
	//				System.out.println( "" );
	//			}
	//			System.out.println( "Score: " + punteggio );
	//		}

	/**
	 * Ritorna la griglia come String - usato nella GUI
	 */
	public String toString() {
		String s = "";
		for ( int i = confine; i < griglia.length; i++ ) {
			for ( int j = confine; j < griglia[i].length; j++ ) {
				s += griglia[i][j].toString() + " ";
			}
			s += "\n";
		}
		return s;
	}


	/**
	 * Spawna un 2 o un 3 in una casella vuota ogni volta che viene fatta una mossa
	 */
	public void spawn() {
		boolean spawnato = false; 
		//tentativi di spawnare sono utili per assicurarmi di non bloccarmi in un loop infinito
		int tentativi = 0;
		while (!spawnato && tentativi < 1000) {
			int row = (int)( Math.random() * righe ); //riga random da 0 a 3 in cui spawnare
			int col = (int)( Math.random() * righe ); //colonna random da 0 a 3 in cui spawnare
			double x = Math.random(); //numero random da 0 a 1
			if ( griglia[row][col].getValore() == 0 ) { //spawno solo se la casella è vuota 
				if ( x < 0.2 ) { //20% di probabilità che spawni un 3
					griglia[row][col] = new Casella( 3 );
					spawnato = true;
				}
				else {//80% di probabilità che spawni un 2
					griglia[row][col] = new Casella( 2 );
					spawnato = true;
				}
			}
			tentativi++;
		}
	}

	/**
	 * Controlla se la partita è finita - cioè controlla se qualsiasi casella (che non sia vuota) 
	 * possa essere combinata con le caselle vicine - se nessuna casella può essere combinata, 
	 * la partita è finita.
	 *
	 * @return boolean
	 */
	public int contaCelleNonCombinabili() {

		int count = 0;

		for ( int i = 0; i < griglia.length; i++ ) {
			for ( int j = 0; j < griglia[i].length; j++ ) {

				int valore = griglia[i][j].getValore();

				if ( valore > 0 ) {

					if ( i == 0 && j == 0 ) {  //la prima casella 
						if ( valore != griglia[i + 1][j].getValore()  //se non può essere combinata con quella sotto
								&& valore != griglia[i][j + 1].getValore() ) { // e neanche con quella a destra
							count++; //aumento il counter
						}
					}
					else if ( i == 0 && j == 3 ) {
						if ( valore != griglia[i + 1][j].getValore()
								&& valore != griglia[i][j - 1].getValore() ) {
							count++;
						}
					}
					else if ( i == 3 && j == 3 ) {
						if ( valore != griglia[i - 1][j].getValore()
								&& valore != griglia[i][j - 1].getValore() ) {
							count++;
						}
					}
					else if ( i == 3 && j == 0 ) {
						if ( valore != griglia[i - 1][j].getValore()
								&& valore != griglia[i][j + 1].getValore() ) {
							count++;
						}
					}
					else if ( i == 0 && ( j == 1 || j == 2 ) ) {
						if ( valore != griglia[i + 1][j].getValore()
								&& valore != griglia[i][j + 1].getValore()
								&& valore != griglia[i][j - 1].getValore() ) {
							count++;
						}
					}
					else if ( i == 3 && ( j == 1 || j == 2 ) ) {
						if ( valore != griglia[i - 1][j].getValore()
								&& valore != griglia[i][j + 1].getValore()
								&& valore != griglia[i][j - 1].getValore() ) {
							count++;
						}
					}
					else if ( j == 0 && ( i == 1 || i == 2 ) ) {
						if ( valore != griglia[i][j + 1].getValore()
								&& valore != griglia[i - 1][j].getValore()
								&& valore != griglia[i + 1][j].getValore() ) {
							count++;
						}
					}
					else if ( j == 3 && ( i == 1 || i == 2 ) ) {
						if ( valore != griglia[i][j - 1].getValore()
								&& valore != griglia[i - 1][j].getValore()
								&& valore != griglia[i + 1][j].getValore() ) {
							count++;
						}
					}
					else {
						if (valore != griglia[i][j - 1].getValore()
								&& valore != griglia[i][j + 1].getValore()
								&& valore != griglia[i - 1][j].getValore()
								&& valore != griglia[i + 1][j].getValore() ) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}


	/**
	 * Questo metodo è chiamato quando viene premuto "w" o la freccia in su - 
	 * scorre l'intero tabellone e chiama verticalMove con un parametro
	 *  "up" per ciascuna casella
	 */
	public void up() {
		for ( int i = 0; i < righe; i++ ) { //controllo ogni colonna 
			confine = 0;  //mi serve per capire se c'è spazio sopra (ogni volta che viene fatto uno spostamento, viene aggiornato)
			for ( int j = 0; j < righe; j++ ) { //scendendo 
				if ( griglia[j][i].getValore() != 0 ) {  //per ogni casella non vuota verifico 
					if ( confine <= j ) { //se posso alzarla (se c'è spazio sopra)
						verticalMove( j, i, "up" );
					}
				}
			}
		}
	}


	/**
	 * 
	 * Questo metodo è chiamato quando viene premuto "s" o la freccia in giù - 
	 * scorre l'intero tabellone e chiama verticalMove con un parametro
	 *  "down" per ciascuna casella
	 */
	public void down() {
		for ( int i = 0; i < righe; i++ ) { //controllo ogni colonna	
			confine = ( righe - 1 );
			for ( int j = righe - 1; j >= 0; j-- ) { //salendo 
				if ( griglia[j][i].getValore() != 0 ) { //per ogni casella non vuota verifico
					if ( confine >= j ) {//se posso abbassarla (se c'è spazio sotto)
						verticalMove( j, i, "down" );
					}
				}
			}
		}
	}


	/**
	 * Compara i valori di due caselle vicine e se sono uguali o se una è
	 * uguale a 0 (piastrella semplice) - i loro valori vengono sommati -1  (a condizione che le caselle che
	 * stiamo confrontando siano due caselle contentenenti numeri primi o 0 e si stanno muovendo verso
	 * direzione appropriata) 
	 * Nel caso siano un numero primo per mandarlo via bisogna sommarlo a un 2
	 * - Utilizza la ricorsione per scorrere l'intera colonna
	 *
	 * @param row
	 * riga della cella da spostare
	 *@param col
	 * colonna della cella da spostare
	 * @param direction
	 */
	private void verticalMove( int row, int col, String direction )
	{
		Casella casellaArrivo = griglia[confine][col]; //casella di arrivo
		Casella casellaPartenza = griglia[row][col];   //casella di partenza

		if  ( /*qua ho le condizioni in base alle quali si può fare uno spostamento verticale:
				//se la casella di arrivo è vuota, se due caselle hanno lo stesso valore oppure se 
				// una casella contiene un numero non primo e l'altra un 2 */

				casellaArrivo.getValore() == 0 || 
				casellaArrivo.getValore() == casellaPartenza.getValore() ||
				(Utils.isPrimo(casellaArrivo.getValore()) == false && casellaPartenza.getValore() == 2) ||
				(casellaArrivo.getValore() == 2 && Utils.isPrimo(casellaPartenza.getValore()) == false)) {

			if ( //se le caselle sono piene, non sono numeri primi e hanno lo stesso valore, non faccio nulla, non si possono somamare!
					//(questo perchè i numeri non primi per scacciarli devo sommarli a un 2)

					casellaArrivo.getValore() != 0 && casellaPartenza.getValore() != 0 &&
					Utils.isPrimo(casellaArrivo.getValore()) == false && Utils.isPrimo(casellaPartenza.getValore()) == false &&
					casellaArrivo.getValore() == casellaPartenza.getValore() ) {


			}
			else { //altrimenti si possono sommare
				if  ( // Questa condizione controlla se la riga della casella di partenza è maggiore del confine (ovvero, se la casella
						// ha spazio sopra di sè) oppure se la direzione è "down" e la riga della casella di partenza è minore del confine
						// (ovvero, ha spazio sotto di sè).
						// Se la condizione è vera, significa che le celle sono disposte correttamente per effettuare lo spostamento verticale.
						row > confine || ( direction.equals( "down" ) && ( row < confine ) )) {

					int addScore = casellaArrivo.getValore() + casellaPartenza.getValore(); //calcolo del punteggio da aggiungere/del 
					// valore da inserire nella casella di arrvo

					if ( casellaArrivo.getValore() != 0 ) { //se la casella non è vuota ci sono due casi:
						// 1) una casella ha il 2 e l'altra non è un numero primo (si sommano normalmente)
						// 2) hanno lo stesso valore (fanno la somma - 1)

						if ((casellaArrivo.getValore() == 2 && Utils.isPrimo(casellaPartenza.getValore()) == false) ||
								(Utils.isPrimo(casellaArrivo.getValore()) == false && casellaPartenza.getValore() == 2)) {
							casellaArrivo.setValore(addScore);
						}
						else {
							casellaArrivo.setValore( addScore - 1);
						}
						punteggio += addScore;
					}
					else { // se la casella d'arrivo è vuota, il suo valore è quello dato dalla somma della casella di partenza con 0
						casellaArrivo.setValore( addScore);
					}

					//ad ogni spostamento, la casella di partenza si svuota
					casellaPartenza.setValore( 0 );
				}
			}
		} 
		else {
			if ( direction.equals( "down" ) ) //se muovo in basso il confine si alza (la riga diminuisce)
			{
				confine--;
			}
			else { //se muovo in alto il confine si abbassa
				confine++;
			}
			verticalMove( row, col, direction );
		}
	}



	/**
	 * Questo metodo è chiamato quando viene premuto "a" o la freccia verso sinistra - 
	 * scorre l'intero tabellone e chiama horizontallMove con un parametro
	 *  "left" per ciascuna casella
	 */

	public void left() {
		for ( int i = 0; i < righe; i++ ) {
			confine = 0;
			for ( int j = 0; j < righe; j++ ) {
				if ( griglia[i][j].getValore() != 0 ) {
					if ( confine <= j ) {
						horizontalMove( i, j, "left" );
					}
				}
			}
		}
	}


	/**
	 * Questo metodo è chiamato quando viene premuto "d" o la freccia verso destra - 
	 * scorre l'intero tabellone e chiama horizontallMove con un parametro
	 *  "right" per ciascuna casella
	 */
	public void right() {
		for ( int i = 0; i < righe; i++ ) {
			confine = ( righe - 1 );
			for ( int j = ( righe - 1 ); j >= 0; j-- ) {
				if ( griglia[i][j].getValore() != 0 ) {
					if ( confine >= j ) {
						horizontalMove( i, j, "right" );
					}
				}
			}
		}
	}


	/**
	 * come la verticalMove ma per mosse orizzontali
	 */
	private void horizontalMove( int row, int col, String direction ) {

		Casella casellaArrivo = griglia[row][confine];
		Casella casellaPartenza = griglia[row][col];

		if ( casellaArrivo.getValore() == 0 ||
				(casellaArrivo.getValore() == casellaPartenza.getValore() ||
				(Utils.isPrimo(casellaArrivo.getValore()) == false && casellaPartenza.getValore() == 2) ||
				(casellaArrivo.getValore() == 2 && Utils.isPrimo(casellaPartenza.getValore()) == false))) {

			if (casellaArrivo.getValore() != 0 && casellaPartenza.getValore() != 0 &&
					Utils.isPrimo(casellaArrivo.getValore()) == false && Utils.isPrimo(casellaPartenza.getValore()) == false &&
					casellaArrivo.getValore() == casellaPartenza.getValore() ) {

			}
			else {
				if ( col > confine || ( direction.equals( "right" ) && ( col < confine ) ) ) {

					int addScore = casellaArrivo.getValore() + casellaPartenza.getValore();

					if ( casellaArrivo.getValore() != 0 ) {
						if ((casellaArrivo.getValore() == 2 && Utils.isPrimo(casellaPartenza.getValore()) == false) ||
								(Utils.isPrimo(casellaArrivo.getValore()) == false && casellaPartenza.getValore() == 2)) {
							punteggio += addScore;
							casellaArrivo.setValore(addScore);
						}
						else {
							punteggio += addScore;
							casellaArrivo.setValore( addScore - 1);
						}
					}
					if (casellaArrivo.getValore() == 0) {
						casellaArrivo.setValore( addScore);
					}

					casellaPartenza.setValore( 0 );
				}
			}
		}
		else {
			if ( direction.equals( "right" ) ) {
				confine--;
			}
			else {
				confine++;
			}
			horizontalMove( row, col, direction );
		}
	}

	/**
	 * conta quanti numeri non primi ci sono nella griglia.
	 * @return
	 */
	public int contaNumeriNonPrimi() {
		int count = 0;
		for ( int i = confine; i < griglia.length; i++ ) {
			for ( int j = confine; j < griglia[i].length; j++ ) {
				if ( griglia[i][j].getValore() > 0 && !Utils.isPrimo(griglia[i][j].getValore())) {
					count++;
				}

			}
		}
		return count;
	}

}