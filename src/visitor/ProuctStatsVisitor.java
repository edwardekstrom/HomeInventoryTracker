package visitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import com.sun.tools.javac.util.Pair;

import model.Date;
import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

public class ProuctStatsVisitor implements ReportVisitor {
	int _months = -1;
	List<Product> _products = new ArrayList<Product>();
	List<Pair<Item,Integer>> _items = new ArrayList<Pair<Item, Integer>>();
	
	public ProuctStatsVisitor(int months){
		_months = months;
	}
	
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
		GregorianCalendar threeMonthsAgo = new GregorianCalendar();
		threeMonthsAgo.add(Calendar.MONTH, -_months);
		if(i.getEntryDate().isAfter(new Date(new GregorianCalendar())) 
				&& i.getEntryDate().isBefore(new Date(new GregorianCalendar()))){
			
			Long daysAround = -1l;
			int daysAroundInt = -1;
			if(i.getExitTime()!=null){
				daysAround = i.getExitTime().getDate().getTimeInMillis() - i.getEntryDate().getDate().getTimeInMillis();
				//daysAroundInt = daysAround
			}
			//Pair<Item, Integer> pair = new Pair<Item, Integer>(i, arg1);
			
		}
	}

	@Override
	public void visit(Product p) {
		_products.add(p);
	}

	@Override
	public void visit(ProductContainer pc) {
		
	}

	@Override
	public void visit(HomeInventory hi) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Pair<Item,Integer>> getItems(){
		return _items;
	}
	
	public List<Product> getProducts(){
		Collections.sort(_products);
		return _products;
	}

}
