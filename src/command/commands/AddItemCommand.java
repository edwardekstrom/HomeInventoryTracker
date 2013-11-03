package command.commands;

import command.Command;
import model.*;

/**
  *	A Command holding all execution information
  */
public class AddItemCommand extends Command{
	
	private Product product;
	private Date entryDate;
	private StorageUnit storageUnit;

	/**
	 *	A constructor that holds onto arguments
	 */
	public AddItemCommand(Product product, Date entryDate, StorageUnit su){
		this.product = product;
		this.entryDate = entryDate;
		this.storageUnit = su;
	}

	/**
	 * Run the Command
	 */
	public void execute(){
		
	}

	/**
	 * Undo the Command
	 */
	public void executeInverse(){
		
	}

}