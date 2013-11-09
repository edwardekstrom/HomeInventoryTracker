package command.commands;

import command.Command;
import model.*;
import model.Date;
import gui.batches.AddItemBatchController;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Map;
import java.util.ArrayList;
import facade.ItemFacade;



/**
  *	A Command holding all execution information
  */
public class AddItemBatchCommand extends Command{
	
	private Map<String,Object> _args;
	private ArrayList<ItemData> _items;
	private AddItemBatchController _aibc;
	private ProductData _pd;

	/**
	 *	A constructor that holds onto arguments
	 */
	public AddItemBatchCommand(ProductData pd, AddItemBatchController aibc){
		_pd = pd;
		_aibc = aibc;
		_items = new ArrayList<ItemData>();
	}

	/**
	 * Run the Command
	 */
	public void execute(){
		_items = new ArrayList<ItemData>();
		_args = _aibc.getAIBCInfo();
		
		Date entryDate = (Date)_args.get("entryDate");
		int count = (Integer)_args.get("count");
		StorageUnit su = (StorageUnit)_args.get("storageUnit");

		for (int i = 0; i < count; i++){
			Item item = ItemFacade.getInstance().addItem((Product)_pd.getTag(), entryDate, su);
			ItemData itemData = item.getTagData();
			_items.add(itemData);
			_aibc.addItem(itemData);
		}

	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		
	}

}