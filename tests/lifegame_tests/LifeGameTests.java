package lifegame_tests;

import static org.junit.Assert.*;
import org.junit.*;
import lifegame.model.*;
import lifegame.model.exceptions.*;
import lifegame_tests.TestUtil.*;

/**
 * <b>LifeGameTests:</b><br>
 * <ul>
 * <li><b>methods</b>
 * <ul>
 * 	<li>sardineNotEqualsRequin</li>
 * 	<li>setSardineZeroAge</li>
 * 	<li>setRequinZeroAge</li>
 * 	<li>setSardineNegativeAge</li>
 * 	<li>setRequinNegativeAge</li>
 * 	<li>setSardineHugeAge</li>
 * 	<li>setRequinHugeAge</li>
 * 	<li>sardineDieAutomaticallyBeforePotentialMaxAge</li>
 * 	<li>requinDieAutomaticallyBeforePotentialMaxAge</li>
 * </ul>
 * @author <ul><li>Jesus GARNICA OLARRA.</li><li>Hivinau GRAFFE.</li></ul>
 * @version 1.0
 */
public class LifeGameTests {

	@Test
	public void sardineNotEqualsRequin() {
		
		Sardine sardine = new Sardine(0, 0);
		Requin requin = new Requin(0, 0);
		
		assertNotSame(sardine, requin);
	}
	
	@Test(expected=PoissonException.class)
	public void setSardineZeroAge() throws PoissonException {
		
		TestUtil.setPoissonAge(PoissonType.SARDINE, 0.0d);
	}
	
	@Test(expected=PoissonException.class)
	public void setRequinZeroAge() throws PoissonException {
		
		TestUtil.setPoissonAge(PoissonType.REQUIN, 0.0d);
	}

	@Test(expected=PoissonException.class)
	public void setSardineNegativeAge() throws PoissonException {
		
		TestUtil.setPoissonAge(PoissonType.SARDINE, -1.0d);
	}

	@Test(expected=PoissonException.class)
	public void setRequinNegativeAge() throws PoissonException {
		
		TestUtil.setPoissonAge(PoissonType.REQUIN, -1.0d);
	}

	@Test(expected=PoissonException.class)
	public void setSardineHugeAge() throws PoissonException {
		
		TestUtil.setPoissonAge(PoissonType.SARDINE, 100.0d);
	}

	@Test(expected=PoissonException.class)
	public void setRequinHugeAge() throws PoissonException {
		
		TestUtil.setPoissonAge(PoissonType.REQUIN, 100.0d);
	}
	
	@Test
	public void sardineDieAutomaticallyBeforePotentialMaxAge()  {
		
		assertTrue(TestUtil.poissonDieAutomaticallyBeforePotentialMaxAge(PoissonType.SARDINE));
	}
	
	@Test
	public void requinDieAutomaticallyBeforePotentialMaxAge()  {
		
		assertTrue(TestUtil.poissonDieAutomaticallyBeforePotentialMaxAge(PoissonType.REQUIN));
	}
	
}
