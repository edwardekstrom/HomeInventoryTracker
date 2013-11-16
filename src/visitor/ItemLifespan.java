package visitor;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Item;

public class ItemLifespan {
	private GregorianCalendar birthday = new GregorianCalendar();
	private GregorianCalendar deathday = new GregorianCalendar();
	private int totalDaysAlive;
	private int totalDaysAliveOutside;

	public ItemLifespan(Item i, GregorianCalendar startDate, GregorianCalendar endDate){
		if(startDate.after(i.getEntryDate().getDate())){
			setBirthday(startDate);
		}else{
			setBirthday(i.getEntryDate().getDate());
		}
		if(i.getExitTime() == null){
			deathday = endDate;
		}else if(endDate.after(i.getExitTime().getDate())){
			deathday = i.getExitTime().getDate();
		}else{
			deathday = endDate;
		}
		
		long deadayMSec = deathday.getTime().getTime();
		long birthdayMSec = birthday.getTime().getTime();
		
		long entryMSec = i.getEntryDate().getDate().getTimeInMillis();
		
		setTotalDaysAlive((int)((deadayMSec - birthdayMSec)/(1000*60*60*24)));
		setTotalDaysAliveOutside((int)((deadayMSec - entryMSec)/(1000*60*60*24)));
		
	}

	/**
	 * @return the totalDaysAlive
	 */
	public int getTotalDaysAlive() {
		return totalDaysAlive;
	}

	/**
	 * @param totalDaysAlive the totalDaysAlive to set
	 */
	public void setTotalDaysAlive(int totalDaysAlive) {
		this.totalDaysAlive = totalDaysAlive;
	}

	/**
	 * @return the birthday
	 */
	public GregorianCalendar getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the totalDaysAliveOutside
	 */
	public int getTotalDaysAliveOutside() {
		return totalDaysAliveOutside;
	}

	/**
	 * @param totalDaysAliveOutside the totalDaysAliveOutside to set
	 */
	public void setTotalDaysAliveOutside(int totalDaysAliveOutside) {
		this.totalDaysAliveOutside = totalDaysAliveOutside;
	}

	public boolean isAlive(GregorianCalendar day) {
		GregorianCalendar actual = new GregorianCalendar();
		actual.setTime(day.getTime());
		actual.set(Calendar.HOUR_OF_DAY, 0);
		actual.set(Calendar.MINUTE, 0);
		actual.set(Calendar.SECOND, 0);
		actual.set(Calendar.MILLISECOND, 0);
		if(deathday.after(actual) && actual.after(birthday)){
			return true;
		}else{
			return false;
		}
	}
}
