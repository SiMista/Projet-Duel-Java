package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duel.Joueur;
import duel.Partie;

class PartieTest {
	Joueur TEST = new Joueur("TEST");
	Joueur TESTadv = new Joueur("TESTadv");

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
