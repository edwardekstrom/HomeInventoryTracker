package reports;

import hit_exceptions.InvalidRowException;

import java.util.ArrayList;
import java.util.HashMap;

import model.HomeInventory;
import model.Product;
import singletons.Configuration;
import singletons.ItemsManager;
import visitor.NMonthSupplyVisitor;
import visitor.ProductStat;
import visitor.ProuctStatsVisitor;
import visitor.ReportVisitor;
import builder.ReportBuilder;

public class ProductStatsReport implements ReportInterface {
	
	ProuctStatsVisitor _visitor = new ProuctStatsVisitor(-1);
	int _months = -1;

	
	public ProductStatsReport(int months){
		_months = months;
		_visitor =  new ProuctStatsVisitor(_months);
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
		HomeInventory hi = Configuration.getHIT();
		hi.accept(_visitor);
		ItemsManager.getInstance().acceptRemovedItemsVisitor(_visitor);
		build.buildReport(this);
	}

	@Override
	public String getHeader() {
		return "Product Report (" + _months +" Months)";
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		ArrayList<ReportTable> list = new ArrayList<ReportTable>();
		ReportTable productReport = new ReportTable(getNumColumns(), "");
		String[] productsName = {"Description", "Barcode", "Size", _months 
				+ "-Month Supply","Supply: Cur/Avg","Supply: Min/Max", "Supply: Used/Added"
				, "Shelf Life", "Used Age: Avg/Max", "Cur Age: Avg/Max"};
		productReport.setHeaderRow(productsName);
		HashMap<Product, ProductStat> statMap = _visitor.getProductStatsMap();
		for(Product p : statMap.keySet()){
			ProductStat ps = statMap.get(p);
			
			String description = p.getDescription();
			String barcode = p.getBarcode().getBarcode();
			String size = p.getSizeAmount() + " " + p.getSizeUnit();
			float supplyNeeded = (float)p.getThreeMonthSupply()/3.0f * _months;
			
			String supplyCurAverage = p.getCurrentSupply() + "/" + ps.get_avgSupply();
			String supplyMinMax = ps.getMinSupply() + "/" + ps.getMaxSupply();
			String supplyUsedAdded = ps.get_supplyUsed() + "/" + ps.get_supplyAdded();
			String shelfLife = p.getShelfLife() +"";


			String usedAgeAvgMax = ps.get_avgAgeUsed() + " days/ " + ps.get_usedAgeMax() 
																				+ " days";
			String curAgeAvgMax = ps.get_avgAgeCurrent() + " days/ " 
									+ ps.get_currentAgeMax() + " days";

			
			
			String[] rowArray = {description,barcode,size,supplyNeeded + "", supplyCurAverage,
					supplyMinMax,supplyUsedAdded,shelfLife,usedAgeAvgMax,curAgeAvgMax};
			TableRow newRow = new TableRow(rowArray);
			try{
				productReport.addRow(newRow);
			}catch(InvalidRowException ire){
				System.out.println("This was for you Chris");
			}
		}
		
		list.add(productReport);
		return list;
	}
	
	private float averagePerDay(int months){
		return 0;
	}

	@Override
	public int getNumTables() {
		return 1;
	}

	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public ArrayList<ReportNotice> getNotices() {
		return null;
	}

}
