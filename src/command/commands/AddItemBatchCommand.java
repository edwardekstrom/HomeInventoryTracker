package command.commands;

import command.Command;
import model.*;
import gui.batches.AddItemBatchController;
import gui.item.ItemData;

import java.util.Map;




/**
  *	A Command holding all execution information
  */
public class AddItemBatchCommand extends Command{
	
	private Map<String,Object> _args;
	private ItemData[] _items;
	private AddItemBatchController _aibc;

	/**
	 *	A constructor that holds onto arguments
	 */
	public AddItemBatchCommand(AddItemBatchController aibc){
		_aibc = aibc;
	}

	/**
	 * Run the Command
	 */
	public void execute(){
		_args = _aibc.getAIBCInfo();
		
		// Date entryDate = new Date(getView().getEntryDate()); 
		
		// String count = getView().getCount();


		// StorageUnit currUnit = _storageUnit;

		// int numItems = Integer.parseInt(count);

		// for (int i = 0; i < numItems; i++){
		// 	Item item = _itemFacade.addItem(current, entryDate, _storageUnit);
		// 	_items.add(item.getTagData());


		// }
		// loadValues();
		// //displayItemsForProduct(current);
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		
	}

}