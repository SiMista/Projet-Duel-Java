package appli;

import duel.Pioche;
import duel.Joueur;

public class Application {

	public static void main(String[] args) {
		Joueur NORD = new Joueur("NORD");
		Joueur SUD = new Joueur("NORD");

		//System.out.println(NORD.getPioche().getPiocheListe().toString());
		
		//Je sais pas pourquoi ça marche pas
		System.out.println(toString(NORD));
		System.out.println(toString(SUD));

	}

	// public void piocherCartes(int cartejouées);
	
}
