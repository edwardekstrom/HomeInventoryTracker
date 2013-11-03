package reports;

import visitor.ReportVisitor;
import builder.ReportBuilder;

public class NoticesReport implements ReportInterface {

	
	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	@Override
	public void generateReport(ReportVisitor visit, ReportBuilder build) {
		// TODO Auto-generated method stub

	}

}
