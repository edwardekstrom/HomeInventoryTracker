/**
 * 
 */
package data_structures;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Capchu
 *
 */
public class DateTime implements Serializable{

	private GregorianCalendar _exitDate;

	DateTime(){
		//make a new date with the current time
		_exitDate = new GregorianCalendar();
	}	
	
	/**
	 * @return the _date
	 */
	public GregorianCalendar get_date() {
		return _exitDate;
	}
}
