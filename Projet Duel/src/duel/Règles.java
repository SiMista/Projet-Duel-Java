package duel;

import java.util.Scanner;

public class Règles {
	private boolean tourDeNord = true;

	public void jouerUnCoup(Joueur JoueurActif, Joueur JoueurPassif) {

	}
/*
	public void jouerCartes(Joueur j) {
		Scanner sc = new Scanner(System.in);
		String s;
		Boolean valide = false;
		System.out.print("> ");
		s = sc.nextLine();
		while (!s.equals("fin")) {
			valide = décompose(s, j);
			if (valide)
				System.out.print("> ");
			else
				System.out.print("#> ");
			s = sc.nextLine();
		}
	}
*/
	public static boolean décompose(String s, Joueur j) {
		String[] tab = s.split("\\s+");
		int carte = 0;
		if (vérifSaisie(tab, j)) {
			for (String mot : tab) {
				// if (mot.charAt(3) == "'")
				carte = Integer.valueOf(mot.substring(0, 1));
				if (poserPileAscendante(mot, carte, j)) { 
					j.setPileAscendante(carte);
					j.retirerMain(carte);
				} else if (poserPileDescendante(mot, carte, j)) {
					j.setPileDescendante(carte);
					j.retirerMain(carte);
				}
			}
			return true;
		} else {
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

	public static boolean poserPileAscendante(String mot, int carte, Joueur j) {
		if ((mot.charAt(2) == '^') && (carte > j.getPileAscendante()) || (carte == j.getPileAscendante() - 10)) {
			return true;
		} else
			return false;
	}

	public static boolean poserPileDescendante(String mot, int carte, Joueur j) {
		if ((mot.charAt(2) == 'v') && (carte < j.getPileDescendante()) || (carte == j.getPileAscendante() + 10)) {
			return true;
		} else
			return false;
	}

	public static boolean vérifSaisie(String[] tab, Joueur j) {
		boolean erreur = true;
		int carte = 0;
		for (String mot : tab) {
			carte = Integer.valueOf(mot.substring(0, 1)); // la variable locale prend la valeur de chaque nombre du tab[]
			for (int carteMain : j.getMain()) // Vérifier si les cartes sont dans la main du joueur
				if (carteMain != carte)
					erreur = false; // IL FAUT COMPARER TOUTES LES CARTES ET PAS QU'UNE !
			if (!Character.isDigit(mot.charAt(0)) && !Character.isDigit(mot.charAt(1))) // Vérifier si il s'agit bien de nombre
				erreur = false;

		}
		return erreur;
	}
}
