package appli;

import duel.Pioche;

public class Application {

	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		pioche.m�lange();
		System.out.println(pioche.toString());
	}

	// public void piocherCartes(int cartejou�es);
	
}
