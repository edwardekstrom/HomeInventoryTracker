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
	private String _measureType;
	public UnitSize(){
		_unit = "count";
		setAmount(0);
		setMeasureType();

	}
	/**@precondition none
	 * Creates and instance not setting any values.
	 * @postcondition creates a new unit size with _amount== 1 and _unit == count
	 */
	public UnitSize(String amount, String unit ) 
		throws InvalidAmountException, InvalidUnitException {
		if(isValid(amount, unit)){
			_unit = unit;
			_amount = Float.parseFloat(amount);
			setMeasureType();
		}else{
			throw new InvalidUnitException("invalid unitsize");
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
			if(amt <= 0){
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

	public void setAmount(float amount){
		_amount = amount;
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

			setMeasureType();
			
		}else{
			throw new InvalidUnitException();
		}
		
	}
	
	private void setMeasureType(){
		if(_unit.equals("count")){
			_measureType = "count";
		}else if(_unit.equals("pounds") 
				|| _unit.equals("ounces") 
				|| _unit.equals("kilograms") 
				|| _unit.equals("grams")){
			_measureType = "weight";
		}else{
			_measureType = "volume";
		}
	}
	
	public boolean isSameMeasurementType(UnitSize us){
		return this._measureType.equals(us._measureType);
	}
	
	private float convertVolumeToPints(UnitSize us){
		if(us.getUnit().equals("gallons")){
			return us.getAmount() * 8f;
		}else if(us.getUnit().equals("quarts")){
			return us.getAmount() * 2f;
		}else if(us.getUnit().equals("pints")){
			return us.getAmount();
		}else if(us.getUnit().equals("fluid ounces")){
			return us.getAmount() / 16f;
		}else if(us.getUnit().equals("liters")){
			return us.getAmount() * 2.11338f;
		}else{
			System.out.println("NOT A VOLUME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return -1;
		}
		
	}
	
	private float convertPintsToUnit(UnitSize us){
		if(us.getUnit().equals("gallons")){
			return us.getAmount() / 8f;
		}else if(us.getUnit().equals("quarts")){
			return us.getAmount() / 2f;
		}else if(us.getUnit().equals("pints")){
			return us.getAmount();
		}else if(us.getUnit().equals("fluid ounces")){
			return us.getAmount() * 16f;
		}else if(us.getUnit().equals("liters")){
			return us.getAmount() / 2.11338f;
		}else{
			System.out.println("NOT A VOLUME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return -1;
		}
	}
	private float convertWeightToGrams(UnitSize us){
		if(us.getUnit().equals("pounds")){
			return us.getAmount() * 453.592f;
		}else if(us.getUnit().equals("kilograms")){
			return us.getAmount() * 1000;
		}if(us.getUnit().equals("grams")){
			return us.getAmount();
		}if(us.getUnit().equals("ounces")){
			return us.getAmount() * 28.3495f;
		}else{
			System.out.println("NOT A WEIGHT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return -1;
		}
	}
	
	private float convertGramsToUnit(UnitSize us){
		if(us.getUnit().equals("pounds")){
			return us.getAmount() / 453.592f;
		}else if(us.getUnit().equals("kilograms")){
			return us.getAmount() / 1000;
		}if(us.getUnit().equals("grams")){
			return us.getAmount();
		}if(us.getUnit().equals("ounces")){
			return us.getAmount() / 28.3495f;
		}else{
			System.out.println("NOT A WEIGHT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return -1;
		}
	}
		
	
	public UnitSize addUnitSize(UnitSize us){
		UnitSize sum = new UnitSize();
		try {
			sum.setUnit(_unit);
		} catch (InvalidUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!this._measureType.equals(us.getMeasureType())){
//			should throw error
			System.out.println("Incompatable types!!!!");
			return null;
		}
		if(this._measureType.equals("volume")){
			sum.setAmount((convertVolumeToPints(this) + convertVolumeToPints(us)));
			sum.setAmount(convertPintsToUnit(sum));
		}else if(this._measureType.equals("weight")){
			sum.setAmount((convertWeightToGrams(this) + convertWeightToGrams(us)));
			sum.setAmount(convertGramsToUnit(sum));
		}else{
			sum.setAmount(this.getAmount() + us.getAmount());
		}
		
		return sum;
		
	}
	public String getMeasureType(){
		return _measureType;
	}
	
//	public int getEnumeratedUnit(){
//		
//	}
}
