/**
 * 
 */
package persistance;

/**
 * @author Capchu
 *
 */
public class JavaPersistanceFactory extends AbstractPerstistanceFactory {
	@Override
	public Persistor buildPersistor() {
		return new JavaPersistor();
	}
}
