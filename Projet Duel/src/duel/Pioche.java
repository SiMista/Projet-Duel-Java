package duel;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
	// Je sais pas si ça doit être static ou pas ses attributs
	private ArrayList<Integer> piocheliste; 
	private int taille;

	public Pioche() {
		taille = 58;
		piocheliste = new ArrayList<Integer>();
		for (int i = 0; i < taille + 2; i++) {
			if (i > 1)
				piocheliste.add(i);
		}
		mélange(piocheliste);
	}
	
	// La fonction mélange, si tu la comprends dis moi parce que jcomprends archi pas mais oklm
	public void mélange(ArrayList<Integer> p) {
	        Random random = new Random();
	        for (int i = 0; i < taille; ++i) {
	            int j = (int) (random.nextDouble() * (taille - i) + i);
	            int tmp = p.get(i);
	            p.set(i , p.get(j));
	            p.set(j, tmp);
	        }
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

	/* S/o Poitrenaud */
	public ArrayList<Integer> getPiocheListe() { 
		return piocheliste;
	}
	
	public int getCartesPioche(int i) { 
		return piocheliste.get(i);
	}
	
	public void retirerCartes(int i) {
		piocheliste.remove(i);
	}
}
