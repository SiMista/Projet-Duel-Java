package appli;

import duel.Partie;
import duel.Joueur;

import java.util.Scanner;

public class Application {
	
	private static void afficherPilesEtMain(Joueur j1, Joueur j2, boolean tourDeJ1) {
		System.out.println(j1.afficherPiles());
		System.out.println(j2.afficherPiles());
		if (tourDeJ1) {
			System.out.println(j1.afficherMain());
		}
		else {
			System.out.println(j2.afficherMain());
		}
		System.out.print("> ");
	}
	

	public static void main(String[] args) {
		Joueur NORD = new Joueur("NORD");
		Joueur SUD = new Joueur("SUD");
		Scanner sc = new Scanner(System.in);
		String s;
		Boolean tourDeNord = true;
		Boolean valide;
		
		afficherPilesEtMain(NORD, SUD, tourDeNord);
		s = sc.nextLine();
		while (!Partie.partieFinie(NORD, SUD)) {
			if (tourDeNord)
				valide = Partie.décomposeCartes(s, NORD, SUD);
			else
				valide = Partie.décomposeCartes(s, SUD, NORD);
			if (!valide)
				while(!valide) {
					System.out.print("#> ");	
					s = sc.nextLine();
					if (tourDeNord)
						valide = Partie.décomposeCartes(s, NORD, SUD);
					else
						valide = Partie.décomposeCartes(s, SUD, NORD);
				}
			if (tourDeNord)
				tourDeNord = false;
			else
				tourDeNord = true;
			if (!Partie.partieFinie(NORD, SUD)) {
				afficherPilesEtMain(NORD, SUD, tourDeNord);
				s = sc.nextLine();
				}
			if(NORD.aGagné()) 
				System.out.println("partie finie, NORD a gagné");
			else if(SUD.aGagné())
				System.out.println("partie finie, SUD a gagné");
		}
	    sc.close();
	}
}