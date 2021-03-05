package duel;

import java.util.Scanner;

public class Partie {
	private boolean tourDeNord = true;

	public static boolean décomposeCartes(String s, Joueur j, Joueur jAdv) {
		String[] tab = s.split("\\s+");
		int carte = 0;
		if (vérifSaisie(tab, j, jAdv)) {
			for (String entrée : tab) {
				carte = Integer.valueOf(entrée.substring(0, 2));
				if (conditionPileAdverseAsc(entrée, carte, j, jAdv))
					poserPileAdverseAsc(carte, j, jAdv);
				else if (conditionPileAdverseDesc(entrée, carte, j, jAdv))
					poserPileAdverseDesc(carte, j, jAdv);
				else if (conditionPileAscendante(entrée, carte, j, jAdv))
					poserPileAscendante(carte, j);
				else if (conditionPileDescendante(entrée, carte, j, jAdv))
					poserPileDescendante(carte, j);
			}
			return true;
		} else
			return false;
	}

	public static boolean conditionPileAdverseAsc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(3) == '’') && (entrée.charAt(2) == '^') && (carte < jAdv.getPileAscendante()))
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
			if ((entrée.charAt(3) == '’') && (entrée.charAt(2) == 'v') && (carte > jAdv.getPileDescendante()))
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
			if (!Character.isDigit(entrée.charAt(0)) && !Character.isDigit(entrée.charAt(1)))
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

			// Vérifie que chaque 'entrée' ne fait pas plus de 4 caractères
			if (entrée.length() > 4)
				erreur = false;

			// Vérifie que les entrées comporte les caractère des piles
			if (!((conditionPileAdverseAsc(entrée, carte, j, jAdv))
					|| (conditionPileAdverseDesc(entrée, carte, j, jAdv))
					|| (conditionPileAscendante(entrée, carte, j, jAdv))
					|| (conditionPileDescendante(entrée, carte, j, jAdv))))
				erreur = false;
		}
		return erreur;
	}
}
