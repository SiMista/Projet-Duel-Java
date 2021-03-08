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
	
	public int getCartes(int i) { 
		return piocheliste.get(i);
	}
	
	public int getTaille() {
		return taille;
	}
	
	public ArrayList<Integer> getPiocheListe(){
		return piocheliste;
	}
	
	public void retirerCarte(int i) {
		piocheliste.remove(i);
		taille--;
	}
}
