package duel;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private ArrayList<Integer> main;
	private Pioche pioche;
	private int pileAscendante;
	private int pileDescendante;

	public Joueur(String nomDuJoueur) {
		nom = nomDuJoueur;
		main = new ArrayList<Integer>();
		pioche = new Pioche();
		pileAscendante = 1;
		pileDescendante = 60;
		piocher(6);
	}

	public void piocher(int nb) {
		int i;
		for (i = 0; i < nb; i++) {
			if (pioche.getPiocheListe().size() > 0) {
				main.add(i, (pioche.getCartesPioche(i)));
				pioche.getPiocheListe().remove(i);
				}
			else 
				break;	
		}
		System.out.println(nb + " cartes posées, " + i + " cartes piochées");
		
		// Afficher la main du joueur qui vient de jouer
		// (donc pas le bon vu qu'il faut afficher la main du joueur qui va jouer)
		System.out.println("cartes " + nom + " " + main.toString());
	}

	public void jouerCartes() {
		Scanner s = new Scanner(System.in);
		String coup = s.next();
		return;
		
	}

	public boolean gagnerPartie() {
		boolean b = false;
		return b;
	}

	public boolean perdrePartie() {
		boolean b = false;
		return b;
	}

	public String toString() {
		String cartesDeLaMain = "{ ";
		for (int cartes : main) {
			if (cartes < 6)
				cartesDeLaMain += String.valueOf(main.get(cartes)) + " ";
			else
				cartesDeLaMain += String.valueOf(main.get(cartes)) + "}";
		}
		return cartesDeLaMain;
	}
	
	//Fonction pour afficher les piles des joueurs
	public String afficherPiles(Joueur joueur) {
		String PilesJoueur = joueur.nom + " ^" +  Integer.toString(joueur.pileAscendante) +" v";
		PilesJoueur += Integer.toString(joueur.pileDescendante)+ " (m" + joueur.main.size() + "p" + joueur.pioche.getPiocheListe().size() + ")";
		return PilesJoueur;
	}

	public Pioche getPioche() {
		return pioche;
	}
}