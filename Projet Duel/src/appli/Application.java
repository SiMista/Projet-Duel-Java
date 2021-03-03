package appli;

import duel.Pioche;
import duel.Règles;
import duel.Joueur;

public class Application {

	public static void main(String[] args) {
		Joueur NORD = new Joueur("NORD");
		Joueur SUD = new Joueur("SUD");
		System.out.println(NORD.afficherPiles());
		System.out.println(SUD.afficherPiles());
		Règles.jouerCartes();

	}	
}
