package duel;

import java.util.ArrayList;
import java.util.Collections;

public class Joueur {
	private String nom;
	private ArrayList<Integer> main;
	private Pioche pioche;
	private int pileAscendante;
	private int pileDescendante;
	private boolean gagner = false;

	public Joueur(String nomDuJoueur) {
		nom = nomDuJoueur;
		main = new ArrayList<Integer>();
		pioche = new Pioche();
		pileAscendante = 1;
		pileDescendante = 60;
		piocheInitiale(6);
	}

	public void piocheInitiale(int nb) {
		int i;
		for (i = 0; i < nb; i++) {
			if (pioche.getTaille() > 0) {
				main.add(i, (pioche.getCartes(i)));
				pioche.retirerCarte(i);
			} else
				break;
		}
	}
	
	public void piocher(int posée, boolean b) {
		int i;
		if (b) {
			for (i = 0; i < posée; i++) {
				if (pioche.getTaille() > 0) {
					main.add(i, (pioche.getCartes(i)));
					pioche.retirerCarte(i);
				}
			}
		}
		else {
				for (i = 0; i < 2; i++) {
					if (pioche.getTaille() > 0) {
						main.add(i, (pioche.getCartes(i)));
						pioche.retirerCarte(i);
					}
				}
		}
		System.out.println(posée + " cartes posées, " + i + " cartes piochées");

	}
	
	public boolean aGagné() {
		return gagner;
	}

	public void setPartieGagnée() {
		gagner = true;
	}


	public String afficherMain() {
		String cartesDeLaMain = "cartes " + nom + " { ";
		Collections.sort(main);
		for (int i = 0; i < main.size(); i++) {
			if (i < main.size() - 1)
				cartesDeLaMain += String.format("%02d", main.get(i)) + " ";
			else
				cartesDeLaMain += String.format("%02d", main.get(i)) + " }";
		}
		return cartesDeLaMain;
	}

	// Fonction pour afficher les piles des joueurs
	public String afficherPiles() {
		String pilesJoueur = "";
		pilesJoueur += String.format("%-4s", nom) + " ^[" + String.format("%02d", pileAscendante) + "]";
		pilesJoueur += " v[" + String.format("%02d", pileDescendante) + "]";
		pilesJoueur += " (m" + main.size() + "p" + pioche.getTaille() + ")";
		return pilesJoueur;
	}

	public void retirerMain(int carte) {
		for (int i = 0; i < main.size(); i++) {
			if (main.get(i) == carte)
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