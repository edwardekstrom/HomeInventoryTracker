package reports;

import java.util.ArrayList;

public class NoticeData {

	//FORMAT:
	//Product group <storageunit>::<prodgroup> has a <n>-month supply (<supply>) 
	//that is inconsistent with the following products:
	String _description;
	
	//FORMAT:
	//<productgroup>::<itemname> (size: <num> <unit>)
	ArrayList<String> _offenders;
	
	public NoticeData(){
		_description = "";
		_offenders = new ArrayList<String>();
	}
	
	public NoticeData(String description){
		_description = description;
		_offenders = new ArrayList<String>();
	}
	
	public NoticeData(String description, ArrayList<String> offenders){
		_description = description;
		_offenders = offenders;
	}
	/**
	 * @return the _description
	 */
	public String getDescription() {
		return _description;
	}
	/**
	 * @param _description the _description to set
	 */
	public void setDescription(String description) {
		_description = description;
	}
	/**
	 * @return the _offenders
	 */
	public ArrayList<String> getOffenders() {
		return _offenders;
	}
	/**
	 * @param _offenders the _offenders to set
	 */
	public void setOffenders(ArrayList<String> offenders) {
		_offenders = offenders;
	}
	
	public void addOffender(String offender){
		_offenders.add(offender);
	}
	
}
