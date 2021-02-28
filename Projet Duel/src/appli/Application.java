package appli;

import duel.Pioche;
import duel.Joueur;

public class Application {

	public static void main(String[] args) {
		Joueur NORD = new Joueur("NORD");
		Joueur SUD = new Joueur("SUD");

		//System.out.println(NORD.getPioche().getPiocheListe().toString());
		NORD.jouerCartes();
	}

	// public void piocherCartes(int cartejouées);
	
}
