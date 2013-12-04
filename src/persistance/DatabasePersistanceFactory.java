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

		Persistor persistor = new DBPersistor();

		File f = new File("DBPersistor.sql");
		if(f.exists()) { System.out.println("BOOOOOO YAAAA"); } 
		return persistor;
	}
}
