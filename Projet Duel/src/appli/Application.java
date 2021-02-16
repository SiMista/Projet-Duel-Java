package appli;

import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		System.out.println(pioche.piocheliste.toString());
		pioche.mélange();
		//test pour la fonction mélange qui marche pas
		//pioche.piocheliste=pioche.mélange(pioche);
		System.out.println(pioche.piocheliste.toString());
	}

	// public void piocherCartes(int cartejouées);
	
}
