package command.commands;

import command.Command;
import model.*;
import gui.batches.AddItemBatchController;
import gui.item.ItemData;
import gui.product.ProductData;

import java.util.Map;
import java.util.ArrayList;

import persistance.Persistor;
import singletons.Configuration;
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
		_args = _aibc.getAIBCInfo();
		
	}

	/**
	 * Run the Command
	 */
	public void execute(){
		_items = new ArrayList<ItemData>();
		
		
		Date entryDate = (Date)_args.get("entryDate");
		
		StorageUnit su = (StorageUnit)_args.get("storageUnit");
		int _count = (Integer)_args.get("count");
		for (int i = 0; i < _count; i++){
			Item item = ItemFacade.getInstance().addItem((Product)_pd.getTag(), entryDate, su);

			ItemData itemData = item.getTagData();
			_items.add(itemData);
			_aibc.addItem(itemData);
			
			Persistor persistor = Configuration.getInstance().getPersistor();
			persistor.insertItem(item);
		}

	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		for (ItemData id : _items){
			_aibc.removeItem(id);
			
			
			Persistor persistor = Configuration.getInstance().getPersistor();
			persistor.deleteItem((Item)id.getTag());
			
			ItemFacade.getInstance().removeItem((Item)id.getTag());

		}
	}

}