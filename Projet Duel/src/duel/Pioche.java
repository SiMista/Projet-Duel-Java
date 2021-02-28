package duel;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {

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
	/* Du coup cette méthode elle sert pas vu qu'on peut l'utiliser à partir de Java comme ça 
	 * System.out.println(NORD.getPioche().getPiocheListe().toString())
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
	*/

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
