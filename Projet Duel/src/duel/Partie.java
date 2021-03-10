package duel;

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
					j.poserPileAdverseAsc(carte, jAdv);
					pos�SurPileAdverse = true;
				} else if (conditionPileAdverseDesc(entr�e, carte, j, jAdv) && !pos�SurPileAdverse) {
					j.poserPileAdverseDesc(carte, jAdv);
					pos�SurPileAdverse = true;
				} else if (conditionPileAscendante(entr�e, carte, j, jAdv))
					j.poserPileAscendante(carte);
				else if (conditionPileDescendante(entr�e, carte, j, jAdv))
					j.poserPileDescendante(carte);
			}
			j.piocher(tab.length, pos�SurPileAdverse);
			return true;
		} else
			return false;
	}

	private static boolean conditionPileAdverseAsc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == '^') && (entr�e.charAt(3) == '�') && (carte < jAdv.getPileAscendante()))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileAdverseDesc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == 'v') && (entr�e.charAt(3) == '�') && (carte > jAdv.getPileDescendante()))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileAscendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
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

	private static boolean conditionPileDescendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
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

	private static boolean v�rifSaisie(String[] tab, Joueur j, Joueur jAdv) {

		boolean erreur = true;
		int tmpAsc = j.getPileAscendante();
		int tmpDesc = j.getPileDescendante();
		for (String entr�e : tab) {

			// V�rifies si la premi�re entr�e est un espace ou une tabulation
			if (entr�e == "")
				return erreur = false;

			// V�rifie si c'est un nombre
			if (!Character.isDigit(entr�e.charAt(0)) || !Character.isDigit(entr�e.charAt(1)))
				return erreur = false;

			// V�rifie que chaque 'entr�e' ne fait pas moins de 3 carat�res plus de 4
			// caract�res
			if (entr�e.length() < 3 || entr�e.length() > 4)
				return erreur = false;

			int carte = Integer.valueOf(entr�e.substring(0, 2)); // La variable prend la valeur de chaque entr�e

			// V�rifie si les cartes sont dans la main du joueur
			for (int carteMain : j.getMain()) {
				erreur = false;
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}

			// V�rifie que les entr�es peuvent se poser sur des piles
			if (conditionPileAscendante(entr�e, carte, j, jAdv))
				if (carte > tmpAsc || carte == tmpDesc - 10)
					tmpAsc = carte;
				else
					return erreur = false;

			else if (conditionPileDescendante(entr�e, carte, j, jAdv))
				if (carte < tmpDesc || carte == tmpDesc + 10)
					tmpDesc = carte;
				else
					return erreur = false;
			else if (conditionPileAdverseAsc(entr�e, carte, j, jAdv) ||
					conditionPileAdverseDesc(entr�e, carte, j, jAdv))
				
			else return erreur = false;
		}
		return erreur;
	}

	public static boolean partieFinie(Joueur j1, Joueur j2) {
		if (j1.aGagn�()) {
			return true;
		} else if (j2.aGagn�()) {
			return true;
		} else
			return false;
	}
}
