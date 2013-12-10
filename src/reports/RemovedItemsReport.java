package reports;

import gui.common.SizeUnits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import model.*;
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
		
		return "Items Removed Since "+ _visitor.getDateAsString(sdf);
	}

	@Override
	public ArrayList<ReportTable> getTableData() {

		SortedMap<Item,Integer> items = _visitor.getSortedItemMap();
		ReportTable table = new ReportTable(5, "");
		table.setHeaderRow(new String[] 
			{"Description","Size","Product Barcode","Removed","Current Supply"} );



		for (Item i: items.keySet()){

			Product p = i.getProduct();
			String desc = p.getDescription();
			String size;
			if(p.getSizeUnit().equals("count")){			
				size = (int)p.getSizeAmount() + " " + p.getSizeUnit();
			}else{			
				size = p.getSizeAmount() + " " + p.getSizeUnit();
			}
			String p_bcode = p.getBarcode().getBarcode();
			String removed = items.get(i) + "";
			String supply =  (int)p.getCurrentSupply() + "";

			TableRow tr = new TableRow(5);
			tr.setData(new String[] 
				{desc,size,p_bcode,removed,supply});
			try{
				table.addRow(tr);
			}catch(Exception e){System.out.println("Error CAUGHT:" + e.getMessage());}
		}

		ArrayList<ReportTable> ret = new ArrayList<ReportTable>();
		ret.add(table);
		return ret;
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
