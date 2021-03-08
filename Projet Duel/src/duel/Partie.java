package duel;

import duel.Pioche;
import java.util.Scanner;


public class Partie {
	
	public static boolean d�composeCartes(String s, Joueur j, Joueur jAdv) {
		String[] tab = s.split("\\s+");
		int carte = 0;
		boolean pos�SurPileAdverse = false;
		if (v�rifSaisie(tab, j, jAdv)) {
			for (String entr�e : tab) {
				if (tab.length < 2) {
					jAdv.setPartieGagn�e();
					partieFinie(j, jAdv);
				}
				if (j.getMain().size() == 1 && j.getPioche().getTaille() == 0) {
					jAdv.setPartieGagn�e();
					partieFinie(j, jAdv);
				}
				if (j.getMain().size() == 0 && j.getPioche().getTaille() == 0) {
					j.setPartieGagn�e();
					partieFinie(j, jAdv);
				}
				carte = Integer.valueOf(entr�e.substring(0, 2));
				if (conditionPileAdverseAsc(entr�e, carte, j, jAdv) && !pos�SurPileAdverse) {
					poserPileAdverseAsc(carte, j, jAdv);
					pos�SurPileAdverse = true;
				}
				else if (conditionPileAdverseDesc(entr�e, carte, j, jAdv) && !pos�SurPileAdverse) {
					poserPileAdverseDesc(carte, j, jAdv);
					pos�SurPileAdverse = true;
				}
				else if (conditionPileAscendante(entr�e, carte, j, jAdv))
					poserPileAscendante(carte, j);
				else if (conditionPileDescendante(entr�e, carte, j, jAdv))
					poserPileDescendante(carte, j);
			}
			j.piocher(tab.length, pos�SurPileAdverse);
			return true;
		} else
			return false;
	}

	public static boolean conditionPileAdverseAsc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == '^') && (entr�e.charAt(3) == '�') && (carte < jAdv.getPileAscendante()))
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
			if ((entr�e.charAt(2) == 'v') && (entr�e.charAt(3) == '�') && (carte > jAdv.getPileDescendante()))
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

			// V�rifie si c'est un nombre
			if (!Character.isDigit(entr�e.charAt(0)) || !Character.isDigit(entr�e.charAt(1)))
				return erreur = false;
			
			// V�rifie que chaque 'entr�e' ne fait pas moins de 3 carat�res plus de 4 caract�res
			if (entr�e.length() < 3 || entr�e.length() > 4)
				return erreur = false;
			
			carte = Integer.valueOf(entr�e.substring(0, 2)); // La variable prend la valeur de chaque entr�e

			// V�rifie si les cartes sont dans la main du joueur
			for (int carteMain : j.getMain()) {
				erreur = false;
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}

			// V�rifie que les entr�es comporte les caract�re des piles
			if (!((conditionPileAdverseAsc(entr�e, carte, j, jAdv))
					|| (conditionPileAdverseDesc(entr�e, carte, j, jAdv))
					|| (conditionPileAscendante(entr�e, carte, j, jAdv))
					|| (conditionPileDescendante(entr�e, carte, j, jAdv))))
				erreur = false;
		}
		return erreur;
	}
		
	
	public static boolean partieFinie(Joueur j1, Joueur j2) {
		if(j1.aGagn�()) {
			return true;
		}
		else 
			if (j2.aGagn�()) {
				return true;		
		}
			else 
				return false;
		}
}
