package visitor;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import model.Date;
import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

public class RemovedItemsVisitor implements ReportVisitor {

	private Date _date;
	private List<Item> _removedItems;
	
	public RemovedItemsVisitor(Date date) {
		_date = date;
	}

	/**gathers the data from the model for the implemented report type
	 * 
	 * @return list of data gathered for building the report
	 * @precondition using the correct report type
	 * @postcondition gathers the needed data from the model for that report
	 */
	@Override
	public List gatherReportData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(Item i) {
		if(i.getExitTime().getDate().compareTo(_date.getDate()) > 0){
			_removedItems.add(i);
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

	public String getDateAsString(SimpleDateFormat sdf) {
		return _date.getDateAsString(sdf);
	}
	
	public List<Item> getSortedItems(){
		
		Collections.sort(_removedItems);
		return _removedItems;
	}

}
