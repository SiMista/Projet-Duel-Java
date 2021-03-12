package appli;

import duel.Joueur;
import duel.Partie;

import java.util.Scanner;

/*
 * La classe application permet d'enregistrer les coups saisis par les joueurs, 
 * les faire rejouer si le coup n'est pas valide 
 * et d'afficher les mains, les piles, le nombre de cartes jou�es et pioch�es et le gagnant de la partie.
 * @author DE ALMEIDA Jules & DEIVA Sim�on 
 */
public class Application {
	
	/*
	 * Afficher les piles et la main du joueur actif.
	 * @param j1, le joueur 1.
	 * @param j2, le joueur 2.
	 * @param tourDeJ1, le bool�en v�rifiant s'il s'agit du tour du joueur 1.
	 */
	private static void afficherPilesEtMain(Joueur j1, Joueur j2, boolean tourDeJ1) {
		System.out.println(j1.afficherPiles());
		System.out.println(j2.afficherPiles());
		if (tourDeJ1)
			System.out.println(j1.afficherMain());
		else 
			System.out.println(j2.afficherMain());
	}
	
	public static void main(String[] args) {
		
		// Attributs
		Joueur NORD = new Joueur("NORD");
		Joueur SUD = new Joueur("SUD");
		Scanner sc = new Scanner(System.in);
		String s;
		Boolean tourDeNord = true;
		Boolean valide;	
		
		// Boucle permettant de jouer des coups jusqu'� ce que la partie soit finie.
		while (!Partie.partieFinie(NORD, SUD)) {
			afficherPilesEtMain(NORD, SUD, tourDeNord);
			if(Partie.conditionVictoire(NORD, SUD, tourDeNord))
				break;
			System.out.print("> ");
			s = sc.nextLine();
			if (tourDeNord) {
				valide = Partie.d�composeCartes(s, NORD, SUD);
			}
			else {
				valide = Partie.d�composeCartes(s, SUD, NORD);
			}
			if (!valide)
				while(!valide) {
					System.out.print("#> ");	
					s = sc.nextLine();
					if (tourDeNord)
						valide = Partie.d�composeCartes(s, NORD, SUD);
					else
						valide = Partie.d�composeCartes(s, SUD, NORD);
				}
			if (tourDeNord == true)
				tourDeNord = false;
			else 
				tourDeNord = true;
		}
		
		// Fin de Partie et affichage du vainqueur.
		if(NORD.aGagn�()) 
			System.out.println("partie finie, NORD a gagn�");
		else
			System.out.println("partie finie, SUD a gagn�");
	    sc.close();
	}
}