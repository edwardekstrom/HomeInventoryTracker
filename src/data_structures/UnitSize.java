package data_structures;

import java.io.Serializable;

import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;

/**
 * @author Capchu
 * This Class is used to store the Unit and Size Tuple used for food amounts.
 */

public class UnitSize implements Serializable{
	
	private float _amount;
	private String _unit;
	
	/**@precondition none
	 * Creates and instance not setting any values.
	 * @postcondition creates a new unit size with _amount== 1 and _unit == count
	 */
	public UnitSize(String amount,String unit ) 
		throws InvalidAmountException, InvalidUnitException {
		setAmount(amount);
		setUnit(unit);
	}
	
	/**
	 * @precondition none
	 * Checks to see if the Unit and Amount are currently valid
	 * @postcondition determines if _amount and _unit are valid
	 * @return true if valid
	 */
	public boolean isCurrentlyValid(){
		if(isValid("" + _amount,_unit))
			return true;
		else
			return false;
	}
	
	/**
	 * Determine whether an amount is valid
	 * @param String unit (the unit of measurement)
	 * @param String amount (the amount of that measurement)
	 * REFACTOR ME!
	 */
	public static boolean isValid(String unit, String amount){
		if(!(unit.equals("pounds") || (unit.compareTo("ounces") == 0) || 
		    (unit.compareTo("grams") == 0) || (unit.compareTo("kilograms") == 0) ||
		    (unit.compareTo("gallons") == 0) || (unit.compareTo("quarts") == 0) ||
		    (unit.compareTo("pints") == 0) || (unit.compareTo("fluid ounces") == 0) ||
		    (unit.compareTo("liters") == 0) || (unit.compareTo("count") == 0))) {
			return false;
		}
		try{
			Float amt = Float.parseFloat(amount);
			if( !(unit.compareTo("count") == 0 && amt.intValue() == amt.floatValue())){
				return false;
			}

		}catch(NumberFormatException nfe){
			return false;
		}		

		return true;
	}

	/** @precondition there is an amount
	 * @postcondition returns the amount
	 * @return the _amount
	 */
	public float getAmount() {
		return _amount;
	}

	/**@precondition passed a String that is a valid amount
	 * @postcondition sets _amount to the given amount String
	 * @param amount the _amount to set
	 */
	public void setAmount(String amount) throws InvalidAmountException{
		if(isValid(_unit, amount)){
			_amount = Float.parseFloat(amount);
		}else{
			throw new InvalidAmountException();
		}
	}

	/**@precondition none
	 * @postcondition returns the _unit
	 * @return the _unit
	 */
	public String getUnit() {
		return _unit;
	}

	/**@precondition given a string that is a valid unit
	 * @postcondition sets _unit to the given unit and changes amount to 1 if unit is count
	 * @param unit the _unit to set
	 */
	public void setUnit(String unit) throws InvalidUnitException{
		if(isValid(unit,"" + _amount)){
			_unit = unit;
		}else{
			throw new InvalidUnitException();
		}
		
	}
}
