package appli;

import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pioche pioche = new Pioche();
		pioche.initialiser();
		pioche.mélange();
		System.out.println(pioche.toString());
	}


	

	// public void piocherCartes(int cartejouées);
	
	
	
	
}
