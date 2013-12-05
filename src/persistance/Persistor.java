/**
 * 
 */
package persistance;

import model.Item;
import model.Product;
import model.ProductContainer;

/**
 * @author Capchu
 *
 */
public interface Persistor {

	/**Inserts the given item into the Database
	 * 
	 * @param toInsert - the item to be inserted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is added to the database
	 */
	public void insertItem(Item toInsert);
	
	/**Updates the given item in the Database
	 * 
	 * @param toUpdate - the item to be updated
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is modified in the database
	 */
	public void updateItem(Item toUpdate);
	
	/**Deletes the given item in the Database
	 * 
	 * @param toDelete - the items to be deleted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is deleted from the database
	 */
	public void deleteItem(Item toDelete);
	
	/**Moves the given item in the Database (modifies references)
	 * 
	 * @param toMove - the items to be inserted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is moved in the database
	 */
	public void moveItem(Item toMove, ProductContainer from, ProductContainer to);
	
	/**Reads the items in the database to populate the model
	 * 
	 * 
	 * @precondition model has no items
	 * @postcondition The Items are added to the model
	 */
	public void readItems();
	
	
	/**Inserts the given Product into the Database
	 * 
	 * @param toInsert - the Product to be inserted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is added to the database
	 */
	public void insertProduct(Product toInsert);
	
	/**Updates the given Product in the Database
	 * 
	 * @param toUpdate - the Product to be updated
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is modified in the database
	 */
	public void updateProduct(Product toUpdate);
	
	/**Deletes the given Product in the Database
	 * 
	 * @param toDelete - the Products to be deleted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is deleted from the database
	 */
	public void deleteProduct(Product toDelete);
	
	/**Moves the given Product in the Database (modifies references)
	 * 
	 * @param toMove - the Products to be inserted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is moved in the database
	 */
	public void moveProduct(Product toMove);
	
	/**Reads the Products in the database to populate the model
	 * 
	 * 
	 * @precondition model has no Products
	 * @postcondition The Products are added to the model
	 */
	public void readProducts();
	
	
	/**Inserts the given ProductContainer into the Database
	 * 
	 * @param toInsert - the ProductContainer to be inserted
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is added to the database
	 */
	public void insertProductContainer(ProductContainer toInsert);
	
	/**Updates the given ProductContainer in the Database
	 * 
	 * @param toUpdate - the ProductContainer to be updated
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is modified in the database
	 */
	public void updateProductContainer(ProductContainer toUpdate);
	
	/**Deletes the given ProductContainer in the Database
	 * 
	 * @param toDelete - the ProductContainers to be deleted
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is deleted from the database
	 */
	public void deleteProductContainer(ProductContainer toDelete);
	
	/**Reads the ProductContainers in the database to populate the model
	 * 
	 * 
	 * @precondition model has no ProductContainers
	 * @postcondition The ProductContainers are added to the model
	 */
	public void readProductContainers();
	
	public void loadAll();
	
	public void save();
	
}
