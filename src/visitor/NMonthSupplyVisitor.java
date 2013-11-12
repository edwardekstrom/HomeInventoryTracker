package visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import singletons.ItemsManager;
import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.StorageUnit;

public class NMonthSupplyVisitor implements ReportVisitor {
	private int _n = -1;
	private List<Product> _products = new ArrayList<Product>();
	private List<ProductGroup> _productGroups = new ArrayList<ProductGroup>();
	
	public NMonthSupplyVisitor(int n) {
		_n = n;
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
		
	}

	@Override
	public void visit(Product p) {
		if(p.getThreeMonthSupply() > 0){
			float oneMonth = (float)p.getThreeMonthSupply()/3.0f;
			float currentSupply = p.getCurrentSupply();
			if(currentSupply < oneMonth*_n){
				_products.add(p);
			}
		}
	}

	@Override
	public void visit(ProductContainer pc) {
		if(pc instanceof StorageUnit) return;
		ProductGroup currentGroup = (ProductGroup)pc;
		float oneMonth = (float)currentGroup.getThreeMonthSup().getAmount()/3.0f;
		if(currentGroup.getThreeMonthSup().getAmount() > 0){
			float totalSupply = currentGroup.getCurrentSupply();
			if(totalSupply < oneMonth * _n)
				_productGroups.add(currentGroup);
		}
	}

	@Override
	public void visit(HomeInventory hi) {
		
	}
	
	public List<Product> getDeficientProducts(){
		Collections.sort(_products);
		return _products;
	}
	
	public List<ProductGroup> getDeficientProductGroups(){
		return _productGroups;
	}
	
	public int getN(){
		return _n;
	}

}
