package visitor;

import java.util.List;

import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;
import model.StorageUnit;
import model.*;
import reports.*;

import java.util.ArrayList;

public class NoticesVisitor implements ReportVisitor {
	private List<Notice> _notices = new ArrayList<Notice>();

	/**gathers the data from the model for the implemented report type
	 * 
	 * @return list of data gathered for building the report
	 * @precondition using the correct report type
	 * @postcondition gathers the needed data from the model for that report
	 */
	@Override
	public List gatherReportData() {
		
		return null;
	}

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
			List<Item> items = pc.getAllItems();
			UnitSize type = ((ProductGroup)pc).getThreeMonthSup();
			String mytype = getTypeGroup(type.getUnit());
			for(Item i : items){
				String itemType = getTypeGroup(i);
				if(!itemType.equals(mytype))
					_notices.add(new Notice(pc,i));
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

	private String getTypeGroup(Item i){
		Product p = i.getProduct();
		String unit = p.getSizeUnit();
		return getTypeGroup(unit);
	}

	@Override
	public void visit(HomeInventory hi) {
		// TODO Auto-generated method stub
		
	}

	private class Notice{
		public ProductContainer _pc;
		public Item _item;

		public Notice(ProductContainer pc,Item item){
			_item = item;
			_pc = pc;
		}
	}


	public ArrayList<ReportNotice> getNoticeNotices(){
		ArrayList<ReportNotice> ret = new ArrayList<ReportNotice>();
		ReportNotice notice = new ReportNotice("3 Month Supply Warnings");
		
		for(Notice n : _notices){
			NoticeData nd = new NoticeData("Is Inconsitent: "+ n._pc.getName() + " " + n._item.getBarcode().getBarcode());
			notice.addNotice(nd);
		}
		ret.add(new ReportNotice("3 Month Supply Warnings"));
		return ret;
	}
}
