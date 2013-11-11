package reports;

import java.util.ArrayList;

import visitor.ProuctStatsVisitor;
import visitor.ReportVisitor;
import builder.ReportBuilder;

public class ProductStatsReport implements ReportInterface {
	
	ReportVisitor _visitor = new ProuctStatsVisitor();

	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	@Override
	public void generateReport(ReportBuilder build) {
		
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumTables() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ReportNotice> getNotices() {
		// TODO Auto-generated method stub
		return null;
	}

}
