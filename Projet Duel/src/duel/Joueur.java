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
		for (int i = 0; i < nb; i++) {
			main.add(i, pioche.getCartesPioche(i));
			pioche.getPiocheListe().remove(i);
		}
		return;
	}

	public ArrayList<Integer> jouerCartes(ArrayList<Integer> carteEnMain) {
		ArrayList<Integer> joue = new ArrayList<Integer>();
		return joue;
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
			if (cartes < main.size() - 1)
				cartesDeLaMain += String.valueOf(main.get(cartes)) + " ";
			else
				cartesDeLaMain += String.valueOf(main.get(cartes)) + "}";
		}
		return cartesDeLaMain;
	}

	public Pioche getPioche() {
		return pioche;
	}
}