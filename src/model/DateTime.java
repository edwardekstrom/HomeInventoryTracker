/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Capchu
 *
 */
public class DateTime implements Serializable{

	private GregorianCalendar _exitDate;

	/**@precondition none
	 * @postcondition makes a new DateTime using the current GregorianCalendar time
	 * 
	 */
	DateTime(){
		//make a new date with the current time
		_exitDate = new GregorianCalendar();
	}	
	
	/**@precondition none
	 * @postconditions gives the date
	 * @return the _date
	 */
	public GregorianCalendar getDate() {
		return _exitDate;
	}
}
