//package singletons_tests;

import static org.junit.Assert.*;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;

import java.util.List;

import model.*;

import org.junit.Test;

import singletons.ItemsManager;

public class ItemsManagerTest {
	
	@Test
	public void testAdd(){

		try {
			ItemsManager im = ItemsManager.getInstance();
			Product p;
			p = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			
			Item i = new Item(p, new Barcode("12345"), new Date(), new ProductContainer(){});
			Item j = new Item(p, new Barcode("12345"), new Date(), new ProductContainer(){});
			
			im.addItem(i);
			
			assertTrue("the list should have one item.", im.getUnmodifiableAllItemsList().contains(i));
			
			im.addItem(j);
			
			assertTrue("the list should have two items.", im.getUnmodifiableAllItemsList().contains(j));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void testRemove(){
		ItemsManager im1 = ItemsManager.getInstance();
		Product p1;
		try {
			p1 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			Item i1 = new Item(p1, new Barcode("12345"), new Date(), new ProductContainer() {});
			
			im1.addItem(i1);
			im1.removeItem(i1);
			
			assertFalse("the list should have zero item", im1.getUnmodifiableAllItemsList().contains(i1));	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	@Test(expected = UnsupportedOperationException.class)
//	public void testGetUnmodList(){
//		ItemsManager im2 = ItemsManager.getInstance();
//		Product p2;
//		try {
//			p2 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
//			Item i2 = new Item(p2, new Barcode("12345"), new Date(), new ProductContainer() {
//			});
//		
//			im2.getUnmodifiableAllItemsList().add(i2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	
	@Test
	public void testGetInstance(){
		ItemsManager im1 = ItemsManager.getInstance();
		ItemsManager im2 = ItemsManager.getInstance();
		assertTrue(im1==im2);
	}
	
	@Test
	public void testCanAddItem(){
		ItemsManager im = ItemsManager.getInstance();
		Product p1;
		try {
			p1 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			Item i = new Item(p1, new Barcode("1234"), new Date(), new ProductContainer() {
			});
			assertTrue(im.canAddItem(i));
			im.addItem(i);
			assertFalse(im.canAddItem(i));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testContainsItem(){
		ItemsManager im = ItemsManager.getInstance();
		Product p1;
		try {
			p1 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
			Item i = new Item(p1, new Barcode("1234"), new Date(), new ProductContainer() {
			});
			im.addItem(i);
			assertTrue(im.containsItem(i));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	@Test (expected = UnsupportedOperationException.class)
//	public void testGetUnmodifiableList(){
//		ItemsManager im = ItemsManager.getInstance();
//		List<Item> l = im.getUnmodifiableAllItemsList();
//		Product p1;
//		try {
//			p1 = new Product(new Date(), new Barcode("12345"), "a product", 1, 3, "1", "count");
//			Item i = new Item(p1, new Barcode("1234"), new Date(), new ProductContainer() {
//			});
//			l.add(i);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	

}

