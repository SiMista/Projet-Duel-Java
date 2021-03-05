package duel;

import java.util.ArrayList;

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
			if (pioche.getTaille() > 0) {
				main.add(i, (pioche.getCartes(i)));
				pioche.retirerCarte(i);
				}
			else 
				break;	
		}
		System.out.println(nb + " cartes posées, " + i + " cartes piochées");
		
		// Afficher la main du joueur qui vient de jouer
		// (donc pas le bon vu qu'il faut afficher la main du joueur qui va jouer)
		System.out.println(afficherMain());
	}



	public boolean gagnerPartie() {
		boolean b = false;
		return b;
	}

	public boolean perdrePartie() {
		boolean b = false;
		return b;
	}

	public String afficherMain() {
		String cartesDeLaMain = "cartes " + nom + " { ";
		for (int i = 0; i < main.size(); i++) {
			if (i < main.size() - 1)
				cartesDeLaMain += String.format("%02d", main.get(i)) + " ";
			else
				cartesDeLaMain += main.get(i) + " }";
		}
		return cartesDeLaMain;
	}
	
	//Fonction pour afficher les piles des joueurs
	public String afficherPiles() {
		String pilesJoueur = "";
		pilesJoueur += String.format("%-4s", nom) + " ^[" + String.format("%02d", pileAscendante) +"]";
		pilesJoueur += " v[" + String.format("%02d", pileDescendante) + "]";
		pilesJoueur += " (m" + main.size() + "p" + pioche.getTaille() + ")";
		return pilesJoueur;
	}
	
	public void retirerMain(int carte) {
		for (int i : main) {
			if(i == carte)
				main.remove(i);
		}
	}
	
	public Pioche getPioche() {
		return pioche;
	}
	
	public ArrayList<Integer> getMain() {
		return main;
	}
	
	public int getPileAscendante() {
		return pileAscendante;
	}
	
	public int getPileDescendante() {
		return pileDescendante;
	}
	public void setPileAscendante(int i) {
		pileAscendante = i;
	}
	
	public void setPileDescendante(int i) {
		pileDescendante = i;
	}
	
}