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

	private ImportProductCommand _ipCommand = null;
	private AddItemBatchController _aibc;
	private StorageUnit _su;
	private Product _product;

	/**
	 *	A constructor that holds onto arguments
	 */
	public AddProductCommand( Map<String,String> args, AddItemBatchController aibc){
		_args = args;
		_aibc = aibc;
	}

	/**
	 * Run the Command
	 */
	public void execute(){

		String shelfLife = _args.get("shelfLife");
		String threeMonthSupply = _args.get("threeMonthSupply");
		String amount = _args.get("amount");
		String unit = _args.get("unit");
		String barcode = _args.get("barcode");
		String desc = _args.get("desc");

		ProductData pd = new ProductData();
		try{

			Integer sl = Integer.parseInt(shelfLife);
			Integer tms = Integer.parseInt(threeMonthSupply);

			_product = new Product(new Date(),new Barcode(barcode),desc,sl,tms,amount,unit);
			

			pd.setBarcode(barcode);
			pd.setSize(amount + " " + unit);
			pd.setShelfLife(shelfLife);
			pd.setSupply(threeMonthSupply);
			//pd.setCount(unit);
			pd.setDescription(desc);
			pd.setTag(_product);
			_product.setTagData(pd);
			ProductFacade.getInstance().addProduct(_product);

		}catch (Exception e){}
		if(_ipCommand == null)
			_ipCommand = new ImportProductCommand(pd,_aibc);
		_ipCommand.execute();
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		_ipCommand.executeInverse();
		ProductFacade.getInstance().recursiveRemoveProduct(_product);
	}
}