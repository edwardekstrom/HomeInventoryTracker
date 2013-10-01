package data_structures_tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import data_structures.Barcode;
import data_structures.HomeInventory;
import data_structures.Product;
import data_structures.Serializer;
import data_structures.StorageUnit;

public class HomeInventorySerializationTest {
	@Test
	public void testSerialization() {
		HomeInventory hi = new HomeInventory();
		StorageUnit su = new StorageUnit();
		Product p = new Product(new Date(), new Barcode("12345"), "product", 1, 1);		
		su.addProduct(p);
		hi.addStorageUnit(su);
		assertTrue(hi.getStorageUnitsCount() == 1);
		
		// Save the HomeInventory to file
		Serializer.serializeHIT(hi);
		
		// Read the HomeInventory from file
		HomeInventory readOffDisk = Serializer.deserializeHIT();
		
		// Add something to the read HomeInventory
		readOffDisk.addStorageUnit(new StorageUnit());
		
		// Check to make sure the old thing is still there
		assertTrue(readOffDisk.getStorageUnitsCount() == 2);

	}
}
