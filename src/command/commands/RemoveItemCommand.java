package command.commands;

import persistance.Persistor;
import singletons.Configuration;
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
	private boolean _addedProductToView;

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
		
		Persistor persistor = Configuration.getInstance().getPersistor();
		persistor.deleteItem((Item)_item.getTag());
		

		ItemFacade.getInstance().removeItem((Item)_item.getTag());
	
		HomeInventory homeInventory = Configuration.getInstance().getHomeInventory();
		Serializer.serializeHIT(homeInventory);
		_addedProductToView = _ric.addProduct(_product);
		_ric.addItem(_item);
		
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){		
		ItemFacade.getInstance().addItem((Item)_item.getTag());

		
		Persistor persistor = Configuration.getInstance().getPersistor();
		persistor.insertItem((Item)_item.getTag());
		
		_ric.removeItem(_item);
		if (_addedProductToView)
			_ric.removeProduct(_product);
	}

}