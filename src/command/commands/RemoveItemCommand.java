package command.commands;

import command.Command;
import model.*;

/**
  *	A Command holding all execution information
  */
public class RemoveItemCommand extends Command{
	
	private Item item;

	/**
	 *	A constructor that holds onto arguments
	 */
	public RemoveItemCommand(Item item){
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