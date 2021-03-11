package duel;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {

	private ArrayList<Integer> piocheListe; 
	private int taille;

	public Pioche() {
		taille = 58;
		piocheListe = new ArrayList<Integer>();
		for (int i = 0; i < taille + 2; i++) {
			if (i > 1)
				piocheListe.add(i);
		}
		Collections.shuffle(piocheListe);
	}
	
	public int getCarte() { 
		taille--;
		return piocheListe.remove(0);
	}
	
	public int getTaille() {
		return taille;
	}
	
}
