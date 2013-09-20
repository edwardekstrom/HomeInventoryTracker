package data_structures;

/**
 * @author Capchu
 * This Class is used to store the Unit and Size Tuple used for food amounts.
 */
public class UnitSize {
	
	private float _amount;
	private String _unit;
	
	/**
	 * Creates and instance not setting any values.
	 */
	public UnitSize() {
		
	}
	
	/**
	 * Sets the unit and size
	 * @return true if the set was successful
	 */
	public boolean setUnitSize(float amount, String unit){
		if(isValidUnitAmount(amount, unit)){
			_amount = amount;
			_unit = unit;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Checks to see if the Unit and Amount are valid
	 * @return true if valid
	 */
	private boolean isValidUnitAmount(float amount, String unit){
		return true;
	}
	
	/**
	 * Checks to see if the Amount is valid, only to be used when the unit is already set
	 * @return true if valid
	 */
	private boolean isValidAmount(float amount){
		return true;
	}
	
	/**
	 * Checks to see if the Unit is valid, only to be used when the amount is already set
	 * @return true if valid
	 */
	private boolean isValidUnit(String unit){
		return true;
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
	public boolean setAmount(float amount) {
		if(isValidAmount(amount)){
			_amount = amount;
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
			return true;
		}else{
			return false;
		}
		
	}
}
