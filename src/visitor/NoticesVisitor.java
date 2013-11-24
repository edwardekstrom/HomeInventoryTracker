package visitor;

import java.util.List;

import model.*;
import reports.*;

import java.util.ArrayList;

public class NoticesVisitor implements ReportVisitor {
	private List<Notice> _notices = new ArrayList<Notice>();

//	/**gathers the data from the model for the implemented report type
//	 * 
//	 * @return list of data gathered for building the report
//	 * @precondition using the correct report type
//	 * @postcondition gathers the needed data from the model for that report
//	 */
//	@Override
//	public List gatherReportData() {
//		
//		return null;
//	}

	@Override
	public void visit(Item i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Product p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ProductContainer pc) {
		if (pc instanceof StorageUnit)
			return;
		else{
			Notice pcNotice = new Notice((ProductGroup)pc);
			//List<Item> items = pc.getAllItems();
			List<Product> products = pc.getProducts();
			UnitSize type = ((ProductGroup)pc).getThreeMonthSup();
			String mytype = getTypeGroup(type.getUnit());
			for(Product p : products){
				String prodType = getTypeGroup(p);
				if(!prodType.equals(mytype)){
					if(!_notices.contains(pcNotice)){
						_notices.add(pcNotice);
					}
					pcNotice.addProduct(p);
				}
					
			}
		}
		
	}

	private String getTypeGroup(String type){
		if(type.equals("gallons") || type.equals("quarts")
			|| type.equals("liters") || type.equals("pints") || 
			type.equals("fluid ounces"))
			return "liquid";
		else if(type.equals("count"))
			return "count";
		else
			return "weight";
	}

	private String getTypeGroup(Product p){
		//Product p = i.getProduct();
		String unit = p.getSizeUnit();
		return getTypeGroup(unit);
	}

	@Override
	public void visit(HomeInventory hi) {
		// TODO Auto-generated method stub
		
	}

	private class Notice{
		public ProductGroup _pc;
		public List<Product> _products;

		public Notice(ProductGroup pc){
			_products = new ArrayList<Product>();
			_pc = pc;
		}
		
		public void addProduct(Product p){
			_products.add(p);
		}
		
		public List<Product> getProducts(){
			return _products;
		}
	}


	public ArrayList<ReportNotice> getNoticeNotices(){
		ArrayList<ReportNotice> ret = new ArrayList<ReportNotice>();
		ReportNotice notice = new ReportNotice("3 Month Supply Warnings");
		
		for(Notice n : _notices){
			//NoticeData nd = new NoticeData("Is Inconsitent: "+ n._pc.getName()
			//+ " " + n._item.getBarcode().getBarcode());
			NoticeData nd = new NoticeData();
			
			//Product group <storageunit/ProdGroup>::<prodgroup> has a 3-month supply (<supply>)
			//that is inconsistent with the following products:
			nd.setDescription("Product group " + n._pc.getStorageUnit().getName() +"::" + 
			        n._pc.getName() + " has a 3-month supply ("+
					n._pc.getThreeMonthSup().getUnit() + " " +
					n._pc.getThreeMonthSup().getAmount() +
					") that is inconsistent with the following products:");
			
			List<Product> offList = n._products;
			for(Product p : offList){
				//-<productgroup>::<itemname/productgroup> (size: <num> <unit>)
				nd.addOffender("-" + n._pc.getName() + "::" + p.getDescription() +
						" (size: " + p.getSizeAmount() + " " + p.getSizeUnit() + ")");
			}

			notice.addNotice(nd);
			
		}
		ret.add(notice);
		return ret;
	}
}
