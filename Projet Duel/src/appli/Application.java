package appli;

import duel.Pioche;
import duel.R�gles;
import duel.Joueur;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Joueur NORD = new Joueur("NORD");
		Joueur SUD = new Joueur("SUD");
		System.out.println(NORD.afficherPiles());
		System.out.println(SUD.afficherPiles());
		System.out.println(SUD.getPioche().getPiocheListe().toString());
		Scanner sc = new Scanner(System.in);
		String s;
		Boolean valide;
		System.out.print("> ");
		s = sc.nextLine();
		while (!s.equals("fin")) {
			valide = R�gles.d�compose(s, NORD);
			if (valide)
				System.out.print("> ");
			else
				System.out.print("#> ");
			s = sc.nextLine();

		}
	}
}
