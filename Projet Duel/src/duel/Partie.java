package duel;

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
					j.poserPileAdverseAsc(carte, jAdv);
					poséSurPileAdverse = true;
				} else if (conditionPileAdverseDesc(entrée, carte, j, jAdv) && !poséSurPileAdverse) {
					j.poserPileAdverseDesc(carte, jAdv);
					poséSurPileAdverse = true;
				} else if (conditionPileAscendante(entrée, carte, j, jAdv))
					j.poserPileAscendante(carte);
				else if (conditionPileDescendante(entrée, carte, j, jAdv))
					j.poserPileDescendante(carte);
			}
			j.piocher(tab.length, poséSurPileAdverse);
			return true;
		} else
			return false;
	}

	private static boolean conditionPileAdverseAsc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == '^') && (entrée.charAt(3) == '’') && (carte < jAdv.getPileAscendante()))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileAdverseDesc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == 'v') && (entrée.charAt(3) == '’') && (carte > jAdv.getPileDescendante()))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileAscendante(String entrée, int carte, Joueur j, Joueur jAdv) {
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

	private static boolean conditionPileDescendante(String entrée, int carte, Joueur j, Joueur jAdv) {
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

	private static boolean vérifSaisie(String[] tab, Joueur j, Joueur jAdv) {

		boolean erreur = true;
		int tmpAsc = j.getPileAscendante();
		int tmpDesc = j.getPileDescendante();
		for (String entrée : tab) {

			// Vérifies si la première entrée est un espace ou une tabulation
			if (entrée == "")
				return erreur = false;

			// Vérifie si c'est un nombre
			if (!Character.isDigit(entrée.charAt(0)) || !Character.isDigit(entrée.charAt(1)))
				return erreur = false;

			// Vérifie que chaque 'entrée' ne fait pas moins de 3 caratères plus de 4
			// caractères
			if (entrée.length() < 3 || entrée.length() > 4)
				return erreur = false;

			int carte = Integer.valueOf(entrée.substring(0, 2)); // La variable prend la valeur de chaque entrée

			// Vérifie si les cartes sont dans la main du joueur
			for (int carteMain : j.getMain()) {
				erreur = false;
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}

			// Vérifie que les entrées peuvent se poser sur des piles
			if (conditionPileAscendante(entrée, carte, j, jAdv))
				if (carte > tmpAsc || carte == tmpDesc - 10)
					tmpAsc = carte;
				else
					return erreur = false;

			else if (conditionPileDescendante(entrée, carte, j, jAdv))
				if (carte < tmpDesc || carte == tmpDesc + 10)
					tmpDesc = carte;
				else
					return erreur = false;
			else if (conditionPileAdverseAsc(entrée, carte, j, jAdv) ||
					conditionPileAdverseDesc(entrée, carte, j, jAdv))
				
			else return erreur = false;
		}
		return erreur;
	}

	public static boolean partieFinie(Joueur j1, Joueur j2) {
		if (j1.aGagné()) {
			return true;
		} else if (j2.aGagné()) {
			return true;
		} else
			return false;
	}
}
