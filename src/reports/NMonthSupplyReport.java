package reports;

import hit_exceptions.InvalidRowException;

import java.util.ArrayList;

import model.HomeInventory;
import model.Product;
import model.ProductGroup;
import singletons.Configuration;
import visitor.ExpiredItemsVisitor;
import visitor.NMonthSupplyVisitor;
import visitor.ReportVisitor;
import builder.ReportBuilder;

public class NMonthSupplyReport implements ReportInterface{
	int _n = -1;
	NMonthSupplyVisitor _visitor;

	public NMonthSupplyReport(int n){
		_n = n;
		_visitor =  new NMonthSupplyVisitor(_n);
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
		build.buildReport(this);		
	}

	@Override
	public String getHeader() {
		return _n + "-Month Supply Report";
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		ArrayList<ReportTable> toReturn = new ArrayList<ReportTable>();
		toReturn.add(generateNMonthProductsTable());
		toReturn.add(generateNMonthProductGroupsTable());
		return toReturn;
	}
	
	private ReportTable generateNMonthProductsTable(){
		ReportTable nMonthProductT = new ReportTable(getNumColumns(), "Products");
		String[] productsName = {"Description", "Barcode", _n + "-Month Supply", "Current Supply"};
		nMonthProductT.setHeaderRow(productsName);
		
		for(Product p : _visitor.getDeficientProducts()){
			String description = p.getDescription();
			String barcode = p.getBarcode().getBarcode();
			float supplyNeeded = (float)p.getThreeMonthSupply()/3.0f * _n;
			String nMonthSupply = supplyNeeded + " " + p.getSizeUnit();
			String currentSupply = p.getCurrentSupply() + "";
			
			String[] rowArray = {description,barcode,nMonthSupply,currentSupply};
			TableRow newRow = new TableRow(rowArray);
			try{
				nMonthProductT.addRow(newRow);
			}catch(InvalidRowException ire){
				System.out.println("This was for you Chris");
			}
		}
		
		return nMonthProductT;
	}
	
	private ReportTable generateNMonthProductGroupsTable(){
		ReportTable nMonthProductGroupT = new ReportTable(getNumColumns(), "Products");
		String[] productGroupsName = {"Product Group","Storage Unit",_n + "-Month Supply","Current Supply"};
		nMonthProductGroupT.setHeaderRow(productGroupsName);
		
		for(ProductGroup pg : _visitor.getDeficientProductGroups()){
			String productGroup = pg.getName();
			String storageUnit = pg.getStorageUnit().getName();
			float supplyNeeded = (float)pg.getThreeMonthSup().getAmount()/3.0f * _n;
			String nMonthSupply = supplyNeeded + " " + pg.getThreeMonthSup().getUnit();
			String currentSupply = pg.getCurrentSupply() + "";
			
			String[] rowArray = {productGroup,storageUnit,nMonthSupply,currentSupply};
			TableRow newRow = new TableRow(rowArray);
			try{
				nMonthProductGroupT.addRow(newRow);
			}catch(InvalidRowException ire){
				System.out.println("This was for you Chris");
			}
			
		}
		
		
		return nMonthProductGroupT;
	}

	@Override
	public int getNumTables() {
		return 2;
	}

	@Override
	public int getNumColumns() {
		return 4;
	}

	@Override
	public ArrayList<ReportNotice> getNotices() {
		return null;
	}

}
