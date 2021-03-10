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

	private void piocheInitiale(int nb) {
		int i;
		for (i = 0; i < nb; i++) {
			if (pioche.getTaille() > 0) {
				main.add(pioche.getCarte());
			} else
				break;
		}
	}

	public void piocher(int posée, boolean b) {
		int i = main.size();
		if (b)
			while (main.size() != 6) {
				if (pioche.getTaille() > 0)
					main.add(pioche.getCarte());
				else
					break;
			}
		else
			for (int y = 0; y < 2; y++) {
				if (pioche.getTaille() > 0)
					main.add(pioche.getCarte());
				else
					break;
			}
		System.out.println(posée + " cartes posées, " + (main.size() - i) + " cartes piochées");
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

	public void poserPileAscendante(int carte) {
		pileAscendante = carte;
		retirerMain(carte);
	}

	public void poserPileDescendante(int carte) {
		pileDescendante = carte;
		retirerMain(carte);
	}

	public void poserPileAdverseAsc(int carte, Joueur jAdv) {
		jAdv.pileAscendante = carte;
		retirerMain(carte);
	}

	public void poserPileAdverseDesc(int carte, Joueur jAdv) {
		jAdv.pileDescendante = carte;
		retirerMain(carte);
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
}