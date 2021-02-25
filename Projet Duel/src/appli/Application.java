package appli;

import duel.Pioche;
import duel.Joueur;

public class Application {

	public static void main(String[] args) {
		Joueur NORD = new Joueur("NORD");
		System.out.println(NORD.getPioche().getPiocheListe().toString());
		System.out.println(NORD.toString());
	}

	// public void piocherCartes(int cartejouées);
	
}
