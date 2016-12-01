package jeudelavie_tests;

import static org.junit.Assert.*;
import org.junit.Test;

public class JUnitTests {

	@Test
	public void test_PoissonsNotSame() {
		
		jeudelavie.librairies.Poisson poisson1 = new jeudelavie.librairies.Poisson();
		jeudelavie.librairies.Poisson poisson2 = new jeudelavie.librairies.Poisson();
		
		assertNotSame(poisson1, poisson2);
	}

	@Test(expected=jeudelavie.librairies.exceptions.PoissonException.class)
	public void test_PoissonSetHugeAge() throws jeudelavie.librairies.exceptions.PoissonException {
		
		jeudelavie.librairies.Poisson poisson = new jeudelavie.librairies.Poisson();
		poisson.setAge(100f);
	}
}
