package duel;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {

	private ArrayList<Integer> piocheliste; 
	private int taille;

	public Pioche() {
		taille = 10;
		piocheliste = new ArrayList<Integer>();
		for (int i = 0; i < taille + 2; i++) {
			if (i > 1)
				piocheliste.add(i);
		}
		Collections.shuffle(piocheliste);
	}
	
	public int getCarte() { 
		taille--;
		return piocheliste.remove(0);
	}
	
	public int getTaille() {
		return taille;
	}
	
}
