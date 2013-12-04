/**
 * 
 */
package persistance;

/**
 * @author Capchu
 *
 */
public class DatabasePersistanceFactory extends AbstractPerstistanceFactory {
	@Override
	public Persistor buildPersistor() {
		return new DBPersistor();
	}
}
