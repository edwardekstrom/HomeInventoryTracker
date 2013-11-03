package reports;

import builder.ReportBuilder;
import visitor.ReportVisitor;

public interface ReportInterface {
	
	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	public void generateReport(ReportVisitor visit, ReportBuilder build);

}
