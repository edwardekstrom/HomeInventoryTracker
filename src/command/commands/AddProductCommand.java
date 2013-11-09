package command.commands;

import command.Command;
import model.*;
import facade.*;
import gui.batches.AddItemBatchController;
import gui.product.*;

import java.util.Map;

/**
  *	A Command holding all execution information
  */
public class AddProductCommand extends Command{
	
	private Map<String,String> _args;

	private ImportProductCommand _ipCommand;

	/**
	 *	A constructor that holds onto arguments
	 */
	public AddProductCommand( Map<String,String> args){
		_args = args;
	}

	/**
	 * Run the Command
	 */
	public void execute(){


		try{

			Integer sl = Integer.parseInt(shelfLife);
			Integer tms = Integer.parseInt(threeMonthSupply);

			Product p = new Product(new Date(),new Barcode(barcode),desc,sl,tms,amount,unit);
			ProductData pd = new ProductData();

			pd.setBarcode(barcode);
			pd.setSize(amount + " " + unit);
			pd.setShelfLife(shelfLife);
			pd.setSupply(threeMonthSupply);
			//pd.setCount(unit);
			pd.setDescription(desc);
			pd.setTag(p);
			p.setTagData(pd);
			_productFacade.addProduct(p);


			
		}catch (Exception e){}
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		
	}
}