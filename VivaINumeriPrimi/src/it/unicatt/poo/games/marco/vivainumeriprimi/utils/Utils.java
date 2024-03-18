package it.unicatt.poo.games.marco.vivainumeriprimi.utils;

public class Utils {

	/*dice se un numero è primo*/
	public static boolean isPrimo(int n) {
		boolean risultato = false ;
		int numero_divisori = 1 ;
		int numeroNaturale = Math.abs(n) ;
		for (int i= 2 ; i <= numeroNaturale + 1 ; i = i+1) {
			if (numeroNaturale % i == 0) {
				numero_divisori = numero_divisori + 1 ;
			}
			if (numero_divisori > 2) {
				break ;
			}
		}
		if (numero_divisori == 2) {
			risultato = true ;
		}
		return risultato ;
	}
}

