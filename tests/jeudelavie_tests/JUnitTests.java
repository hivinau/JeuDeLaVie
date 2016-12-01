package jeudelavie_tests;

import static org.junit.Assert.*;
import org.junit.Test;

public class JUnitTests {

	@Test
	public void test_SardineIsNotRequin() {
		
		jeudelavie.librairies.Sardine sardine = new jeudelavie.librairies.Sardine(0, 0);
		jeudelavie.librairies.Requin requin = new jeudelavie.librairies.Requin(0, 0);
		
		assertNotSame(sardine, requin);
	}

	@Test(expected=jeudelavie.librairies.exceptions.PoissonException.class)
	public void test_SardineSetHugeAge() throws jeudelavie.librairies.exceptions.PoissonException {
		
		jeudelavie.librairies.Sardine sardine = new jeudelavie.librairies.Sardine(0, 0);
		sardine.setAge(100f);
	}

	@Test(expected=jeudelavie.librairies.exceptions.PoissonException.class)
	public void test_RequinSetHugeAge() throws jeudelavie.librairies.exceptions.PoissonException {
		
		jeudelavie.librairies.Requin requin = new jeudelavie.librairies.Requin(0, 0);
		requin.setAge(100f);
	}
}
