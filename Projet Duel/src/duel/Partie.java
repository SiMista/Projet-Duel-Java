package duel;

public class Partie {

	public static boolean d�composeCartes(String s, Joueur j, Joueur jAdv) {
		
		String[] tab = s.trim().split("\\s+");
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
				if (conditionPileAdverseAsc(entr�e, carte, j, jAdv)) {
					j.poserPileAdverseAsc(carte, jAdv);
					pos�SurPileAdverse = true;
				} else if (conditionPileAdverseDesc(entr�e, carte, j, jAdv)) {
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

	private static boolean conditionPileAscendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 3)
			if ((entr�e.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileDescendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 3)
			if ((entr�e.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileDescendante() + 10))
				erreur = true;
		return erreur;
	}
	
	private static boolean conditionPileAdverseAsc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == '^') && (entr�e.charAt(3) == '\'') && (carte < jAdv.getPileAscendante()))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileAdverseDesc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == 'v') && (entr�e.charAt(3) == '\'') && (carte > jAdv.getPileDescendante()))
				erreur = true;
		return erreur;
	}

	private static boolean v�rifSaisie(String[] tab, Joueur j, Joueur jAdv) {
		boolean pos�SurPileAdverse = false;
		int tmpAsc = j.getPileAscendante();
		int tmpDesc = j.getPileDescendante();
		for (String entr�e : tab) {
			
			// V�rifie si c'est un nombre
			if (!Character.isDigit(entr�e.charAt(0)) || !Character.isDigit(entr�e.charAt(1)))
				return false;

			// V�rifie que chaque 'entr�e' ne fait pas moins de 3 carat�res plus de 4
			// caract�res
			if (entr�e.length() < 3 || entr�e.length() > 4)
				return false;

			int carte = Integer.valueOf(entr�e.substring(0, 2)); // La variable prend la valeur de chaque entr�e

			// V�rifie si les cartes ne sont pas jou�es en double 
			boolean doublon = false;
			int[] testCartesDoublons = new int[6];
			int index=0;
			for (String test : tab) {
				testCartesDoublons[index] = Integer.valueOf(test.substring(0, 2));
				if (index > 0)
					for (int i = 0; i < index; ++i)
						if(testCartesDoublons[index] == testCartesDoublons[i])
							doublon = true;
				if (doublon == true)
					return false;
				index++;
			}
			
			

			// V�rifie si les cartes sont dans la main du joueur
			boolean erreur = false;
			for (int carteMain : j.getMain()) {
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}
			if (erreur == false)
				return false;

			// V�rifie que les entr�es peuvent se poser sur des piles
			if (conditionPileAscendante(entr�e, carte, j, jAdv))
				if (carte > tmpAsc || carte == tmpAsc - 10)
					tmpAsc = carte;
				else
					return false;

			else if (conditionPileDescendante(entr�e, carte, j, jAdv))
				if (carte < tmpDesc || carte == tmpDesc + 10)
					tmpDesc = carte;
				else
					return false;
			else if (conditionPileAdverseAsc(entr�e, carte, j, jAdv)
					|| conditionPileAdverseDesc(entr�e, carte, j, jAdv)) {
				if (pos�SurPileAdverse == true)
					return false;
				pos�SurPileAdverse = true;
			} else
				return false;
		}
		return true;

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
