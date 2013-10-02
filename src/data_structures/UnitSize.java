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
	public UnitSize() {
		_amount = 1;
		_unit = "count";
	}
	
	
	/**
	 * @precondition none
	 * Checks to see if the Unit and Amount are currently valid
	 * @postcondition determines if _amount and _unit are valid
	 * @return true if valid
	 */
	public boolean isCurrentlyValid(){
		if(isValidAmount(_amount+"") && isValidUnit(_unit)){
			return true;
		}else{
			return false;
		}
	}
	
	/**@precondition none
	 * @postcondition checks if the string is a valid amount
	 * @returns true if passed String is a valid float and unit is not count
	 * Checks to see if the Amount is valid
	 * @return true if valid
	 */
	public boolean isValidAmount(String amount){
		if(_unit.equals("count")){
			return false;
		}
		try{
			Float.parseFloat(amount);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}		
	}
	
	/**@precondition none
	 * @postcondition checks to see if the passed value is a vaild unit
	 * Checks to see if the Unit is valid
	 * @return true if valid
	 */
	public boolean isValidUnit(String unit){
		if( (unit.compareTo("pounds") == 0) || (unit.compareTo("ounces") == 0) || 
		    (unit.compareTo("grams") == 0) || (unit.compareTo("kilograms") == 0) ||
		    (unit.compareTo("gallons") == 0) || (unit.compareTo("quarts") == 0) ||
		    (unit.compareTo("pints") == 0) || (unit.compareTo("fluid ounces") == 0) ||
		    (unit.compareTo("liters") == 0) || (unit.compareTo("count") == 0))
		{	
			return true;
		}else{
			return false;
		}
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
		if(isValidAmount(amount)){
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
		if(isValidUnit(unit)){
			_unit = unit;
			if(unit.equals("count")){
				_amount = 1;
			}
		}else{
			throw new InvalidUnitException();
		}
		
	}
}
