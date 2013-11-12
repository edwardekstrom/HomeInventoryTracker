package reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Date;
import model.HomeInventory;
import singletons.Configuration;
import singletons.ItemsManager;
import visitor.ExpiredItemsVisitor;
import visitor.RemovedItemsVisitor;
import visitor.ReportVisitor;
import builder.ReportBuilder;

public class RemovedItemsReport implements ReportInterface {
	
	RemovedItemsVisitor _visitor;
	
	public RemovedItemsReport(Date date){
		_visitor = new RemovedItemsVisitor(date);
	}
	
	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	@Override
	public void generateReport(ReportBuilder build) {
		ItemsManager manager = ItemsManager.getInstance();
		manager.acceptRemovedItemsVisitor(_visitor);
		
		build.buildReport(this);		
	}

	@Override
	public String getHeader() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
		
		return "Items Removed Since"+ sdf.format(_visitor.getDateAsString(sdf));
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumTables() {
		return 1;
	}

	@Override
	public int getNumColumns() {
		return 5;
	}

	@Override
	public ArrayList<ReportNotice> getNotices() {
		return null;
	}

}
