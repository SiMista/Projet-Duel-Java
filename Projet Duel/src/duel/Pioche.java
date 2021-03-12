package duel;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Classe dans laquelle une pioche al�atoire est construite, 
 * @author DE ALMEIDA Jules & DEIVA Sim�on 
 */
public class Pioche {

	// Attributs
	private ArrayList<Integer> piocheListe; 
	private int taille;

	// Constructeur
	
	/* 
	 * Constructeur de base d'une pioche.
	 * Initialise une pioche avec des cartes uniques allant de 2 � 59,
	 * Puis m�lange la pioche.
	 */
	public Pioche() {
		taille = 58;
		piocheListe = new ArrayList<Integer>();
		for (int i = 0; i < taille + 2; i++) {
			if (i > 1)
				piocheListe.add(i);
		}
		Collections.shuffle(piocheListe);
	}
	
	
	// M�thodes publiques 
	
	/*
	 * Permet d'obtenir la premi�re carte tout en la retirant de la pioche.
	 * Diminue �galement la taille de 1.
	 * @return Retourne la premi�re carte de la pioche .
	 */
	public int getCarte() { 
		taille--;
		return piocheListe.remove(0);
	}
	
	/*
	 * Permet d'obtenir la taille de la pioche.
	 * @return Retourne la taille de la pioche.
	 */
	public int getTaille() {
		return taille;
	}
	
}
