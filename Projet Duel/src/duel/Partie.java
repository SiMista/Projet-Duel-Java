package duel;

public class Partie {

	public static boolean décomposeCartes(String s, Joueur j, Joueur jAdv) {
		
		String[] tab = s.trim().split("\\s+");
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
				if (conditionPileAdverseAsc(entrée, carte, j, jAdv)) {
					j.poserPileAdverseAsc(carte, jAdv);
					poséSurPileAdverse = true;
				} else if (conditionPileAdverseDesc(entrée, carte, j, jAdv)) {
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

	private static boolean conditionPileAscendante(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 3)
			if ((entrée.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileDescendante(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 3)
			if ((entrée.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileDescendante() + 10))
				erreur = true;
		return erreur;
	}
	
	private static boolean conditionPileAdverseAsc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == '^') && (entrée.charAt(3) == '\'') && (carte < jAdv.getPileAscendante()))
				erreur = true;
		return erreur;
	}

	private static boolean conditionPileAdverseDesc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == 'v') && (entrée.charAt(3) == '\'') && (carte > jAdv.getPileDescendante()))
				erreur = true;
		return erreur;
	}

	private static boolean vérifSaisie(String[] tab, Joueur j, Joueur jAdv) {
		boolean poséSurPileAdverse = false;
		int tmpAsc = j.getPileAscendante();
		int tmpDesc = j.getPileDescendante();
		for (String entrée : tab) {
			
			// Vérifie si c'est un nombre
			if (!Character.isDigit(entrée.charAt(0)) || !Character.isDigit(entrée.charAt(1)))
				return false;

			// Vérifie que chaque 'entrée' ne fait pas moins de 3 caratères plus de 4
			// caractères
			if (entrée.length() < 3 || entrée.length() > 4)
				return false;

			int carte = Integer.valueOf(entrée.substring(0, 2)); // La variable prend la valeur de chaque entrée

			// Vérifie si les cartes ne sont pas jouées en double 
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
			
			

			// Vérifie si les cartes sont dans la main du joueur
			boolean erreur = false;
			for (int carteMain : j.getMain()) {
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}
			if (erreur == false)
				return false;

			// Vérifie que les entrées peuvent se poser sur des piles
			if (conditionPileAscendante(entrée, carte, j, jAdv))
				if (carte > tmpAsc || carte == tmpAsc - 10)
					tmpAsc = carte;
				else
					return false;

			else if (conditionPileDescendante(entrée, carte, j, jAdv))
				if (carte < tmpDesc || carte == tmpDesc + 10)
					tmpDesc = carte;
				else
					return false;
			else if (conditionPileAdverseAsc(entrée, carte, j, jAdv)
					|| conditionPileAdverseDesc(entrée, carte, j, jAdv)) {
				if (poséSurPileAdverse == true)
					return false;
				poséSurPileAdverse = true;
			} else
				return false;
		}
		return true;

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
