package duel;

import java.util.Scanner;

public class Règles {
	private boolean tourDeNord = true;
	
	public void jouerUnCoup(Joueur JoueurActif, Joueur JoueurPassif) {
		
	}
	
	public static void jouerCartes() {
		Scanner sc = new Scanner(System.in);
		String s;
		Boolean valide;
		System.out.print("> ");
		s = sc.nextLine();
		while (!s.equals("fin")) {
			valide = décompose(s);
			if (valide) 
			System.out.print("> ");
			else System.out.print("#> ");
			s = sc.nextLine();
		}
	}

	public static boolean décompose(String s) {
		// une solution
		String[] tab = s.split("\\s+");
		if (vérifSaisie(tab)) {
			for (String mot : tab) {
				if (mot.charAt(2) == '^') {
			// poser sur la pile ascendante
				}
				else if (mot.charAt(2) == 'v') {
			// poser sur la pile descendante
				}
			}
			return true;
		}
		else {
			return false;
		}
		
		/*
		Scanner scs = new Scanner(s);
		while (scs.hasNext()) {
			String mot = scs.next();
			if (Character.isDigit(mot.charAt(0))) {
				int n = Integer.parseInt("" + mot.charAt(0));
				System.out.println("'" + mot + "' commence par " + n);
			} else
				System.out.println("'" + mot + "' ne commence pas par un chiffre");
		}
		scs.close();
		*/
	}
	
	public static boolean vérifSaisie(String[] tab) {
		boolean erreur = false;
		for (String mot : tab) {
			//vérifier les règles de pose
		}
		return erreur;
	}
}
