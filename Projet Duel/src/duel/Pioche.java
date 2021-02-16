package duel;

import java.util.ArrayList;
import java.util.Random;


public class Pioche {
	public static ArrayList<Integer> piocheliste;
	private int taille;

	public Pioche() {
		taille = 58;
		piocheliste = new ArrayList<Integer>();
		for (int i = 0; i < taille+2; i++) {
			piocheliste.add(i);
		}
		piocheliste.remove(Integer.valueOf(0));
		piocheliste.remove(Integer.valueOf(1));
	}
	

	//J'ai essayer des trucs avec la fonction mélange mais ça marche pas quoi
	/*public ArrayList<Integer> mélange(Pioche pioche2) {
		Pioche piocheAléatoire = new Pioche();
		Random random = new Random();
		int pos = 0;
		while (pioche2.piocheliste.size() > 0) {
			pos = random.nextInt(pioche2.piocheliste.size());
			piocheAléatoire.piocheliste.set(pioche2.piocheliste.size() - 1, pioche2.piocheliste.get(pos));
			pioche2.piocheliste.remove(pos);
		}
		return piocheAléatoire.piocheliste;
	}*/
	
	
	public void mélange() {
		/*ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 1; i <= taille; i++) {
            intList.add(i);
        }
 
        int[] randomValues = new int[intList.size()];
        Random random = new Random();
        int pos = 0;
 
        while (intList.size() > 0) {
            pos = random.nextInt(intList.size());
            randomValues[intList.size() - 1] = intList.get(pos);
            intList.remove(pos);
        }
 
        return randomValues;*/
		return;
    }
	

	public String toString() {
		String cartesDeLaPioche = "[";
		for (int cartes : piocheliste) {
			if (piocheliste.get(cartes) < 59)
				cartesDeLaPioche += String.valueOf(piocheliste.get(cartes)) + ", ";
			else
				cartesDeLaPioche += String.valueOf(piocheliste.get(cartes)) + "]";
		}
		return cartesDeLaPioche;
	}
}
