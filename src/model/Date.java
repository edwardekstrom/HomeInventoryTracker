/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * @author Capchu
 *
 */
public class Date implements Serializable{

	private GregorianCalendar _date;

	/**@precondition none
	 * @postcondition makes a new date with the current time
	 * 
	 */
	public Date(){
		_date = new GregorianCalendar();
	}
	
	/**@precondition none
	 * @postcondition makes a date with the assigned GregorianCalander date
	 * 
	 * @param date
	 */
	public Date(GregorianCalendar date){
		_date = (GregorianCalendar) date.clone();
	}
	
	/**@precondition none
	 * @postcondition makes a new date from the ints passed in for day month and year
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public Date(int year, int month, int day){
		_date = new GregorianCalendar(year, month, day);
	}
	
	public Date(java.util.Date entryDate) {
		_date = new GregorianCalendar();
		_date.setTime(entryDate);
		// TODO Auto-generated constructor stub
	}

	/**@precondition passed an int for the shelfLife if the product
	 * @postcondition generates an expiration date using the entry 
	 * date it's called on and the shelf life of the product
	 * 
	 * @param shelfLife
	 * @return the expiration date
	 */
	public Date generateExperationDate(int shelfLife){
		Date expDate = new Date(_date);
		expDate.getDate().add(GregorianCalendar.MONTH, shelfLife);
		return expDate;
	}
	
	/**@precondition none
	 * @postcondition gives the date
	 * 
	 * @return the GregorianCalendar date
	 */
	public GregorianCalendar getDate() {
		return _date;
	}

	public java.util.Date getUtilDate(){
		return _date.getTime();
	}

	public String getDateAsString(SimpleDateFormat sdf){
		java.util.Date dt = _date.getTime();
		return sdf.format(dt);
	}
}
