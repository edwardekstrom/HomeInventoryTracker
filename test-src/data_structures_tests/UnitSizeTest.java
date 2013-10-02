/**
 * 
 */
package data_structures_tests;

import static org.junit.Assert.*;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;

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
		try {
			_testUnitSize.setAmount("4.2");
		} catch (InvalidAmountException e) {
			
		}
		assertTrue(_testUnitSize.getAmount()==1);
		//Can't do non-numbers
		try {
			_testUnitSize.setAmount("River");
		} catch (InvalidAmountException e) {
			
		}
		assertTrue(_testUnitSize.getAmount()==1);
		//can't change amount when unit is count
		try {
			_testUnitSize.setAmount("5");
		} catch (InvalidAmountException e) {
			
		}
		assertTrue(_testUnitSize.getAmount()==1);
		//make sure it remains unchanged
		assertTrue(_testUnitSize.getAmount() == 1);	
	}
	
	@Test
	public void changeUnitTest() {
		_testUnitSize = new UnitSize();
		
		//test all viable units
		try {
			_testUnitSize.setUnit("ounces");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		try {
			_testUnitSize.setUnit("pounds");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("pounds"));
		
		try {
			_testUnitSize.setUnit("grams");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("grams"));
		
		try {
			_testUnitSize.setUnit("kilograms");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("kilograms"));
		
		try {
			_testUnitSize.setUnit("gallons");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("gallons"));
		
		try {
			_testUnitSize.setUnit("quarts");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("quarts"));
		
		try {
			_testUnitSize.setUnit("pints");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("pints"));
		
		try {
			_testUnitSize.setUnit("fluid ounces");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("fluid ounces"));
		
		try {
			_testUnitSize.setUnit("liters");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("liters"));
		
		//Invalid Units
		try {
			_testUnitSize.setUnit("river");
		} catch (InvalidUnitException e) {
			
		}
		assertFalse(_testUnitSize.getUnit().equals("river"));
		
		try {
			_testUnitSize.setUnit("10");
		} catch (InvalidUnitException e) {
			
		}
		assertFalse(_testUnitSize.getUnit().equals("10"));
		
		try {
			_testUnitSize.setUnit("fluidounces");
		} catch (InvalidUnitException e) {
			
		}
		assertFalse(_testUnitSize.getUnit().equals("fluidounces"));

				
	}
	
	@Test
	public void changeAmountOtherTest() {
		_testUnitSize = new UnitSize();
		
		//change unit
		try {
			_testUnitSize.setUnit("ounces");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		//integer
		try {
			_testUnitSize.setAmount("44");
		} catch (InvalidAmountException e) {
			
		}
		assertTrue(_testUnitSize.getAmount() == 44);

		//float
		try {
			_testUnitSize.setAmount("42.34");
		} catch (InvalidAmountException e) {
			
		}
		assertTrue(_testUnitSize.getAmount() == (float) 42.34);
		
		//float
		try {
			_testUnitSize.setAmount("Song");
		} catch (InvalidAmountException e) {
			
		}
		assertTrue(_testUnitSize.getAmount() == (float) 42.34);
		
		//Persistence between changes
		try {
			_testUnitSize.setUnit("grams");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("grams"));
		assertTrue(_testUnitSize.getAmount() == (float) 42.34);
		
		//changes to 1 if unit becomes count
		try {
			_testUnitSize.setUnit("count");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getAmount() == (float) 1);
		
	}
	
	@Test
	public void isValidUnitAndAmountTest() {

		_testUnitSize = new UnitSize();
		
		//change unit
		try {
			_testUnitSize.setUnit("ounces");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		//check if a string is valid without attempting to make a change
		assertFalse(_testUnitSize.isValidAmount("River"));
		assertTrue(_testUnitSize.isValidAmount("43.3"));
		assertTrue(_testUnitSize.isValidAmount("42"));
		assertFalse(_testUnitSize.isValidAmount("ounces"));
		
		//if count, cant change at all
		try {
			_testUnitSize.setUnit("count");
		} catch (InvalidUnitException e) {
			
		}
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
