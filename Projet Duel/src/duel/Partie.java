package duel;

import java.util.Scanner;

public class Partie {
	private boolean tourDeNord = true;

	public static boolean d�composeCartes(String s, Joueur j, Joueur jAdv) {
		String[] tab = s.split("\\s+");
		int carte = 0;
		if (v�rifSaisie(tab, j, jAdv)) {
			for (String entr�e : tab) {
				carte = Integer.valueOf(entr�e.substring(0, 1));
				if (conditionPileAdverseAsc(entr�e, carte, j, jAdv))
					poserPileAdverseAsc(carte, j, jAdv);
				else if (conditionPileAdverseDesc(entr�e, carte, j, jAdv))
					poserPileAdverseDesc(carte, j, jAdv);
				else if (conditionPileAscendante(entr�e, carte, j, jAdv))
					poserPileAscendante(carte, j);
				else if (conditionPileDescendante(entr�e, carte, j, jAdv))
					poserPileDescendante(carte, j);
			}
			return true;
		} else
			return false;
	}

	public static boolean conditionPileAdverseAsc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(3) == '�') && (entr�e.charAt(2) == '^') && (carte < jAdv.getPileAscendante()))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}

	public static boolean conditionPileAdverseDesc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(3) == '�') && (entr�e.charAt(2) == 'v') && (carte > jAdv.getPileDescendante()))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}


	public static boolean conditionPileAscendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 3)
			if ((entr�e.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10))
				erreur = true;
			else
				erreur = false;
		else
			erreur = false;
		return erreur;
	}
	
	public static boolean conditionPileDescendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 3)
			if ((entr�e.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileDescendante() + 10))
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

	public static boolean v�rifSaisie(String[] tab, Joueur j, Joueur jAdv) {
		boolean erreur = true;
		int carte = 0;
		for (String entr�e : tab) {
			if (!Character.isDigit(entr�e.charAt(0)) && !Character.isDigit(entr�e.charAt(1))) // V�rifie si c'est un
				// nombre
				return erreur = false;
			carte = Integer.valueOf(entr�e.substring(0, 1)); // La variable locale prend la valeur de chaque nombre du
																// tab[]
			/* for (int carteMain : j.getMain()) // V�rifie si les cartes sont dans la main du joueur
				for (int i = 0; i < tab.length; ++i)
					if (carteMain != carte)
						erreur = false; // IL FAUT COMPARER TOUTES LES CARTES ET PAS QU'UNE ! */

			if (entr�e.length() > 4) // V�rifie que chaque 'entr�e' ne fait pas plus de 4 caract�res
				erreur = false;

			if (!((conditionPileAdverseAsc(entr�e, carte, j, jAdv)) // V�rifie que l'entr�e comporte les caract�re des
																	// piles
					|| (conditionPileAdverseDesc(entr�e, carte, j, jAdv))
					|| (conditionPileAscendante(entr�e, carte, j, jAdv))
					|| (conditionPileDescendante(entr�e, carte, j, jAdv))))
				erreur = false;
		}
		return erreur;
	}
}
