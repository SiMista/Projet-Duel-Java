package duel;

/*
 * Classe dans laquelle se traite les coups jou�s par les joueurs, leurs validit�s 
 * et les conditions de victoires de la partie.
 * @author DE ALMEIDA Jules & DEIVA Sim�on 
 */
public class Partie {
	
	// M�thodes priv�es
	
	/*
	 * V�rifie si la carte jou�e peut �tre jou�e sur la pile ascendante adverse.
	 * @param entr�e, la carte et le ou les signes jou�s sous forme de chaine de caract�res.
	 * @param carte, la carte jou�e sous forme d'entier.
	 * @param j, le joueur qui a jou�.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le bool�en v�rifiant si la carte peut �tre jou� sur la pile ascendante adverse.
	 */
	private static boolean conditionPileAdverseAsc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == '^') && (entr�e.charAt(3) == '\'') && (carte < jAdv.getPileAscendante()))
				erreur = true;
		return erreur;
	}

	/*
	 * V�rifie si la carte jou�e peut �tre jou�e sur la pile descendante adverse.
	 * @param entr�e, la carte et le ou les signes jou�s sous forme de chaine de caract�res.
	 * @param carte, la carte jou�e sous forme d'entier.
	 * @param j, le joueur qui a jou�.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le bool�en v�rifiant si la carte peut �tre jou� sur la pile descendante adverse.
	 */
	private static boolean conditionPileAdverseDesc(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 4)
			if ((entr�e.charAt(2) == 'v') && (entr�e.charAt(3) == '\'') && (carte > jAdv.getPileDescendante()))
				erreur = true;
		return erreur;
	}

	/*
	 * V�rifie si la carte jou�e peut �tre jou�e sur la pile ascendante.
	 * @param entr�e, la carte et le ou les signes jou�s sous forme de chaine de caract�res.
	 * @param carte, la carte jou�e sous forme d'entier.
	 * @param j, le joueur qui a jou�.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le bool�en v�rifiant si la carte peut �tre jou� sur la pile ascendante.
	 */
	private static boolean conditionPileAscendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 3)
			if ((entr�e.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10))
				erreur = true;
		return erreur;
	}

	/*
	 * V�rifie si la carte jou�e peut �tre jou�e sur la pile descendante.
	 * @param entr�e, la carte et le ou les signes jou�s sous forme de chaine de caract�res.
	 * @param carte, la carte jou�e sous forme d'entier.
	 * @param j, le joueur qui a jou�.
	 * @param jAdv, le joueur adverse.
	 * @return erreur, le bool�en v�rifiant si la carte peut �tre jou� sur sa pile descendante.
	 */
	private static boolean conditionPileDescendante(String entr�e, int carte, Joueur j, Joueur jAdv) {
		boolean erreur = false;
		if (entr�e.length() == 3)
			if ((entr�e.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileDescendante() + 10))
				erreur = true;
		return erreur;
	}
	
	/*
	 * V�rifie si les cartes saisies sont valables.
	 * @param tab, le tableau de chaine de caract�res contenant dans chaque case une carte 
	 * 	           et l'endroit o� elle est jou�e.
	 * @param carte, la carte jou�e sous forme d'entier.
	 * @param j, le joueur qui a jou�.
	 * @param jAdv, le joueur adverse.
	 * @return boolean, le bool�en v�rifiant si il y a une erreur de saisie ou non.
	 */
	private static boolean v�rifSaisie(String[] tab, Joueur j, Joueur jAdv) {
		boolean pos�SurPileAdverse = false;
		int tmpAsc = j.getPileAscendante();
		int tmpDesc = j.getPileDescendante();
		for (String entr�e : tab) {		
			int carte = Integer.valueOf(entr�e.substring(0, 2)); // La variable prend la valeur de chaque entr�e.
			// V�rifie si c'est un nombre
			if (!Character.isDigit(entr�e.charAt(0)) || !Character.isDigit(entr�e.charAt(1)))
				return false;

			// V�rifie que chaque 'entr�e' ne fait pas moins de 3 carat�res plus de 4 caract�res.
			if (entr�e.length() < 3 || entr�e.length() > 4)
				return false;


			// V�rifie si les cartes ne sont pas jou�es en double. 
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
			
			// V�rifie que le coup jouer � plus.
			if (tab.length < 2)
				return false;
			
			// V�rifie si les cartes sont dans la main du joueur.
			boolean erreur = false;
			for (int carteMain : j.getMain()) {
				if (carteMain == carte) {
					erreur = true;
					break;
				}
			}
			if (erreur == false)
				return false;

			// V�rifie que les entr�es peuvent se poser sur au moins une pile.
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
			else 
				if (conditionPileAdverseAsc(entr�e, carte, j, jAdv)
				|| conditionPileAdverseDesc(entr�e, carte, j, jAdv)) {
					if (pos�SurPileAdverse == true)
						return false;
					pos�SurPileAdverse = true;
				} 	
				else
						return false;
		}
		return true;
	}
	
	/*
	 * V�rifie si au moins 2 cartes en main sont jouables
	 * @param j, le joueur qui doit jouer.
	 * @param jAdv, le joueur adverse.
	 * @return boolean, le bool�en v�rifiant si le joueur peut jouer.
	 */
	private static boolean v�rifCartesJouables(Joueur j, Joueur jAdv) {
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
	
	
	// M�thodes publiques
	
	/*
	 * D�compose les diff�rentes cartes jou�es, et si elles sont valides, les poses sur les piles correspondantes.
	 * @param s, les diff�rentes cartes jou�es
	 * @param j, le joueur qui a jouer.
	 * @param jAdv, le joueur adverse.
	 * @return boolean, le bool�en v�rifiant si le coup est valide.
	 */
	public static boolean d�composeCartes(String s, Joueur j, Joueur jAdv) {	
		String[] tab = s.trim().split("\\s+");
		int carte = 0;
		boolean pos�SurPileAdverse = false;
		if (v�rifSaisie(tab, j, jAdv)) {
			for (String entr�e : tab) {
				carte = Integer.valueOf(entr�e.substring(0, 2));
				if (conditionPileAdverseAsc(entr�e, carte, j, jAdv)) {
					jAdv.poserPileAscendante(carte);
					pos�SurPileAdverse = true;
				} 
				else if (conditionPileAdverseDesc(entr�e, carte, j, jAdv)) {
					jAdv.poserPileDescendante(carte);
					pos�SurPileAdverse = true;
				} 
				else if (conditionPileAscendante(entr�e, carte, j, jAdv))
					j.poserPileAscendante(carte);
				else if (conditionPileDescendante(entr�e, carte, j, jAdv))
					j.poserPileDescendante(carte);
			}
			j.piocherPour(tab.length, pos�SurPileAdverse);
			return true;
		} else
			return false;
	}
	
	/*
	 * Permet de savoir si un joueur a gagn�.
	 * @param j1, le premier joueur.
	 * @param j2, le deuxi�me joueur.
	 * @param tourDeJ1, le bool�en repr�sentant s'il s'agit du tour du joueur 1.
	 * @return boolean, le bool�en v�rifiant si un joueur a gagn�.
	 */
	public static boolean conditionVictoire(Joueur j1, Joueur j2, boolean tourDeJ1) {
		// V�rifie si les joueurs ont fini leurs mains 
		// (et leurs pioches par cons�quent puisqu'ils n'ont pas pioch� de cartes)
		// et donc gagn� la partie.
		 if(j1.getMain().size() == 0) {
			j1.setPartieGagn�e();
			return true;
		}
		 else if(j2.getMain().size() == 0) {
			j2.setPartieGagn�e();
			return true;
		}
		// V�rifie si les joueurs n'ont plus qu'une carte en main ce qui leur fait perdre la partie
		 else if(j1.getMain().size() == 1) {
			j2.setPartieGagn�e();
			return true;
		}
		else if(j2.getMain().size() == 1) {
			j1.setPartieGagn�e();
			return true;
		}
		// V�rifie si le joueur � au moins 2 cartes jouables, sinon il perd.
		else if(!v�rifCartesJouables(j1,j2) && tourDeJ1) {
			j2.setPartieGagn�e();
			return true;
		}
		else if(!v�rifCartesJouables(j2,j1) && !tourDeJ1) {
			j1.setPartieGagn�e();
			return true;
		}
		return false;
	}

	/*
	 * D�termine si la partie est finie
	 * @param j1, le joueur 1.
	 * @param j2, le joueur 2.
	 * @return boolean, le bool�en v�rifiant si la partie est finie.
	 */
	public static boolean partieFinie(Joueur j1, Joueur j2) {
		if (j1.aGagn�())
			return true;
		else if (j2.aGagn�()) 
			return true;
		else
			return false;
	}
	
}
