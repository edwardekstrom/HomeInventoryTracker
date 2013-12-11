

import static org.junit.Assert.*;
import hit_exceptions.IllegalNameException;

import org.junit.Test;


import singletons.BarcodeManager;

public class BarcodeManagerTest {
	
	@Test
	public void testAdd(){
		BarcodeManager bm = BarcodeManager.getInstance();
		int oldSize = bm.barcodeListSize();
		bm.generateUniqueBarcode();
		assertTrue( bm.barcodeListSize() == oldSize+1);
	}
	
	@Test
	public void testAddDuplicateUserBarcode(){
		BarcodeManager bm = BarcodeManager.getInstance();
		try{
		bm.addUserBarcode("12345");
		}catch(IllegalNameException e){
		}
		try{
		assertFalse(bm.addUserBarcode("12345"));
		}catch(IllegalNameException e){
		}
	}
	
	@Test
	public void testGetInstance(){
		BarcodeManager bm = BarcodeManager.getInstance();
		BarcodeManager bm1 = BarcodeManager.getInstance();
		assertTrue(bm==bm1);
	}
	
	@Test
	public void testAddUserBarcode(){
		BarcodeManager bm = BarcodeManager.getInstance();
		try{
		bm.addUserBarcode("123asdf:");
		}catch(IllegalNameException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testAddUserBarcode1(){
		BarcodeManager bm = BarcodeManager.getInstance();
		try{
		bm.addUserBarcode("123asdf1");
		}catch(IllegalNameException e){
			assertTrue(false);
		}

	}
	
	
}
