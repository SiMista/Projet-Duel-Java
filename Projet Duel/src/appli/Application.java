package appli;

import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		System.out.println(pioche.piocheliste.toString());
		pioche.m�lange();
		//test pour la fonction m�lange qui marche pas
		//pioche.piocheliste=pioche.m�lange(pioche);
		System.out.println(pioche.piocheliste.toString());
	}

	// public void piocherCartes(int cartejou�es);
	
}
