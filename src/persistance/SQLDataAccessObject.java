package persistance;

	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import model.Barcode;
import model.Date;
import model.Item;
import model.Product;
import model.ProductContainer;

public class SQLDataAccessObject {
	
	SimpleDateFormat _dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

	/**Inserts the given item into the Database
	 * 
	 * @param toInsert - the item to be inserted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is added to the database
	 */
	public boolean insertItem(Item toInsert){
		
		try {
			String query = "INSERT INTO 'items' ('product_container','product','barcode'," +
					"'entry_date','exit_date','expiration_date','removed'" +
					")VALUES(?,?,?,?,?,?,?,)";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, toInsert.getContainer().getID());
			stmt.setInt(2, toInsert.getProduct().getID());
			stmt.setString(3, toInsert.getBarcode().getBarcode());
			stmt.setDate(4, new java.sql.Date(toInsert.getEntryDate().getDateAsLong()));
			stmt.setDate(5, new java.sql.Date(toInsert.getExitTime().getDateTimeAsLong()));
			stmt.setDate(6, new java.sql.Date(toInsert.getExpirationDate().getDateAsLong()));
			stmt.setBoolean(7, false);
			if (stmt.executeUpdate() == 1) {
				Statement keyStmt = SQLTransactionManager.getConnection().createStatement();
				ResultSet keyRS = keyStmt.executeQuery("select last_insert_rowid()");
				try {
					keyRS.next();
					int id = keyRS.getInt(1);
					toInsert.setID(id);
				}
				finally {
					keyRS.close();
				}
			}	
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	/**Updates the given item in the Database
	 * 
	 * @param toUpdate - the item to be updated
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is modified in the database
	 */
	public boolean updateItem(Item toUpdate){
		try {
			String query = "UPDATE 'items'" +
					"SET entry_date=?,'expiration_date'=?" +
					"WHERE barcode=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(toUpdate.getEntryDate().getDateAsLong()));
			stmt.setDate(2, new java.sql.Date(toUpdate.getExpirationDate().getDateAsLong()));
			stmt.setString(3, toUpdate.getBarcode().getBarcode());		
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	/**Deletes the given item in the Database
	 * 
	 * @param toDelete - the items to be deleted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is deleted from the database
	 */
	public boolean deleteItem(Item toDelete){
		try {
			String query = "UPDATE 'items'" +
					"SET removed=?" +
					"WHERE barcode=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setBoolean(1, true);
			stmt.setString(2, toDelete.getBarcode().getBarcode());		
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	/**Moves the given item in the Database (modifies references)
	 * 
	 * @param toMove - the items to be inserted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is moved in the database
	 */
	public boolean moveItem(Item toMove){
		try {
			String query = "UPDATE 'items'" +
					"SET product_container=?" +
					"WHERE barcode=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, toMove.getContainer().getID());
			stmt.setString(2, toMove.getBarcode().getBarcode());		
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	/**Reads the items in the database to populate the model
	 * 
	 * 
	 * @precondition model has no items
	 * @postcondition The Items are added to the model
	 */
	public void readItems(){
		
	}
	
	
	/**Inserts the given Product into the Database
	 * 
	 * @param toInsert - the Product to be inserted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is added to the database
	 */
	public boolean insertProduct(Product toInsert){
		try {
			
			String query = "INSERT INTO 'products' ('description','three_month_supply',"+
					"'amount','unit','shelf_life','barcode'"+
					")VALUES(?,?,?,?,?,?)";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setString(1, toInsert.getDescription());
			stmt.setInt(2, toInsert.getThreeMonthSupply());
			stmt.setDouble(3, toInsert.getSizeAmount());
			stmt.setString(4, toInsert.getSizeUnit());
			stmt.setString(6, toInsert.getBarcode().getBarcode());
			if (stmt.executeUpdate() == 1) {
				Statement keyStmt = SQLTransactionManager.getConnection().createStatement();
				ResultSet keyRS = keyStmt.executeQuery("select last_insert_rowid()");
				try {
					keyRS.next();
					int id = keyRS.getInt(1);
					toInsert.setID(id);
				}
				finally {
					keyRS.close();
				}
			}	
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	/**Updates the given Product in the Database
	 * 
	 * @param toUpdate - the Product to be updated
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is modified in the database
	 */
	public boolean updateProduct(Product toUpdate){
		return false;
	}
	
	/**Deletes the given Product in the Database
	 * 
	 * @param toDelete - the Products to be deleted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is deleted from the database
	 */
	public boolean deleteProduct(Product toDelete){
		return false;
	}
	
	/**Moves the given Product in the Database (modifies references)
	 * 
	 * @param toMove - the Products to be inserted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is moved in the database
	 */
	public boolean moveProduct(Product toMove){
		return false;
	}
	
	/**Reads the Products in the database to populate the model
	 * 
	 * 
	 * @precondition model has no Products
	 * @postcondition The Products are added to the model
	 */
	public void readProducts(){
		
	}
	
	
	/**Inserts the given ProductContainer into the Database
	 * 
	 * @param toInsert - the ProductContainer to be inserted
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is added to the database
	 */
	public boolean insertProductContainer(ProductContainer toInsert){
		return false;
	}
	
	/**Updates the given ProductContainer in the Database
	 * 
	 * @param toUpdate - the ProductContainer to be updated
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is modified in the database
	 */
	public boolean updateProductContainer(ProductContainer toUpdate){
		return false;
	}
	
	/**Deletes the given ProductContainer in the Database
	 * 
	 * @param toDelete - the ProductContainers to be deleted
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is deleted from the database
	 */
	public boolean deleteProductContainer(ProductContainer toDelete){
		return false;
	}
	
	/**Reads the ProductContainers in the database to populate the model
	 * 
	 * 
	 * @precondition model has no ProductContainers
	 * @postcondition The ProductContainers are added to the model
	 */
	public void readProductContainers(){
		
	}
	
	public int getItemIdByBarcode(Barcode barcode){
		//TODO
		return 0;
	}
	
}
