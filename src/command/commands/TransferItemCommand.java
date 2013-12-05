package command.commands;

import persistance.Persistor;
import singletons.Configuration;
import command.Command;
import model.*;
import gui.item.ItemData;
import gui.product.ProductData;
import gui.inventory.ProductContainerData;
import gui.batches.TransferItemBatchController;
import facade.ItemFacade;

/**
  *	A Command holding all execution information
  */
public class TransferItemCommand extends Command{
	
	private ItemData _item;
	private ProductData _product;
	private ProductContainerData _targetPCD;
	private ProductContainerData _sourcePCD;
	private TransferItemBatchController _tibc;
	private boolean _addedProductToView = true;
	private boolean _addedProductToModel = true;

	/**
	 *	A constructor that holds onto arguments
	 */
	public TransferItemCommand(ItemData id, ProductData pd, ProductContainerData pcd,
			 TransferItemBatchController tibc ){
		_item = id;
		_product = pd;
		_targetPCD = pcd;
		_tibc = tibc;
	}

	/**
	 * Run the Command
	 */
	public void execute(){

		Item item = (Item)_item.getTag();
		ProductContainer sourcePC = item.getContainer();
		_sourcePCD = sourcePC.getTagData();

		// should be done in the facade....
		ProductContainer targetPC = (ProductContainer)_targetPCD.getTag();
		_addedProductToModel = !targetPC.containsProduct(item.getProduct());

		
		Persistor persistor = Configuration.getInstance().getPersistor();
		persistor.moveItem(item, sourcePC, (ProductContainer)_targetPCD.getTag());

		ItemFacade.getInstance().moveItemInTree(
			item,
			(ProductContainer)_targetPCD.getTag());
		

		
		
		_addedProductToView = _tibc.addProduct(_product);
		_tibc.addItem(_item);
		
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		if (_addedProductToModel){// should be in the facade...
			ProductContainer pc = (ProductContainer)_targetPCD.getTag();
			pc.removeProduct((Product)_product.getTag());
			// System.out.println("yo man");
		}
		
		Persistor persistor = Configuration.getInstance().getPersistor();
		persistor.moveItem((Item)_item.getTag(), 
						   (ProductContainer)_targetPCD.getTag(), 
						   (ProductContainer)_sourcePCD.getTag());
		
		ItemFacade.getInstance().moveItemInTree(
			(Item)_item.getTag(),
			(ProductContainer)_sourcePCD.getTag());
		_tibc.removeItem(_item);
		
		


		if (_addedProductToView)
			_tibc.removeProduct(_product);



	}

}