package appli;

import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		System.out.println(pioche.getPiocheListe().toString());
		Pioche pioche2 = new Pioche(); 
		System.out.println(pioche2.getPiocheListe().toString());
		
	}

	// public void piocherCartes(int cartejouées);
	
}
