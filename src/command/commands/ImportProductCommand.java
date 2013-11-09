package command.commands;

import command.Command;
import model.*;
import facade.*;
import gui.batches.AddItemBatchController;

import gui.product.ProductData;

/**
  *	A Command holding all execution information
  */
public class ImportProductCommand extends Command{

	private ProductData _pd;
	private AddItemBatchController _aibController;
	private AddItemBatchCommand _aibCommand;

	/**
	 *	A constructor that holds onto arguments
	 */
	public ImportProductCommand(ProductData pd, AddItemBatchController aibc){
		_pd = pd;
		_aibController = aibc;
	}

	/**
	 * Run the Command
	 */
	public void execute(){
		_aibController.addProduct(_pd);
		_aibCommand = new AddItemBatchCommand(_pd,_aibController);
		_aibCommand.execute();	
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		_aibCommand.executeInverse();
		_aibController.removeProduct(_pd);
	}
}