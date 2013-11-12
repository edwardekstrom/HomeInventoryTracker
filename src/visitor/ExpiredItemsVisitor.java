package visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Date;
import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

public class ExpiredItemsVisitor implements ReportVisitor {
	
	List<Item> _expiredItems = new ArrayList<Item>();

	/**gathers the data from the model for the implemented report type
	 * 
	 * @return list of data gathered for building the report
	 * @precondition using the correct report type
	 * @postcondition gathers the needed data from the model for that report
	 */
	@Override
	public List gatherReportData() {
		// remove this from the interface.
		return null;
	}

	@Override
	public void visit(Item i) {
		
		if(i.getExpirationDate().isAfter(new Date())){
			_expiredItems.add(i);
		}
	}
	@Override
	public void visit(Product p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ProductContainer pc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(HomeInventory hi) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Item> getExpiredList(){
		Collections.sort(_expiredItems);
		return _expiredItems;
	}

}
