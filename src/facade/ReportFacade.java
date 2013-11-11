package facade;

import builder.HTMLBuilder;
import builder.PDFBuilder;
import builder.ReportBuilder;
import builder.TestBuilder;
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
		ReportBuilder builder;
		if(type == 1){
			builder = new PDFBuilder();
		}else if(type == 2){
			builder = new HTMLBuilder();
		}else{
			builder = new TestBuilder();
		}
			
		//rf.generateReport(rf, builder);
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
