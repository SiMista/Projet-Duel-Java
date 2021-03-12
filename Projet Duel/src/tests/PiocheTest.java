package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import duel.Pioche;

class PiocheTest {

	@Test
	void testPioche() throws NoSuchFieldException, IllegalAccessException {
		Pioche PIOCHE = new Pioche();
		final Field testNom = PIOCHE.getClass().getDeclaredField("taille");
		testNom.setAccessible(true);
		assertEquals(58, testNom.get(PIOCHE));

		final Field testMain = PIOCHE.getClass().getDeclaredField("piocheListe");
		testMain.setAccessible(true);
		assertNotNull(testMain.get(PIOCHE));

	}
}
