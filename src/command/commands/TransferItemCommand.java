package command.commands;

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
		_sourcePCD = item.getContainer().getTagData();

		ItemFacade.getInstance().moveItemInTree(
			item,
			(ProductContainer)_targetPCD.getTag());
		_tibc.addItem(_item);
		_tibc.addProduct(_product);
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){

		ItemFacade.getInstance().moveItemInTree(
			(Item)_item.getTag(),
			(ProductContainer)_sourcePCD.getTag());
		_tibc.removeItem(_item);
		_tibc.removeProduct(_product);
	}

}