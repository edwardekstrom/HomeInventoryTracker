package data_structures;

import java.io.Serializable;

/**
 * @author Capchu
 * This Class is used to store the Unit and Size Tuple used for food amounts.
 */
public class UnitSize implements Serializable{
	
	private float _amount;
	private String _unit;
	
	/**
	 * Creates and instance not setting any values.
	 */
	public UnitSize() {
		_amount = 1;
		_unit = "count";
	}
	
//	/**
//	 * Sets the unit and size
//	 * @return true if the set was successful
//	 */
//	public boolean setUnitSize(String amount, String unit){
//		if(isValidUnitAmount(amount, unit)){
//			_amount = Float.parseFloat(amount);
//			_unit = unit;
//			return true;
//		}else{
//			return false;
//		}
//	}
	
//	/**
//	 * Checks to see if the Unit and Amount are valid
//	 * @return true if valid
//	 */
//	private boolean isValidUnitAmount(String amount, String unit){
//		if(isValidAmount(amount) && isValidUnit(unit)){
//			return true;
//		}else{
//			return false;
//		}
//	}
	
	/**
	 * Checks to see if the Unit and Amount are valid
	 * @return true if valid
	 */
	public boolean isCurrentlyValid(){
		if(isValidAmount(_amount+"") && isValidUnit(_unit)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
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
	
	/**
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
	
	/**
	 * @return the _amount
	 */
	public float getAmount() {
		return _amount;
	}

	/**
	 * @param amount the _amount to set
	 */
	public boolean setAmount(String amount) {
		if(isValidAmount(amount)){
			_amount = Float.parseFloat(amount);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return the _unit
	 */
	public String getUnit() {
		return _unit;
	}

	/**
	 * @param unit the _unit to set
	 */
	public boolean setUnit(String unit) {
		if(isValidUnit(unit)){
			_unit = unit;
			if(unit.equals("count")){
				_amount = 1;
			}
			return true;
		}else{
			return false;
		}
		
	}
}
