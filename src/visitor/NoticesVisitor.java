package visitor;

import java.util.List;

import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

public class NoticesVisitor implements ReportVisitor {

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
		// TODO Auto-generated method stub
		
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

}
