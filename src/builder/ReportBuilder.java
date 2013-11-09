package builder;

import reports.ReportInterface;

public interface ReportBuilder {

	/**uses the data that has been gathered to generate a report in the format that the concrete builder uses
	 * @precondition data from the model gathered by the visitors
	 * @postcondition the data given in the desired format
	 */ 
	public void buildReport(ReportInterface report);
	
}
