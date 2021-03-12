package duel;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Classe dans laquelle se d�finie un joueur son nom, sa main, sa pile ascendante,
 * sa pile descendante, sa pioche, s'il a gagn� ainsi que toutes les m�thodes de pioche
 * @author DE ALMEIDA Jules & DEIVA Sim�on 
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
	 * Cr�er un joueur avec le nom entr� en param�tre,
	 * une pioche al�atoire, une pile ascendante � 1, une pile descendante � 60.
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

	
	// M�thodes priv�es
	
	/*
	 * M�thode utilis�e pour pioch�e les cartes lors de la cr�ation du joueur.
	 * @param nb, le nombre de cartes � piocher.
	 */
	private void piocheInitiale(int nb) {
		assert(pioche.getTaille() > 0);
		int i;
		for (i = 0; i < nb; i++) {
			main.add(pioche.getCarte());
		}
	}
	
	// M�thodes publiques 
	
	/*
	 * Piocher un certain nombre de carte, d�pendant d'un bool�en.
	 * @param pos�es, le nombre de cartes pos�es lors du coup jou�.
	 * @param b, le bool�en d�terminant le nombre de cartes pioch�es par le joueur.
	 */
	public void piocherPour(int pos�es, boolean b) {
		int i = main.size();
		// Fait piocher des cartes au joueur jusqu'� ce que sa main soit pleine.
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
		System.out.println(pos�es + " cartes pos�es, " + (main.size() - i) + " cartes pioch�es");
	}

	/*
	 * Permet de savoir si un joueur a gagn�.
	 * @return gagner, le bool�en qui d�termine si le joueur a gagn�.
	 */
	public boolean aGagn�() {
		return gagner;
	}

	/*
	 * Permet de savoir si un joueur a gagn�.
	 * @return gagner, le bool�en qui d�termine si le joueur a gagn�.
	 */
	public void setPartieGagn�e() {
		gagner = true;
	}

	/*
	 * Permet d'obtenir la main du joueur en chaine de caract�re.
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
	
	/* Permet d'obtenir la carte jou�e tout en la retirant de la main.
	 * @param carte, la carte � retirer de la main.
	 */
	private void retirerMain(int carte) {
		for (int i = 0; i < main.size(); i++) {
			if (main.get(i) == carte)
				main.remove(i);
		}
	}
	
	/*
	 * Permet d'obtenir les piles du joueur en chaine de caract�re.
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
	 * @param carte, la carte � poser sur la pile descendante.
	 * @param jAdv, le joueur adverse.
	 */
	public void poserPileAdverseAsc(int carte, Joueur jAdv) {
		jAdv.pileAscendante = carte;
		retirerMain(carte);
	}
	
	/*
	 * Permet de poser une carte sur la pile descendante adverse,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte � poser sur la pile descendante adverse.
	 * @param jAdv, le joueur adverse.
	 */
	public void poserPileAdverseDesc(int carte, Joueur jAdv) {
		jAdv.pileDescendante = carte;
		retirerMain(carte);
	}

	/*
	 * Permet de poser une carte sur une pile ascendante,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte � poser sur la pile ascendante.
	 */
	public void poserPileAscendante(int carte) {
		pileAscendante = carte;
		retirerMain(carte);
	}

	/*
	 * Permet de poser une carte sur une pile descendante,
	 * Tout en retirant la carte de la main du joueur.
	 * @param carte, la carte � poser sur la pile descendante.
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