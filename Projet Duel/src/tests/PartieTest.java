package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import duel.Joueur;
import duel.Partie;



class PartieTest {
	Joueur TEST = new Joueur("TEST");
	Joueur TESTadv = new Joueur("TESTadv");

	/*
	 * Tests invalides :
	 * conditionPileAdverseAsc(String entrée, int carte, Joueur j, Joueur jAdv)
	 * conditionPileAdverseDesc(String entrée, int carte, Joueur j, Joueur jAdv)
	 * conditionPileAscendante(String entrée, int carte, Joueur j, Joueur jAdv)
	 * conditionPileDescendante(String entrée, int carte, Joueur j, Joueur jAdv)
	 * vérifSaisie(String[] tab, Joueur j, Joueur jAdv)
	 * décomposeCartes(String s, Joueur j, Joueur jAdv)
	 */
	
	// Tests valides
	@Test
	void testConditionVictoire() {
		assertFalse(Partie.conditionVictoire(TEST, TESTadv, true));
		for (int i = 0; i < 6; i++)
			TEST.getMain().remove(0);
		assertTrue(Partie.conditionVictoire(TEST, TESTadv, true));
	}

	@Test
	void testPartieFinie() {
		assertFalse(Partie.partieFinie(TEST, TESTadv));
	}

}
