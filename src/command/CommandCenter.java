package command;

import java.util.Stack;

/**
  *	CommandCeter, a class encapsulating a double stack execution 
  * of facade functions
  */
public class CommandCenter{

	/**
	 * The stack of commands that are done
	 */
	private Stack<Command> done;

	/**
	 * The stack of commands that were undone
	 */
	private Stack<Command> undone;


	/**
	 * An Empty Constructor
	 */
	public CommandCenter(){
		done = new Stack<Command>();
		undone = new Stack<Command>();
	}

	/**
	 * Check whether there is a function to undo
	 * @return boolean value indicating if undo is allowed
	 */
	public boolean canUndo(){
		return (!done.empty());
	}

	/**
	 * Check whether there is a function to redo
	 * @return boolean value indicating if redo is allowed
	 */
	public boolean canRedo(){
		return (!undone.empty());
	}


	/**
	 *	Execute a command and push it onto the done pile
	 *  @param command - the command to be executed
	 */
	public void doIt(Command todo){
		todo.execute();
		done.push(todo);
		this.clearUndone();
	}


	/**
	 * Pop a command off of the done stack and execute the inverse
	 */
	public void undo(){
		Command c = done.pop();
		c.executeInverse();
		undone.push(c);
	}

	/**
	 * Pop a command off of the undone stack and execute
	 */
	public void redo(){
		Command c = undone.pop();
		c.execute();
		done.push(c);
	}



	/**
	 * Clear the done stack(called after and do command)
	 */
	private void clearUndone(){
		undone = new Stack<Command>();
	}


}