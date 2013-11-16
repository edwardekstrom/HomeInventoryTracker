package visitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.tools.javac.util.Pair;

import model.Date;
import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

public class ProuctStatsVisitor implements ReportVisitor {
	int _months = -1;
	HashMap<Product, ProductStat> _productStats = new HashMap<Product, ProductStat>();
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
		if(i.getExitTime() != null){
			GregorianCalendar start = new GregorianCalendar();
			start.add(Calendar.MONTH, - _months);
			if(start.after(i.getExitTime().getDate())){
				return;
			}
		}
		ProductStat ps = new ProductStat(i.getProduct(), _months);
		if(_productStats.containsKey(i.getProduct())){
			ps = _productStats.get(i.getProduct());
		}else{
			_productStats.put(i.getProduct(), ps);
		}
		ps.addItem(i);
	}

	@Override
	public void visit(Product p) {
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
	
//	public List<Product> getProducts(){
//		Collections.sort(_products);
//		return _products;
//	}
	
	public HashMap<Product, ProductStat> getProductStatsMap(){
		return _productStats;
	}

}
