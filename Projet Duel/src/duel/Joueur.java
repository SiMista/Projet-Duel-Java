package duel;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Classe dans laquelle se définie un joueur son nom, sa main, sa pile ascendante,
 * sa pile descendante, sa pioche, s'il a gagné ainsi que toutes les méthodes de pioche
 * @author DE ALMEIDA Jules & DEIVA Siméon 
 */
public class Joueur {
	
	// Attributs
	private String nom;
	private ArrayList<Integer> main;
	private Pioche pioche;
	private int pileAscendante;
	private int pileDescendante;
	private boolean gagner = false;

	//Constructeur
	
	/*
	 * Constructeur du joueur.
	 * Créer un joueur avec le nom entré en paramètre,
	 * une pioche aléatoire, une pile ascendante à 1, une pile descendante à 60.
	 * et une main de 6 cartes provenant de la pioche.
	 * @param nomDuJoueur le nom du joueur.
	 */
	public Joueur(String nomDuJoueur) {
		nom = nomDuJoueur;
		main = new ArrayList<Integer>();
		pioche = new Pioche();
		pileAscendante = 1;
		pileDescendante = 60;
		piocheInitiale(6);
	}

	
	// Méthodes privées
	
	/*
	 * Méthode utilisée pour piochée les cartes lors de la création du joueur.
	 * @param nb, le nombre de cartes à piocher.
	 */
	private void piocheInitiale(int nb) {
		assert(pioche.getTaille() > 0);
		int i;
		for (i = 0; i < nb; i++) {
			main.add(pioche.getCarte());
		}
	}
	
	// Méthodes publiques 
	
	/*
	 * Piocher un certain nombre de carte, dépendant d'un booléen.
	 * @param posées, le nombre de cartes posées lors du coup joué.
	 * @param b, le booléen déterminant le nombre de cartes piochées par le joueur.
	 */
	public void piocherPour(int posées, boolean b) {
		int i = main.size();
		// Fait piocher des cartes au joueur jusqu'à ce que sa main soit pleine.
		if (b)
			while (main.size() < 6) {
				if (pioche.getTaille() > 0)
					main.add(pioche.getCarte());
				else
					break;
			}
		// Fait piocher 2 cartes au joueur.
		else
			for (int y = 0; y < 2; y++) {
				if (pioche.getTaille() > 0)
					main.add(pioche.getCarte());
				else
					break;
			}
		System.out.println(posées + " cartes posées, " + (main.size() - i) + " cartes piochées");
	}

	/*
	 * Permet de savoir si un joueur a gagné.
	 * @return gagner, le booléen qui détermine si le joueur a gagné.
	 */
	public boolean aGagné() {
		return gagner;
	}

	/*
	 * Permet de savoir si un joueur a gagné.
	 * @return gagner, le booléen qui détermine si le joueur a gagné.
	 */
	public void setPartieGagnée() {
		gagner = true;
	}

	/*
	 * Permet d'obtenir la main du joueur en chaine de caractère.
	 * @return cartesDeLaMain, les cartes de la main du joueur .
	 */
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
	
	/* Permet d'obtenir la carte jouée tout en la retirant de la main.
	 * @param carte, la carte à retirer de la main.
	 */
	private void retirerMain(int carte) {
		for (int i = 0; i < main.size(); i++) {
			if (main.get(i) == carte)
				main.remove(i);
		}
	}
	
	/*
	 * Permet d'obtenir les piles du joueur en chaine de caractère.
	 * @return pilesJoueur, les piles du joueur .
	 */
	public String afficherPiles() {
		String pilesJoueur = "";
		pilesJoueur += String.format("%-4s", nom) + " ^[" + String.format("%02d", pileAscendante) + "]";
		pilesJoueur += " v[" + String.format("%02d", pileDescendante) + "]";
		pilesJoueur += " (m" + main.size() + "p" + pioche.getTaille() + ")";
		return pilesJoueur;
	}
	
	/*
	 * Permet de poser une carte sur une pile descendante,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte à poser sur la pile descendante.
	 * @param jAdv, le joueur adverse.
	 */
	public void poserPileAdverseAsc(int carte, Joueur jAdv) {
		jAdv.pileAscendante = carte;
		retirerMain(carte);
	}
	
	/*
	 * Permet de poser une carte sur la pile descendante adverse,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte à poser sur la pile descendante adverse.
	 * @param jAdv, le joueur adverse.
	 */
	public void poserPileAdverseDesc(int carte, Joueur jAdv) {
		jAdv.pileDescendante = carte;
		retirerMain(carte);
	}

	/*
	 * Permet de poser une carte sur une pile ascendante,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte à poser sur la pile ascendante.
	 */
	public void poserPileAscendante(int carte) {
		pileAscendante = carte;
		retirerMain(carte);
	}

	/*
	 * Permet de poser une carte sur une pile descendante,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte à poser sur la pile descendante.
	 */
	public void poserPileDescendante(int carte) {
		pileDescendante = carte;
		retirerMain(carte);
	}
	

	/*
	 * Permet de d'obtenir la main du joueur
	 * @return main, la main du joueur
	 */
	public ArrayList<Integer> getMain() {
		return main;
	}

	/*
	 * Permet de d'obtenir la pile ascendante du joueur.
	 * @return pileAscendante, la pile ascendante du joueur.
	 */
	public int getPileAscendante() {
		return pileAscendante;
	}

	/*
	 * Permet de d'obtenir la pile descendante du joueur.
	 * @return pileDescendante, la pile descendante du joueur.
	 */
	public int getPileDescendante() {
		return pileDescendante;
	}
}