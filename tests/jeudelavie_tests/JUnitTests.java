package jeudelavie_tests;

import static org.junit.Assert.*;
import org.junit.Test;

import jeudelavie.librairies.*;

public class JUnitTests {

	@Test
	public void test_PoissonsNotSame() {
		
		Poisson poisson1 = new Poisson();
		Poisson poisson2 = new Poisson();
		
		assertNotSame(poisson1, poisson2);
	}

	@Test(expected=jeudelavie.librairies.exceptions.PoissonException.class)
	public void test_PoissonSetHugeAge() throws jeudelavie.librairies.exceptions.PoissonException {
		
		Poisson poisson = new Poisson();
		poisson.setAge(100f);
	}
}
