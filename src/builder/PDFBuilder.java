/**
 * 
 */
package builder;

import reports.ReportInterface;

/**
 * @author Capchu
 *
 */
public class PDFBuilder implements ReportBuilder {


	/**uses the data that has been gathered to generate a report in the format that the concrete builder uses
	 * @precondition data from the model gathered by the visitors
	 * @postcondition the data given in the desired format
	 */ 
	@Override
	public void buildReport(ReportInterface report) {
		// TODO Auto-generated method stub
		
	}

}
