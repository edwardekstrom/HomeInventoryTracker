package reports;

import java.util.ArrayList;

import visitor.ReportVisitor;
import builder.ReportBuilder;
import visitor.*;
import singletons.*;
import model.*;

public class NoticesReport implements ReportInterface {

	NoticesVisitor _visitor = new NoticesVisitor();
	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	@Override
	public void generateReport(ReportBuilder build) {
		// TODO Auto-generated method stub
		HomeInventory hi = Configuration.getHIT();
		hi.accept(_visitor);
		build.buildReport(this);	
		
	}

	@Override
	public String getHeader() {
		String header = "Notices";
		return header;
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
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
		return _visitor.getNoticeNotices();
		
	}

}
