package duel;

import java.util.ArrayList;
import java.util.Random;


public class Pioche {
	private static ArrayList<Integer> pioche;
	private int taille;

	public Pioche() {
		taille = 58;
		pioche = new ArrayList<Integer>();
		for (int i = 0; i < taille; i++) {
			pioche.add(i);
		}
	}
	
	
	public void mélange() {

/*
		ArrayList<Integer> randomValues = new ArrayList<Integer>(taille);
		Random random = new Random();
		int pos = 0;

		while (pioche.size() > 0) {
			pos = random.nextInt(pioche.size());
			randomValues.set(pioche.size() - 1, pioche.get(pos));
			pioche.remove(pos);
		}
		return randomValues;
		*/
		return;
	}

	public String toString() {
		String cartesDeLaPioche = "[";
		for (int cartes : pioche) {
			cartesDeLaPioche += String.valueOf(pioche.get(cartes)) + ", ";
		}
		return cartesDeLaPioche;
	}
}
