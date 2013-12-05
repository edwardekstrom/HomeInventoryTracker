/**
 * 
 */
package persistance;
import java.io.File;
/**
 * @author Capchu
 *
 */
public class DatabasePersistanceFactory extends AbstractPerstistanceFactory {
	@Override
	public Persistor buildPersistor() {

		DBPersistor persistor = new DBPersistor();
		
		File f = new File("hit.sqlite");
		if(!f.exists()) { 
			persistor.createTables();
		} 
		return persistor;
	}
}
