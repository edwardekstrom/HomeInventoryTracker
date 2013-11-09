package command.commands;

import command.Command;
import model.*;
import gui.item.ItemData;
import gui.product.ProductData;

import gui.batches.RemoveItemBatchController;

import facade.ItemFacade;

/**
  *	A Command holding all execution information
  */
public class RemoveItemCommand extends Command{
	
	private ItemData _item;
	private ProductData _product;
	private RemoveItemBatchController _ric;

	/**
	 *	A constructor that holds onto arguments
	 */
	public RemoveItemCommand(ItemData id,ProductData pd,RemoveItemBatchController ric){
		_item = id;
		_product = pd;
		_ric =ric;
	}

	/**
	 * Run the Command
	 */
	public void execute(){

		ItemFacade.getInstance().removeItem((Item)_item.getTag());
		_ric.addItem(_item);
		_ric.addProduct(_product);
			
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		ItemFacade.getInstance().addItem((Item)_item.getTag());
		_ric.removeItem(_item);
		_ric.removeProduct(_product);
	}

}