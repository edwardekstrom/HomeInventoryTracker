package model;

import java.io.Serializable;

import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;
import gui.common.SizeUnits;

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
	public UnitSize(String amount, String unit ) 
		throws InvalidAmountException, InvalidUnitException {
		if(isValid(amount, unit)){
			_unit = unit;
			_amount = Float.parseFloat(amount);
		}else{
			throw new InvalidUnitException();
		}
		
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
	public static boolean isValid(String amount, String unit){

		boolean found = false;
		for( SizeUnits v : SizeUnits.values()){
			if(v.toString().equals(unit)){
				found = true;
				break;
			}
		}

		if(!found)
			return false;

		try{
			//System.out.println(amount);
			Float amt = Float.parseFloat(amount);
			if( unit.equals("count") && amt.intValue() != amt.floatValue()){
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
		if(isValid(amount, _unit)){
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
		//System.out.println(unit);
		//System.out.println("" + _amount);
		if(isValid("" + _amount, unit)){
			//System.out.println("changed");
			_unit = unit;
		}else{
			throw new InvalidUnitException();
		}
		
	}
}
