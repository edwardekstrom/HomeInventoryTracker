package command;

/**
  *	A Command holding all execution information
  */
public abstract class Command{

	/**
	 * Run the Command
	 */
	public abstract void execute();

	/**
	 * Undo the Command
	 */
	public abstract void executeInverse();

}