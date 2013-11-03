package command.commands;

import model.*;
import command.Command;

/**
  *	A Command holding all execution information
  */
public class TransferItemCommand extends Command{
	
	private Item item;

	/**
	 *	A constructor that holds onto arguments
	 */
	public TransferItemCommand(Item item){
		this.item = item;
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