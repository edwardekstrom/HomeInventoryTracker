/**
 * 
 */
//package data_structures_tests;

import static org.junit.Assert.*;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;

import java.util.Date;

import model.Barcode;
import model.Product;
import model.UnitSize;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		_testUnitSize = new UnitSize("1", "count");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void makeUnitSizeTest() {
		try {
			_testUnitSize = new UnitSize("1", "count");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(_testUnitSize.getUnit().equals("count"));
		assertTrue(_testUnitSize.getAmount() == 1);
	}
	
	@Test
	public void changeAmountCountTest() {
		try {
			_testUnitSize = new UnitSize("1", "count");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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

	}
	
	@Test
	public void changeUnitTest() {
		try {
			_testUnitSize = new UnitSize("1", "count");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("fail");
			e1.printStackTrace();
		}
		
		//test all viable units
		try {
			_testUnitSize.setUnit("ounces");
		} catch (InvalidUnitException e) {
			System.out.println(_testUnitSize.getUnit());
			System.out.println(_testUnitSize.getAmount());
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
		try {
			_testUnitSize = new UnitSize("1", "count");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
			System.out.println("failed");
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
		
	}
	
	@Test
	public void isValidUnitAndAmountTest() {

		try {
			_testUnitSize = new UnitSize("1", "count");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//change unit
		try {
			_testUnitSize.setUnit("ounces");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("ounces"));
		
		//check if a string is valid without attempting to make a change
		assertFalse(UnitSize.isValid("River", "ounces"));
		assertTrue(UnitSize.isValid("43.3", "ounces"));
		assertTrue(UnitSize.isValid("42", "ounces"));
		assertFalse(UnitSize.isValid("ounces", "ounces"));
		
		//if count, cant change at all
		try {
			_testUnitSize.setUnit("count");
		} catch (InvalidUnitException e) {
			
		}
		assertTrue(_testUnitSize.getUnit().equals("count"));
		assertTrue(UnitSize.isValid("13", "count"));
		assertFalse(UnitSize.isValid("Simon", "count"));
		assertFalse(UnitSize.isValid("3.4", "count"));
		assertFalse(UnitSize.isValid("ounces", "count"));
		
		//check if a unit is valid w/o making a change
		assertTrue(UnitSize.isValid("1", "count"));
		assertFalse(UnitSize.isValid("1", "55.33"));
		assertFalse(UnitSize.isValid("1", "55"));
		assertFalse(UnitSize.isValid("1", "Tam"));
			
	}

}
