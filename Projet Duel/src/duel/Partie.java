package duel;

/*
 * Classe dans laquelle se traite les coups joués par les joueurs, leurs validités 
 * et les conditions de victoires de la partie.
 * @author DE ALMEIDA Jules & DEIVA Siméon 
 */
public class Partie {
	
	// Méthodes privées
	
	/*
	 * Vérifie si la carte jouée peut être jouée sur la pile ascendante adverse.
	 * @param entrée, la carte et le ou les signes joués sous forme de chaine de caractères.
	 * @param carte, la carte jouée sous forme d'entier.
	 * @param j, le joueur qui a joué.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le booléen vérifiant si la carte peut être joué sur la pile ascendante adverse.
	 */
	private static boolean conditionPileAdverseAsc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == '^') && (entrée.charAt(3) == '\'') && (carte < jAdv.getPileAscendante()))
				erreur = true;
		return erreur;
	}

	/*
	 * Vérifie si la carte jouée peut être jouée sur la pile descendante adverse.
	 * @param entrée, la carte et le ou les signes joués sous forme de chaine de caractères.
	 * @param carte, la carte jouée sous forme d'entier.
	 * @param j, le joueur qui a joué.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le booléen vérifiant si la carte peut être joué sur la pile descendante adverse.
	 */
	private static boolean conditionPileAdverseDesc(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 4)
			if ((entrée.charAt(2) == 'v') && (entrée.charAt(3) == '\'') && (carte > jAdv.getPileDescendante()))
				erreur = true;
		return erreur;
	}

	/*
	 * Vérifie si la carte jouée peut être jouée sur la pile ascendante.
	 * @param entrée, la carte et le ou les signes joués sous forme de chaine de caractères.
	 * @param carte, la carte jouée sous forme d'entier.
	 * @param j, le joueur qui a joué.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le booléen vérifiant si la carte peut être joué sur la pile ascendante.
	 */
	private static boolean conditionPileAscendante(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 3)
			if ((entrée.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10))
				erreur = true;
		return erreur;
	}

	/*
	 * Vérifie si la carte jouée peut être jouée sur la pile descendante.
	 * @param entrée, la carte et le ou les signes joués sous forme de chaine de caractères.
	 * @param carte, la carte jouée sous forme d'entier.
	 * @param j, le joueur qui a joué.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le booléen vérifiant si la carte peut être joué sur sa pile descendante.
	 */
	private static boolean conditionPileDescendante(String entrée, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entrée.length() == 3)
			if ((entrée.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileDescendante() + 10))
				erreur = true;
		return erreur;
	}
	
	/*
	 * Vérifie si les cartes saisies sont valables.
	 * @param tab, le tableau de chaine de caractères contenant dans chaque case une carte 
	 * 	           et l'endroit où elle est jouée.
	 * @param carte, la carte jouée sous forme d'entier.
	 * @param j, le joueur qui a joué.
	 * @param jAdv, le joueur adverse.
	 * @return boolean, le booléen vérifiant si il y a une erreur de saisie ou non.
	 */
	private static boolean vérifSaisie(String[] tab, Joueur j, Joueur jAdv) {
		boolean poséSurPileAdverse = false;
		int tmpAsc = j.getPileAscendante();
		int tmpDesc = j.getPileDescendante();
		for (String entrée : tab) {		
			int carte = Integer.valueOf(entrée.substring(0, 2)); // La variable prend la valeur de chaque entrée.
			// Vérifie si c'est un nombre
			if (!Character.isDigit(entrée.charAt(0)) || !Character.isDigit(entrée.charAt(1)))
				return false;

			// Vérifie que chaque 'entrée' ne fait pas moins de 3 caratères plus de 4 caractères.
			if (entrée.length() < 3 || entrée.length() > 4)
				return false;


			// Vérifie si les cartes ne sont pas jouées en double. 
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
			
			// Vérifie que le coup jouer à plus.
			if (tab.length < 2)
				return false;
			
			// Vérifie si les cartes sont dans la main du joueur.
			boolean erreur = false;
			for (int carteMain : j.getMain()) {
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}
			if (erreur == false)
				return false;

			// Vérifie que les entrées peuvent se poser sur au moins une pile.
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
			else 
				if (conditionPileAdverseAsc(entrée, carte, j, jAdv)
				|| conditionPileAdverseDesc(entrée, carte, j, jAdv)) {
					if (poséSurPileAdverse == true)
						return false;
					poséSurPileAdverse = true;
				} 	
				else
						return false;
		}
		return true;
	}
	
	/*
	 * Vérifie si au moins 2 cartes en main sont jouables
	 * @param j, le joueur qui doit jouer.
	 * @param jAdv, le joueur adverse.
	 * @return boolean, le booléen vérifiant si le joueur peut jouer.
	 */
	private static boolean vérifCartesJouables(Joueur j, Joueur jAdv) {
		int cartesJouables = 0;
		for (int carteMain : j.getMain()) 
			if((carteMain > j.getPileAscendante()) || (carteMain == j.getPileAscendante() - 10)||
			(carteMain < j.getPileDescendante()) || (carteMain == j.getPileDescendante() + 10)||
			(carteMain < jAdv.getPileAscendante()) || (carteMain > jAdv.getPileDescendante()))
				cartesJouables++;
		if(cartesJouables > 1) 
			return true;
		return false;
	}
	
	
	// Méthodes publiques
	
	/*
	 * Décompose les différentes cartes jouées, et si elles sont valides, les poses sur les piles correspondantes.
	 * @param s, les différentes cartes jouées
	 * @param j, le joueur qui a jouer.
	 * @param jAdv, le joueur adverse.
	 * @return boolean, le booléen vérifiant si le coup est valide.
	 */
	public static boolean décomposeCartes(String s, Joueur j, Joueur jAdv) {	
		String[] tab = s.trim().split("\\s+");
		int carte = 0;
		boolean poséSurPileAdverse = false;
		if (vérifSaisie(tab, j, jAdv)) {
			for (String entrée : tab) {
				carte = Integer.valueOf(entrée.substring(0, 2));
				if (conditionPileAdverseAsc(entrée, carte, j, jAdv)) {
					jAdv.poserPileAscendante(carte);
					poséSurPileAdverse = true;
				} 
				else if (conditionPileAdverseDesc(entrée, carte, j, jAdv)) {
					jAdv.poserPileDescendante(carte);
					poséSurPileAdverse = true;
				} 
				else if (conditionPileAscendante(entrée, carte, j, jAdv))
					j.poserPileAscendante(carte);
				else if (conditionPileDescendante(entrée, carte, j, jAdv))
					j.poserPileDescendante(carte);
			}
			j.piocherPour(tab.length, poséSurPileAdverse);
			return true;
		} else
			return false;
	}
	
	/*
	 * Permet de savoir si un joueur a gagné.
	 * @param j1, le premier joueur.
	 * @param j2, le deuxième joueur.
	 * @param tourDeJ1, le booléen représentant s'il s'agit du tour du joueur 1.
	 * @return boolean, le booléen vérifiant si un joueur a gagné.
	 */
	public static boolean conditionVictoire(Joueur j1, Joueur j2, boolean tourDeJ1) {
		// Vérifie si les joueurs ont fini leurs mains 
		// (et leurs pioches par conséquent puisqu'ils n'ont pas pioché de cartes)
		// et donc gagné la partie.
		 if(j1.getMain().size() == 0) {
			j1.setPartieGagnée();
			return true;
		}
		 else if(j2.getMain().size() == 0) {
			j2.setPartieGagnée();
			return true;
		}
		// Vérifie si les joueurs n'ont plus qu'une carte en main ce qui leur fait perdre la partie
		 else if(j1.getMain().size() == 1) {
			j2.setPartieGagnée();
			return true;
		}
		else if(j2.getMain().size() == 1) {
			j1.setPartieGagnée();
			return true;
		}
		// Vérifie si le joueur à au moins 2 cartes jouables, sinon il perd.
		else if(!vérifCartesJouables(j1,j2) && tourDeJ1) {
			j2.setPartieGagnée();
			return true;
		}
		else if(!vérifCartesJouables(j2,j1) && !tourDeJ1) {
			j1.setPartieGagnée();
			return true;
		}
		return false;
	}

	/*
	 * Détermine si la partie est finie
	 * @param j1, le joueur 1.
	 * @param j2, le joueur 2.
	 * @return boolean, le booléen vérifiant si la partie est finie.
	 */
	public static boolean partieFinie(Joueur j1, Joueur j2) {
		if (j1.aGagné())
			return true;
		else if (j2.aGagné()) 
			return true;
		else
			return false;
	}
	
}
