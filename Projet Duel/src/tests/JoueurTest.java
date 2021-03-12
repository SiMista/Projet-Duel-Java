package tests;

import duel.Joueur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;



class JoueurTest {
	Joueur TEST = new Joueur("TEST");
	Joueur TESTadv = new Joueur("TESTadv");
	
	@Test
	void testJoueur() throws NoSuchFieldException, IllegalAccessException {
        final Field testNom = TEST.getClass().getDeclaredField("nom");
        testNom.setAccessible(true);
        assertEquals("TEST", testNom.get(TEST));
        
        final Field testPileAsc = TEST.getClass().getDeclaredField("pileAscendante");
        testPileAsc.setAccessible(true);
        assertEquals(1, testPileAsc.get(TEST));
        
        final Field testPileDesc = TEST.getClass().getDeclaredField("pileDescendante");
        testPileDesc.setAccessible(true);
        assertEquals(60, testPileDesc.get(TEST));
        
        final Field testMain = TEST.getClass().getDeclaredField("main");
        testMain.setAccessible(true);
        assertNotNull(testMain.get(TEST));
	}


	@Test
	void testPiocher() {
		TEST.piocherPour(1, false);
		assertEquals(6 + 2, TEST.getMain().size());
	}

	@Test
	void testPoserPileAscendante() {
		TEST.poserPileAscendante(5);
		assertEquals(5, TEST.getPileAscendante());
	}

	@Test
	void testPoserPileDescendante() {
		TEST.poserPileAscendante(50);
		assertEquals(50, TEST.getPileAscendante());
	}

	@Test
	void testPoserPileAdverseAsc() {
		TEST.poserPileAdverseAsc(5, TESTadv);
		assertEquals(5, TESTadv.getPileAscendante());
	}

	@Test
	void testPoserPileAdverseDesc() {
		TEST.poserPileAdverseDesc(5, TESTadv);
		assertEquals(5, TESTadv.getPileDescendante());
	}
}
