package appli;

import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		pioche.mélange();
		System.out.println(pioche.toString());
	}

	// public void piocherCartes(int cartejouées);
	
}
