package reports;

import hit_exceptions.InvalidRowException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.HomeInventory;
import model.Item;
import singletons.Configuration;
import visitor.ExpiredItemsVisitor;
import visitor.ReportVisitor;
import builder.ReportBuilder;

public class ExpiredItemsReport implements ReportInterface {
	
	ExpiredItemsVisitor _visitor = new ExpiredItemsVisitor();

	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	@Override
	public void generateReport(ReportBuilder build) {
		HomeInventory hi = Configuration.getHIT();
		hi.accept(_visitor);
		build.buildReport(this);
	}

	@Override
	public String getHeader() {
		return "Expired Items Report";
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		ArrayList<ReportTable> arrayListOfTables = new ArrayList<ReportTable>();
		
		ReportTable reportTable = new ReportTable(getNumColumns(), "Expired Items");
		String[] headerRow = {"Description", "Storage Unit",
				"Product Group", "Entry Date", "Expire Date", "Item Barcode"};
		reportTable.setHeaderRow(headerRow);
		
		for(Item i : _visitor.getExpiredList()){
//			System.out.println("adding row!");
			String description = i.getProduct().getDescription();
			String storageUnit = i.getContainer().getStorageUnit().getName();
			String productGroup = i.getContainer().getName();
			
			if(i.getContainer() == i.getContainer().getStorageUnit()){
				productGroup = "";
			}
			String entryDate = i.getEntryDate().getDateAsString(new SimpleDateFormat("MM/dd/yyyy"));
			String expireDate = i.getExpirationDate().getDateAsString(new SimpleDateFormat("MM/dd/yyyy"));
			String itemBarcode = i.getBarcode().getBarcode();
			String[] rowArray = {description,storageUnit,productGroup,entryDate,expireDate,itemBarcode};
			TableRow newRow = new TableRow(rowArray);
			
			try{
				reportTable.addRow(newRow);
			}catch(InvalidRowException ire){
				System.out.println("This was for you Chris");
			}
		}
		arrayListOfTables.add(reportTable);
		
		return arrayListOfTables;
	}

	@Override
	public int getNumTables() {
		return 1;
	}

	@Override
	public int getNumColumns() {
		return 6;
	}

	@Override
	public ArrayList<ReportNotice> getNotices() {
		return null;
	}

}
