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
public class Date implements Serializable{

	private GregorianCalendar _date;

	public Date(){
		_date = new GregorianCalendar();
	}
	
	public Date(GregorianCalendar date){
		_date = date;
	}
	
	public Date(int year, int month, int day){
		_date = new GregorianCalendar(year, month, day);
	}
	
	public Date generateExperationDate(int shelfLife){
		Date expDate = new Date(_date);
		expDate.getDate().add(GregorianCalendar.MONTH, shelfLife);
		return expDate;
	}
	
	public GregorianCalendar getDate() {
		return _date;
	}
}
