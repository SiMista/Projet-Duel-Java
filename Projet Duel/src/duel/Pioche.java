package duel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Pioche {
	private static final int taille=58;
	private ArrayList<Integer> pioche;
	
	public Pioche() {
		ArrayList<Integer> pioche = new ArrayList<Integer>(taille);
	}
	
	public static ArrayList<Integer> piocheAléatoire(Pioche pioche) {
        for (int i = 1; i <= taille; i++) {
            pioche.add(i);
        }
 
        ArrayList<Integer> randomValues = new ArrayList<Integer>(taille);
        Random random = new Random();
        int pos = 0;
 
        while (pioche.size() > 0) {
            pos = random.nextInt(pioche.size());
            randomValues.set(pioche.size()-1, pioche.get(pos));
            pioche.remove(pos);
        }
 
        return randomValues;
    }
	
	public static String toString(int i, ArrayList<Integer> pioche) {
		return String.valueOf(pioche.get(i));
	}
	

	
	public static void main(String args[]) {
        int x = 5;
        String str_x = String.valueOf(x);
        System.out.println(str_x);
    }
}
