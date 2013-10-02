package singletons_tests;

import static org.junit.Assert.*;

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
		Product p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		
		pm.addProduct(p);
		
		assertTrue(pm.containsProduct(p));

	}
	
	@Test
	public void testRemove() {
		ProductsManager pm = ProductsManager.getInstance();
		Product p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		
		pm.addProduct(p);
		
		
		
		pm.removeProduct(p);

		assertFalse(pm.containsProduct(p));
	}

	
	@Test
	public void testContainsProduct(){
		Product p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		ProductsManager pm = ProductsManager.getInstance();
		pm.addProduct(p);
		assertTrue(pm.containsProduct(p));
	}
	
	@Test
	public void testCanAddProduct(){
		Product p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		ProductsManager pm = ProductsManager.getInstance();
		assertFalse(pm.containsProduct(p));
		assertTrue(pm.canAddProduct(p));
		pm.addProduct(p);
		assertFalse(pm.canAddProduct(p));
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetUnmodList(){
		Product p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		ProductsManager pm = ProductsManager.getInstance();
		pm.getUnmodifiableList().add(p);
	}
	
}


