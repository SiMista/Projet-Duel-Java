package duel;

import java.util.ArrayList;
import java.util.Random;


public class Pioche {
	private static final int taille = 58;
	private static ArrayList<Integer> pioche;

	public Pioche() {
		pioche = new ArrayList<Integer>(taille);
	}
	
	
	public ArrayList<Integer> mélange() {
		for (int i = 1; i <= taille; i++) {
			pioche.add(i);
		}

		ArrayList<Integer> randomValues = new ArrayList<Integer>(taille);
		Random random = new Random();
		int pos = 0;

		while (pioche.size() > 0) {
			pos = random.nextInt(pioche.size());
			randomValues.set(pioche.size() - 1, pioche.get(pos));
			pioche.remove(pos);
		}
		return randomValues;
	}

	public String toString(ArrayList<Integer> pioche) {
		String cartesDeLaPioche = "[";
		for (int cartes : pioche) {
			cartesDeLaPioche = String.valueOf(pioche.get(cartes)) + ", ";
		}
		return cartesDeLaPioche;
	}
}
