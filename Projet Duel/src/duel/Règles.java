package duel;

import java.util.Scanner;

public class Règles {
	private boolean tourDeNord = true;
	
	public void jouerUnCoup(Joueur JoueurActif, Joueur JoueurPassif) {
		
	}
	
	public static void jouerCartes() {
		Scanner sc = new Scanner(System.in);
		String s;
		System.out.print("> ");
		s = sc.nextLine();
		while (!s.equals("fin")) {
			décompose(s);
			System.out.print("> ");
			s = sc.nextLine();
		}
	}

	public static void décompose(String s) {
		// une solution
		String[] tab = s.split("\\s+");
		for (String mot : tab)
			if (mot.charAt(0) == '"') {
			
		}
			else if (mot.charAt(0) == '^') {
				
		} 	else if (mot.charAt(0) == '^') {
			
		}
		
		
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
}
