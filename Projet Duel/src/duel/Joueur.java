package duel;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private ArrayList<Integer> main;
	private Pioche pioche;
	private int pileAscendante;
	private int pileDescendante;

	public Joueur(String nomDuJoueur) {
		nom = nomDuJoueur;
		main = new ArrayList<Integer>();
		pioche = new Pioche();
		pileAscendante = 1;
		pileDescendante = 60;
		piocher(6);
	}

	public void piocher(int nb) {
		int i;
		for (i = 0; i < nb; i++) {
			if (pioche.getPiocheListe().size() > 0) {
				main.add(i, (pioche.getCartesPioche(i)));
				pioche.retirerCartes(i);
				}
			else 
				break;	
		}
		System.out.println(nb + " cartes pos�es, " + i + " cartes pioch�es");
		
		// Afficher la main du joueur qui vient de jouer
		// (donc pas le bon vu qu'il faut afficher la main du joueur qui va jouer)
		System.out.println("cartes " + nom + " " + afficherMain());
	}


	public void jouerCartes() {
		Scanner sc = new Scanner(System.in);
		String s;
		System.out.print("> ");
		s = sc.nextLine();
		while (!s.equals("fin")) {
			d�compose(s);
			System.out.print("> ");
			s = sc.nextLine();
		}
	}

	public void d�compose(String s) {
		// une solution
		String[] tab = s.split("\\s+");
		for (String mot : tab)
			System.out.println(mot);

		// une autre solution
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
	}

	public boolean gagnerPartie() {
		boolean b = false;
		return b;
	}

	public boolean perdrePartie() {
		boolean b = false;
		return b;
	}

	public String afficherMain() {
		String cartesDeLaMain = "{ ";
		for (int i = 0; i < main.size(); i++) {
			if (i < main.size() - 1)
				cartesDeLaMain += String.valueOf(main.get(i)) + " ";
			else
				cartesDeLaMain += String.valueOf(main.get(i)) + " }";
		}
		return cartesDeLaMain;
	}
	
	//Fonction pour afficher les piles des joueurs
	public String afficherPiles(Joueur joueur) {
		String PilesJoueur = joueur.nom + " ^" +  Integer.toString(joueur.pileAscendante) +" v";
		PilesJoueur += Integer.toString(joueur.pileDescendante)+ " (m" + joueur.main.size() + "p" + joueur.pioche.getPiocheListe().size() + ")";
		return PilesJoueur;
	}

	public Pioche getPioche() {
		return pioche;
	}
}