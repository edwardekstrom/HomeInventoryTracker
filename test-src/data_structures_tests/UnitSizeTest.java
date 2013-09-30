/**
 * 
 */
package data_structures.data_structures_tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data_structures.Barcode;
import data_structures.Product;
import data_structures.UnitSize;

/**
 * @author Capchu
 *
 */
public class UnitSizeTest {
	UnitSize _testUnitSize;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_testUnitSize = new UnitSize();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void makeUnitSizeTest() {
		_testUnitSize = new UnitSize();
		
		assertTrue(_testUnitSize.getUnit().equals("count"));
		assertTrue(_testUnitSize.getAmount() == 1);
	}
	
	@Test
	public void changeAmountCountTest() {
		_testUnitSize = new UnitSize();
		
		//Can't do a float for count
		assertFalse(_testUnitSize.setAmount("4.2"));
		//Can't do non-numbers
		assertFalse(_testUnitSize.setAmount("River"));	
		//can't change amount when unit is count
		assertFalse(_testUnitSize.setAmount("5"));
		//make sure it remains unchanged
		assertTrue(_testUnitSize.getAmount() == 1);	
	}
	
	@Test
	public void changeUnitTest() {
		_testUnitSize = new UnitSize();
		
		//test all viable units
		assertTrue(_testUnitSize.setUnit("ounces"));
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		assertTrue(_testUnitSize.setUnit("pounds"));
		assertTrue(_testUnitSize.getUnit().equals("pounds"));
		
		assertTrue(_testUnitSize.setUnit("grams"));
		assertTrue(_testUnitSize.getUnit().equals("grams"));
		
		assertTrue(_testUnitSize.setUnit("kilograms"));
		assertTrue(_testUnitSize.getUnit().equals("kilograms"));
		
		assertTrue(_testUnitSize.setUnit("gallons"));
		assertTrue(_testUnitSize.getUnit().equals("gallons"));
		
		assertTrue(_testUnitSize.setUnit("quarts"));
		assertTrue(_testUnitSize.getUnit().equals("quarts"));
		
		assertTrue(_testUnitSize.setUnit("pints"));
		assertTrue(_testUnitSize.getUnit().equals("pints"));
		
		assertTrue(_testUnitSize.setUnit("fluid ounces"));
		assertTrue(_testUnitSize.getUnit().equals("fluid ounces"));
		
		assertTrue(_testUnitSize.setUnit("liters"));
		assertTrue(_testUnitSize.getUnit().equals("liters"));
		
		//Invalid Units
		assertFalse(_testUnitSize.setUnit("river"));
		assertFalse(_testUnitSize.getUnit().equals("river"));
		
		assertFalse(_testUnitSize.setUnit("10"));
		assertFalse(_testUnitSize.getUnit().equals("10"));
		
		assertFalse(_testUnitSize.setUnit("fluidounces"));
		assertFalse(_testUnitSize.getUnit().equals("fluidounces"));
				
	}
	
	@Test
	public void changeAmountOtherTest() {
		_testUnitSize = new UnitSize();
		
		//change unit
		_testUnitSize.setUnit("ounces");
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		//integer
		assertTrue(_testUnitSize.setAmount("44"));
		assertTrue(_testUnitSize.getAmount() == 44);

		//float
		assertTrue(_testUnitSize.setAmount("42.34"));
		assertTrue(_testUnitSize.getAmount() == (float) 42.34);
		
		//float
		assertFalse(_testUnitSize.setAmount("Song"));
		assertTrue(_testUnitSize.getAmount() == (float) 42.34);
		
		//Persistence between changes
		_testUnitSize.setUnit("grams");
		assertTrue(_testUnitSize.getUnit().equals("grams"));
		assertTrue(_testUnitSize.getAmount() == (float) 42.34);
		
		//changes to 1 if unit becomes count
		_testUnitSize.setUnit("count");
		assertTrue(_testUnitSize.getAmount() == (float) 1);
		
	}
	
	@Test
	public void isValidUnitAndAmountTest() {

		_testUnitSize = new UnitSize();
		
		//change unit
		_testUnitSize.setUnit("ounces");
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		//check if a string is valid without attempting to make a change
		assertFalse(_testUnitSize.isValidAmount("River"));
		assertTrue(_testUnitSize.isValidAmount("43.3"));
		assertTrue(_testUnitSize.isValidAmount("42"));
		assertFalse(_testUnitSize.isValidAmount("ounces"));
		
		//if count, cant change at all
		_testUnitSize.setUnit("count");
		assertTrue(_testUnitSize.getUnit().equals("count"));
		assertFalse(_testUnitSize.isValidAmount("13"));
		assertFalse(_testUnitSize.isValidAmount("Simon"));
		assertFalse(_testUnitSize.isValidAmount("3.4"));
		assertFalse(_testUnitSize.isValidAmount("ounces"));
		
		//check if a unit is valid w/o making a change
		assertTrue(_testUnitSize.isValidUnit("count"));
		assertFalse(_testUnitSize.isValidUnit("55.33"));
		assertFalse(_testUnitSize.isValidUnit("55"));
		assertFalse(_testUnitSize.isValidUnit("Tam"));
			
	}

}
