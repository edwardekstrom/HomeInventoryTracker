package visitor;

import java.util.List;

public interface ReportVisitor {

	/**gathers the data from the model for the implemented report type
	 * 
	 * @return list of data gathered for building the report
	 * @precondition using the correct report type
	 * @postcondition gathers the needed data from the model for that report
	 */
	public List gatherReportData();
	
}
