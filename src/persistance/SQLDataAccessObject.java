package persistance;

	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;


import java.util.Map;

import model.*;

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
					"'entry_date','expiration_date','removed'" +
					")VALUES(?,?,?,?,?,?)";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, toInsert.getContainer().getID());
			stmt.setInt(2, toInsert.getProduct().getID());
			stmt.setString(3, toInsert.getBarcode().getBarcode());
			stmt.setDate(4, new java.sql.Date(toInsert.getEntryDate().getDateAsLong()));
			stmt.setDate(5, new java.sql.Date(toInsert.getExpirationDate().getDateAsLong()));
			stmt.setBoolean(6, false);
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
			}else{
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
					"SET entry_date=?'expiration_date'=?" +
					"WHERE barcode=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(toUpdate.getEntryDate().getDateAsLong()));
			stmt.setDate(2, new java.sql.Date(toUpdate.getExpirationDate().getDateAsLong()));
			stmt.setString(3, toUpdate.getBarcode().getBarcode());
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
	public boolean moveItem(Item toMove,ProductContainer destination){
		try {
			String query = "UPDATE 'items'" +
					"SET product_container=?"+
					"WHERE barcode=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, destination.getID());
			stmt.setString(2, toMove.getBarcode().getBarcode());
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**Reads the items in the database to populate the model
	 * 
	 * 
	 * @precondition model has no items
	 * @postcondition The Items are added to the model
	 */
	public ArrayList<Item> readItems(){
		try{
			String query = "SELECT id, product_container, product, barcode, entry_date, exit_date, expiration_date, removed FROM items;";
			ResultSet rs = SQLTransactionManager.getConnection().prepareStatement(query).executeQuery();
			while(rs.next()){
				Item newItem = new Item(null, null, null, null);
				System.out.println(rs.getString("barcode"));
			}
			}catch(Exception e){
				
			}
		return new ArrayList<Item>();
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
			System.out.println("HI");
			
			String query = "INSERT INTO 'products' ('description','three_month_supply',"+
					"'amount','unit','shelf_life','barcode'"+
					")VALUES(?,?,?,?,?,?)";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setString(1, toInsert.getDescription());
			stmt.setInt(2, toInsert.getThreeMonthSupply());
			stmt.setDouble(3, toInsert.getSizeAmount());
			stmt.setString(4, toInsert.getSizeUnit());
			stmt.setInt(5, toInsert.getShelfLife());
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
			}else{
				return false;
			}
			
			List<ProductContainer> containerList = toInsert.getContainersList();
			for(ProductContainer pc : containerList){
				String queryJT = "INSERT INTO 'pc_join_p' ('product_container_id','product_id'"+
						")VALUES(?,?)";
				PreparedStatement stmtJT = SQLTransactionManager.getConnection().prepareStatement(queryJT);
				stmtJT.setInt(1, pc.getID());
				stmtJT.setInt(2, toInsert.getID());
				stmtJT.executeUpdate();
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
		try {
			String query = "UPDATE 'products'" +
					"SET description=?, three_month_supply=?, amount=?, unit=?, shelf_life=?" +
					"WHERE barcode=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setString(1, toUpdate.getDescription());
			stmt.setInt(2, toUpdate.getThreeMonthSupply());
			stmt.setDouble(3, toUpdate.getSizeAmount());
			stmt.setString(4, toUpdate.getSizeUnit());
			stmt.setInt(5, toUpdate.getShelfLife());
			stmt.setString(6, toUpdate.getBarcode().getBarcode());
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return true;
	}
	 
	/**Deletes the given Product in the Database
	 * 
	 * @param toDelete - the Products to be deleted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is deleted from the database
	 */
	public boolean deleteProduct(Product toDelete, ProductContainer whereFrom){
		try {
			String query = "DELETE FROM 'pc_join_p'" +
					"WHERE product_container_id=? AND product_id=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, whereFrom.getID());
			stmt.setInt(2, toDelete.getID());
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**Moves the given Product in the Database (modifies references)
	 * 
	 * @param toMove - the Products to be inserted
	 * 
	 * @precondition passed a valid Product
	 * @postcondition The Product is moved in the database
	 */
	public boolean moveProduct(Product toMove, ProductContainer whereTo){
		try {
			String query = "INSERT INTO 'pc_join_p'('product_container_id', 'product_id'" +
					")VALUES(?,?)";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, whereTo.getID());
			stmt.setInt(2, toMove.getID());
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**Reads the Products in the database to populate the model
	 * 
	 * 
	 * @precondition model has no Products
	 * @postcondition The Products are added to the model
	 */
	public ArrayList<Product> readProducts(){
		ArrayList<Product> products = new ArrayList<Product>();

		try{
			Statement statement = SQLTransactionManager.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM products");

			while(rs.next()){
				model.Date date = new model.Date(new java.util.Date());
				Barcode barcode = new Barcode(rs.getString("barcode"));
				String description = rs.getString("description");
				Integer shelfLife = rs.getInt("shelf_life");
				Double threeMonthSupply = rs.getDouble("three_month_supply");
				String amount  = rs.getString("amount");
				String unit = rs.getString("unit");

				Product p = new Product(date,barcode,description,shelfLife,
					new Integer(threeMonthSupply.intValue()),
					amount,unit);
				p.setID(rs.getInt("id"));

		 		products.add(p);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();}

		return products;
	}

	public Map<Integer,ArrayList<Integer>> readJoin(){
		Map<Integer,ArrayList<Integer>> joinTable = new HashMap<Integer,ArrayList<Integer>>();

		try{
			Statement statement = SQLTransactionManager.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM pc_join_p");

			while(rs.next()){
				int productId = rs.getInt("product_id");
				int productContainerId = rs.getInt("product_container_id");

				ArrayList<Integer> productIds = joinTable.get(productContainerId);
				if (productIds == null){
					productIds = new ArrayList<Integer>();
					productIds.add(productId);
					joinTable.put(productContainerId,productIds);
				}
				else
					productIds.add(productId);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();}

		return joinTable;

	}
	
	/**Inserts the given ProductContainer into the Database
	 * 
	 * @param toInsert - the ProductContainer to be inserted
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is added to the database
	 */
	public boolean insertProductContainer(ProductContainer toInsert){
		if(toInsert.getClass() == ProductGroup.class){
			try {
				ProductGroup toInsertProd = (ProductGroup) toInsert;
				String query = "INSERT INTO 'product_containers' ('name','parent','storage_unit',"+
						"'three_month_amount','three_month_unit'"+
						")VALUES(?,?,?,?,?)";
				PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
				stmt.setString(1, toInsertProd.getName());
				stmt.setInt(2, toInsertProd.getContainer().getID());
				stmt.setInt(3, toInsertProd.getStorageUnit().getID());
				stmt.setDouble(4, toInsertProd.getThreeMonthSup().getAmount());
				stmt.setString(5, toInsertProd.getThreeMonthSup().getUnit());
				if (stmt.executeUpdate() == 1) {
					Statement keyStmt = SQLTransactionManager.getConnection().createStatement();
					ResultSet keyRS = keyStmt.executeQuery("select last_insert_rowid()");
					try {
						keyRS.next();
						int id = keyRS.getInt(1);
						toInsertProd.setID(id);
					}
					finally {
						keyRS.close();
					}
				}else{
					return false;
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			try {
				StorageUnit toInsertStor = (StorageUnit) toInsert;
				String query = "INSERT INTO 'product_containers' ('name','storage_unit'"+
						")VALUES(?,?)";
				PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
				stmt.setString(1, toInsertStor.getName());
				stmt.setInt(2, toInsertStor.getStorageUnit().getID());
				if (stmt.executeUpdate() == 1) {
					Statement keyStmt = SQLTransactionManager.getConnection().createStatement();
					ResultSet keyRS = keyStmt.executeQuery("select last_insert_rowid()");
					try {
						keyRS.next();
						int id = keyRS.getInt(1);
						toInsertStor.setID(id);
					}
					finally {
						keyRS.close();
					}
				}else{
					return false;
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**Updates the given ProductContainer in the Database
	 * 
	 * @param toUpdate - the ProductContainer to be updated
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is modified in the database
	 */
	public boolean updateProductContainer(ProductContainer toUpdate){
		if(toUpdate.getClass() == ProductGroup.class){
			try {
				ProductGroup toUpdateProd = (ProductGroup) toUpdate;
				String query = "UPDATE 'product_containers'" +
						"SET name=?, three_month_amount=?, three_month_unit=?" +
						"WHERE id=?";
				PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
				stmt.setString(1, toUpdateProd.getName());
				stmt.setDouble(2, toUpdateProd.getThreeMonthSup().getAmount());
				stmt.setString(3, toUpdateProd.getThreeMonthSup().getUnit());
				stmt.setInt(4, toUpdateProd.getID());
				if(stmt.executeUpdate() != 1){
					return false;
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}else{
			try {
				StorageUnit toUpdateStor = (StorageUnit) toUpdate;
				String query = "UPDATE 'product_containers'" +
						"SET name=?" +
						"WHERE id=?";
				PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
				stmt.setString(1, toUpdateStor.getName());
				stmt.setInt(2, toUpdateStor.getID());	
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**Deletes the given ProductContainer in the Database
	 * 
	 * @param toDelete - the ProductContainers to be deleted
	 * 
	 * @precondition passed a valid ProductContainer
	 * @postcondition The ProductContainer is deleted from the database
	 */
	public boolean deleteProductContainer(ProductContainer toDelete){
		try {
			String query = "DELETE FROM 'product_containers'" +
					"WHERE id=?";
			PreparedStatement stmt = SQLTransactionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, toDelete.getID());
			
			String query2 = "DELETE FROM 'pc_join_p'" +
					"WHERE product_container_id=?";
			PreparedStatement stmt2 = SQLTransactionManager.getConnection().prepareStatement(query2);
			stmt2.setInt(1, toDelete.getID());	
			if(stmt.executeUpdate() != 1){
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return true;
	}
	
	/**Reads the ProductContainers in the database to populate the model
	 * 
	 * 
	 * @precondition model has no ProductContainers
	 * @postcondition The ProductContainers are added to the model
	 */
	public ArrayList<ProductContainer> readProductContainers(){
		ArrayList<ProductContainer> pcList = new ArrayList<ProductContainer>();

		try{

			Statement statement = SQLTransactionManager.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM product_containers");

			while(rs.next()){
				String name = rs.getString("name");
				int id = rs.getInt("id");
				Double three_month_amount = rs.getDouble("three_month_amount");
				String three_month_unit = rs.getString("three_month_unit");
				Integer parent = rs.getInt("parent");



				ProductContainer pc = null;
				if (parent == 0)
					pc = new StorageUnit(name);
				else{
					pc = new ProductGroup(name);
					UnitSize us = new UnitSize(three_month_amount + "",three_month_unit);
					((ProductGroup)pc).setThreeMonthSup(us);
				}
				pc.setID(id);
				pc._parent_id = parent;
				pcList.add(pc);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();}

		return pcList;
	}
	
	public void createTables(){

		try {
			String query = "DROP TABLE IF EXISTS 'items';";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query =	"CREATE TABLE 'items' ('id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 'product_container' INTEGER, 'product' INTEGER, 'barcode' VARCHAR, 'entry_date' DATETIME, 'exit_date' DATETIME, 'expiration_date' DATETIME, 'removed' BOOL);";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query = "DROP TABLE IF EXISTS 'pc_join_p';";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query = "CREATE TABLE 'pc_join_p' ('product_container_id' INTEGER, 'product_id' INTEGER);";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query = "DROP TABLE IF EXISTS 'product_containers';";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query = "CREATE TABLE 'product_containers' ('id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 'name' VARCHAR, 'parent' INTEGER, 'storage_unit' INTEGER, 'three_month_amount' DOUBLE, 'three_month_unit' VARCHAR);";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query = "DROP TABLE IF EXISTS 'products';";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
			query = "CREATE TABLE 'products' ('id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 'description' TEXT, 'three_month_supply' DOUBLE, 'amount' DOUBLE, 'unit' VARCHAR, 'shelf_life' INTEGER, 'barcode' VARCHAR);";
			SQLTransactionManager.getConnection().prepareStatement(query).executeUpdate();	
				
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
