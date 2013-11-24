package visitor;

import java.util.List;

import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

public interface ReportVisitor {

//	/**gathers the data from the model for the implemented report type
//	 * 
//	 * @return list of data gathered for building the report
//	 * @precondition using the correct report type
//	 * @postcondition gathers the needed data from the model for that report
//	 */
//	public List gatherReportData();
	
	public void visit(Item i);
	public void visit(Product p);
	public void visit(ProductContainer pc);
	public void visit(HomeInventory hi);
}
