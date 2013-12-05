/**
 * 
 */
package persistance;

import singletons.Configuration;

import com.sun.org.apache.xml.internal.serialize.Serializer;

import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;

/**
 * @author Capchu
 *
 */
public class JavaPersistor implements Persistor {

	/* (non-Javadoc)
	 * @see persistance.Persistor#insertItem(model.Item)
	 */
	@Override
	public void insertItem(Item toInsert) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#updateItem(model.Item)
	 */
	@Override
	public void updateItem(Item toUpdate) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#deleteItem(model.Item)
	 */
	@Override
	public void deleteItem(Item toDelete) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#moveItem(model.Item)
	 */
	@Override
	public void moveItem(Item toMove, ProductContainer from, ProductContainer to) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#readItems()
	 */
	@Override
	public void readItems() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#insertProduct(model.Product)
	 */
	@Override
	public void insertProduct(Product toInsert) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#updateProduct(model.Product)
	 */
	@Override
	public void updateProduct(Product toUpdate) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#deleteProduct(model.Product)
	 */
	@Override
	public void deleteProduct(Product toDelete) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#moveProduct(model.Product)
	 */
	@Override
	public void moveProduct(Product toMove) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#readProducts()
	 */
	@Override
	public void readProducts() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#insertProductContainer(model.ProductContainer)
	 */
	@Override
	public void insertProductContainer(ProductContainer toInsert) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#updateProductContainer(model.ProductContainer)
	 */
	@Override
	public void updateProductContainer(ProductContainer toUpdate) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#deleteProductContainer(model.ProductContainer)
	 */
	@Override
	public void deleteProductContainer(ProductContainer toDelete) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see persistance.Persistor#readProductContainers()
	 */
	@Override
	public void readProductContainers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadAll() {
		HomeInventory hi = model.Serializer.deserializeHIT();
		Configuration.getInstance().setHomeInventory(hi);
		model.Serializer.de_storeManagers();
	}

	@Override
	public void save() {
		HomeInventory homeInventory = Configuration.getInstance().getHomeInventory();
		model.Serializer.serializeHIT(homeInventory);
	}

}
