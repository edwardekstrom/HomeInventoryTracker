/**
 * 
 */
package persistance;

import model.Item;
import model.Product;
import model.ProductContainer;

import java.io.File;

/**
 * @author Capchu
 *
 */
public class DBPersistor implements Persistor {

	SQLDataAccessObject _doa =null;

	public DBPersistor(){
		SQLTransactionManager.initialize();
		_doa = new SQLDataAccessObject();
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#insertItem(model.Item)
	 */
	@Override
	public void insertItem(Item toInsert) {
		SQLTransactionManager.begin();
		_doa.insertItem(toInsert);
		SQLTransactionManager.end(true);

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#updateItem(model.Item)
	 */
	@Override
	public void updateItem(Item toUpdate) {
		SQLTransactionManager.begin();
		_doa.updateItem(toUpdate);
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#deleteItem(model.Item)
	 */
	@Override
	public void deleteItem(Item toDelete) {
		SQLTransactionManager.begin();
		_doa.deleteItem(toDelete);
		SQLTransactionManager.end(true);

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#moveItem(model.Item)
	 */
	@Override
	public void moveItem(Item toMove, ProductContainer from, ProductContainer to) {
		SQLTransactionManager.begin();
		_doa.moveItem(toMove,to);
		SQLTransactionManager.end(true);

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#readItems()
	 */
	@Override
	public void readItems() {
		SQLTransactionManager.begin();
		_doa.readItems();
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#insertProduct(model.Product)
	 */
	@Override
	public void insertProduct(Product toInsert) {
<<<<<<< HEAD
		SQLTransactionManager.begin();

		SQLTransactionManager.end(true);
=======
>>>>>>> 44a14152f70c0727421d60bc6b74bdb0441af490

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#updateProduct(model.Product)
	 */
	@Override
	public void updateProduct(Product toUpdate) {
		SQLTransactionManager.begin();
		_doa.insertProduct(toUpdate);
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#deleteProduct(model.Product)
	 */
	@Override
<<<<<<< HEAD
	public void deleteProduct(Product toDelete) {
		SQLTransactionManager.begin();
		_doa.deleteProduct(toDelete);
		SQLTransactionManager.end(true);
=======
	public void deleteProduct(Product toDelete, ProductContainer whereFrom) {
		// TODO Auto-generated method stub

>>>>>>> 44a14152f70c0727421d60bc6b74bdb0441af490
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#moveProduct(model.Product)
	 */
	@Override
	public void moveProduct(Product toMove) {
		SQLTransactionManager.begin();
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#readProducts()
	 */
	@Override
	public void readProducts() {
		SQLTransactionManager.begin();
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#insertProductContainer(model.ProductContainer)
	 */
	@Override
	public void insertProductContainer(ProductContainer toInsert) {
		SQLTransactionManager.begin();
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#updateProductContainer(model.ProductContainer)
	 */
	@Override
	public void updateProductContainer(ProductContainer toUpdate) {
		SQLTransactionManager.begin();
		SQLTransactionManager.end(true);

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#deleteProductContainer(model.ProductContainer)
	 */
	@Override
	public void deleteProductContainer(ProductContainer toDelete) {
		SQLTransactionManager.begin();
		SQLTransactionManager.end(true);
	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#readProductContainers()
	 */
	@Override
	public void readProductContainers() {
		SQLTransactionManager.begin();
		SQLTransactionManager.end(true);
	}

	@Override
	public void loadAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		
		
	}

	/**
	 *	Called if the sql database is not found. create the files
	 *
	 */
	public void createTables(){
		SQLTransactionManager.begin();
		_doa.createTables();
		SQLTransactionManager.end(true);
	}

}
