package singletons_tests;

import static org.junit.Assert.*;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;

import org.junit.Test;

import com.sun.source.tree.AssertTree;

import singletons.ItemsManager;
import singletons.ProductsManager;
import data_structures.Barcode;
import data_structures.Date;
import data_structures.Item;
import data_structures.Product;
import data_structures.StorageUnit;
import data_structures.UnitSize;

public class ProductsManagerTest {
	@Test
	public void testAdd() {
		ProductsManager pm = ProductsManager.getInstance();
		Product p;
		try {
			p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			pm.addProduct(p);
			
			assertTrue(pm.containsProduct(p));
		} catch (InvalidUnitException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}
	
	@Test
	public void testRemove() {
		ProductsManager pm = ProductsManager.getInstance();
		Product p;
		try {
			p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			pm.addProduct(p);
			
			
			
			pm.removeProduct(p);

			assertFalse(pm.containsProduct(p));
		} catch (InvalidUnitException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	
	@Test
	public void testContainsProduct(){
		Product p;
		try {
			p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			ProductsManager pm = ProductsManager.getInstance();
			pm.addProduct(p);
			assertTrue(pm.containsProduct(p));
		} catch (InvalidUnitException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testCanAddProduct(){
		Product p;
		try {
			p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			ProductsManager pm = ProductsManager.getInstance();
			assertFalse(pm.containsProduct(p));
			assertTrue(pm.canAddProduct(p));
			pm.addProduct(p);
			assertFalse(pm.canAddProduct(p));
		} catch (InvalidUnitException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetUnmodList(){
		Product p;
		try {
			p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			ProductsManager pm = ProductsManager.getInstance();
			pm.getUnmodifiableList().add(p);
		} catch (InvalidUnitException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}


