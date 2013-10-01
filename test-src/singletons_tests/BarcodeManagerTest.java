package singletons_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.source.tree.AssertTree;

import singletons.BarcodeManager;

public class BarcodeManagerTest {
	@Test
	public void testAdd(){
		BarcodeManager bm = BarcodeManager.getInstance();
		bm.generateUniqueBarcode();
		assertTrue( bm.barcodeListSize() == 1);
	}
	
	@Test
	public void testAddDuplicateUserBarcode(){
		BarcodeManager bm = BarcodeManager.getInstance();
		bm.addUserBarcode("12345");
		assertFalse(bm.addUserBarcode("12345"));
	}
}
