package duel;

<<<<<<< Updated upstream
public class R�gles {
	
=======
import java.util.Scanner;

public class R�gles {
	private boolean tourDeNord = true;
	
	public void jouerUnCoup(Joueur JoueurActif, Joueur JoueurPassif) {
		
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
		/* une solution
		String[] tab = s.split("\\s+");
		for (String mot : tab)
			System.out.println(mot);
*/
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
>>>>>>> Stashed changes
}
