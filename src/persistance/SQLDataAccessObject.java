package persistance;

	
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			stmt.setInt(1, getProductContainerIdByBarcode(toInsert.getBarcode()));
			stmt.setInt(2, getProductIdByBarcode(toInsert.getBarcode()));
			stmt.setString(3, toInsert.getBarcode().getBarcode());
			stmt.setDate(4, new java.sql.Date(toInsert.getEntryDate().getDateAsLong()));
			stmt.setDate(5, new java.sql.Date(toInsert.getExitTime().getDateTimeAsLong()));
			stmt.setDate(6, new java.sql.Date(toInsert.getExpirationDate().getDateAsLong()));
			stmt.setBoolean(7, false);
			
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
		return false;
	}
	
	/**Moves the given item in the Database (modifies references)
	 * 
	 * @param toMove - the items to be inserted
	 * 
	 * @precondition passed a valid Item
	 * @postcondition The Item is moved in the database
	 */
	public boolean moveItem(Item toMove){
		return false;
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
		return false;
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
	
	private int getProductContainerIdByBarcode(Barcode barcode){
		//TODO:do this
		return 0;
	}
	
	private int getProductIdByBarcode(Barcode barcode){
		//TODO:do this
		return 0;
	}
	
	/**
	 * public static Result add(BookDTO book) {
		
		Result result = new Result();
		
		try {
			String query = "insert into book (title, author, genre) values (?, ?, ?)";
			PreparedStatement stmt = TransactionManager.getConnection().prepareStatement(query);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, convertGenre(book.getGenre()));
			if (stmt.executeUpdate() == 1) {
				Statement keyStmt = TransactionManager.getConnection().createStatement();
				ResultSet keyRS = keyStmt.executeQuery("select last_insert_rowid()");
				try {
					keyRS.next();
					int id = keyRS.getInt(1);
					book.setID(id);
					result.setStatus(true);
				}
				finally {
					keyRS.close();
				}
			}
			else {
				result.setMessage("Could not insert book");
			}
		}
		catch (SQLException e) {
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	 */
}
