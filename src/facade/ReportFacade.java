package facade;

import reports.ReportInterface;
import reports.ThisIsATestReport;
import visitor.ReportVisitor;

public class ReportFacade {
	
	/**Generates the report type in the passed builder format
	 * 
	 * @param the enumerated type
	 * @precondition passed a visitor and a builder
	 * @postcondition creates the report desired
	 */
	public void generateExpiredItemsReport(int type){
		
	
	}
	
	/**Generates the report type in the passed builder format
	 * 
	 * @param the enumerated type
	 * @precondition passed a visitor and a builder
	 * @postcondition creates the report desired
	 */
	public void generateRemovedItemsReport(int type){
		
	
	}
	
	/**Generates the report type in the passed builder format
	 * 
	 * @param the enumerated type
	 * @precondition passed a visitor and a builder
	 * @postcondition creates the report desired
	 */
	public void generateNoticesReport(int type){
		
		ReportInterface rf = new ThisIsATestReport();
		//TODO fix this.
		rf.generateReport(null, null);
	}
	
	/**Generates the report type in the passed builder format
	 * 
	 * @param the number of months to be looked at
	 * @param the enumerated type
	 * @precondition passed a visitor and a builder
	 * @postcondition creates the report desired
	 */
	public void generateNMonthSupplyReport(int type, int months){
		
	
	}
	
	/**Generates the report type in the passed builder format
	 * 
	 * @param the enumerated type
	 * @precondition passed a visitor and a builder
	 * @postcondition creates the report desired
	 */
	public void generateProdutStatsReport(int type){
		
	
	}

}
