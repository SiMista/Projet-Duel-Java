package duel;

import duel.Pioche;
import java.util.Scanner;


public class Partie {
	
	public static boolean décomposeCartes(String s, Joueur j, Joueur jAdv) {
		String[] tab = s.split("\\s+");
		int carte = 0;
		boolean poséSurPileAdverse = false;
		if (vérifSaisie(tab, j, jAdv)) {
			for (String entrée : tab) {
				if (tab.length < 2) {
					jAdv.setPartieGagnée();
					partieFinie(j, jAdv);
				}
				if (j.getMain().size() == 1 && j.getPioche().getTaille() == 0) {
					jAdv.setPartieGagnée();
					partieFinie(j, jAdv);
				}
				if (j.getMain().size() == 0 && j.getPioche().getTaille() == 0) {
					j.setPartieGagnée();
					partieFinie(j, jAdv);
				}
				carte = Integer.valueOf(entrée.substring(0, 2));
				if (conditionPileAdverseAsc(entrée, carte, j, jAdv) && !poséSurPileAdverse) {
					poserPileAdverseAsc(carte, j, jAdv);
					poséSurPileAdverse = true;
				}
				else if (conditionPileAdverseDesc(entrée, carte, j, jAdv) && !poséSurPileAdverse) {
					poserPileAdverseDesc(carte, j, jAdv);
					poséSurPileAdverse = true;
				}
				else if (conditionPileAscendante(entrée, carte, j, jAdv))
					poserPileAscendante(carte, j);
				else if (conditionPileDescendante(entrée, carte, j, jAdv))
					poserPileDescendante(carte, j);
			}
			j.piocher(tab.length, poséSurPileAdverse);
			return true;
		} else
			return false;
	}

	public static boolean conditionPileAdverseAsc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == '^') && (entrée.charAt(3) == '’') && (carte < jAdv.getPileAscendante()))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}

	public static boolean conditionPileAdverseDesc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == 'v') && (entrée.charAt(3) == '’') && (carte > jAdv.getPileDescendante()))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}

	public static boolean conditionPileAscendante(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 3)
			if ((entrée.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}

	public static boolean conditionPileDescendante(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 3)
			if ((entrée.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileDescendante() + 10))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}

	public static void poserPileAscendante(int carte, Joueur j) {
		j.setPileAscendante(carte);
		j.retirerMain(carte);
	}

	public static void poserPileDescendante(int carte, Joueur j) {
		j.setPileDescendante(carte);
		j.retirerMain(carte);
	}

	public static void poserPileAdverseAsc(int carte, Joueur j, Joueur jAdv) {
		jAdv.setPileAscendante(carte);
		j.retirerMain(carte);
	}

	public static void poserPileAdverseDesc(int carte, Joueur j, Joueur jAdv) {
		jAdv.setPileDescendante(carte);
		j.retirerMain(carte);
	}

	public static boolean vérifSaisie(String[] tab, Joueur j, Joueur jAdv) {
		boolean erreur = true;
		int carte = 0;
		for (String entrée : tab) {

			// Vérifie si c'est un nombre
			if (!Character.isDigit(entrée.charAt(0)) || !Character.isDigit(entrée.charAt(1)))
				return erreur = false;
			
			// Vérifie que chaque 'entrée' ne fait pas moins de 3 caratères plus de 4 caractères
			if (entrée.length() < 3 || entrée.length() > 4)
				return erreur = false;
			
			carte = Integer.valueOf(entrée.substring(0, 2)); // La variable prend la valeur de chaque entrée

			// Vérifie si les cartes sont dans la main du joueur
			for (int carteMain : j.getMain()) {
				erreur = false;
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}

			// Vérifie que les entrées comporte les caractère des piles
			if (!((conditionPileAdverseAsc(entrée, carte, j, jAdv))
					|| (conditionPileAdverseDesc(entrée, carte, j, jAdv))
					|| (conditionPileAscendante(entrée, carte, j, jAdv))
					|| (conditionPileDescendante(entrée, carte, j, jAdv))))
				erreur = false;
		}
		return erreur;
	}
		
	
	public static boolean partieFinie(Joueur j1, Joueur j2) {
		if(j1.aGagné()) {
			return true;
		}
		else 
			if (j2.aGagné()) {
				return true;		
		}
			else 
				return false;
		}
}
