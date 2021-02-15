package appli;

import java.util.ArrayList;
import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pioche pioche;
		pioche = new Pioche();
		pioche = Pioche.piocheAléatoire(pioche);
		for (int i = 0; i < pioche.size(); i++) {
			System.out.println(Pioche.toString(i, pioche));
		}
	}
	

	// public void piocherCartes(int cartejouées);
	
	
	
	
}
