package singletons_tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import data_structures.Barcode;
import data_structures.Item;
import data_structures.Product;
import data_structures.ProductContainer;
import data_structures.StorageUnit;
import data_structures.UnitSize;
import singletons.ItemsManager;

public class ItemsManagerTest {
	
	@Test
	public void testAdd(){
		ItemsManager im = ItemsManager.getInstance();
		Product p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		Item i = new Item(p, new Barcode("12345"), new Date(), new ProductContainer() {
		});
		Item j = new Item(p, new Barcode("12345"), new Date(), new ProductContainer() {
		});
		
		im.addItem(i);
		
		assertTrue("the list should have one item.", im.getUnmodifiableAllItemsList().contains(i));
		
		im.addItem(j);
		
		assertTrue("the list should have two items.", im.getUnmodifiableAllItemsList().contains(j));
		
		
	}
	
	@Test
	public void testRemove(){
		ItemsManager im1 = ItemsManager.getInstance();
		Product p1 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		Item i1 = new Item(p1, new Barcode("12345"), new Date(), new ProductContainer() {
		});
		
		im1.addItem(i1);
		im1.removeItem(i1);
		
		assertFalse("the list should have zero item", im1.getUnmodifiableAllItemsList().contains(i1));	
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetUnmodList(){
		ItemsManager im2 = ItemsManager.getInstance();
		Product p2 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3);
		Item i2 = new Item(p2, new Barcode("12345"), new Date(), new ProductContainer() {
		});
	
		im2.getUnmodifiableAllItemsList().add(i2);
	}

}

