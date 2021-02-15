package duel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Pioche {
	private static final int taille=58;
	private ArrayList<Integer> pioche;
	
	public static ArrayList<Integer> piocheAléatoire(int taille) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 1; i <= taille; i++) {
            intList.add(i);
        }
 
        ArrayList<Integer> randomValues = new ArrayList<Integer>(taille);
        Random random = new Random();
        int pos = 0;
 
        while (intList.size() > 0) {
            pos = random.nextInt(intList.size());
            randomValues.set(intList.size()-1, intList.get(pos));
            intList.remove(pos);
        }
 
        return randomValues;
    }
	
	public static String toString(int taille, ArrayList<Integer> pioche) {
		 int[] tab = new int[taille];
	        for (int[] ligne : tab)
	        	for(int i = 0; i < taille; i++) {
	        		Arrays.fill(ligne, i);
	        }
	        return tab.toString();
	}
}
