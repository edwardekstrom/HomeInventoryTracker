package data_structures_tests;

import static org.junit.Assert.*;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;

import org.junit.Test;

import data_structures.Barcode;
import data_structures.Date;
import data_structures.HomeInventory;
import data_structures.Item;
import data_structures.Product;
import data_structures.ProductContainer;
import data_structures.Serializer;
import data_structures.StorageUnit;

public class HomeInventorySerializationTest {
	@Test
	public void testSerialization() {
		HomeInventory hi = new HomeInventory();
		StorageUnit su = new StorageUnit();
		Product p;
		
		
		try {
			p = new Product(new Date(), new Barcode("12345"), "product", 1, 1, "1", "count");
			Item i = new Item(p, new Barcode("12345"), new Date(), new StorageUnit());
			su.addItem(i);
			hi.addStorageUnit(su);
			assertTrue(hi.getStorageUnitsCount() == 1);
			int oldStorageUnitCount = hi.getStorageUnitsCount();
			// Save the HomeInventory to file
			//TODO: Modify Test when serialize is finished
			//Serializer.serializeHIT(hi);
			
			// Read the HomeInventory from file
			HomeInventory readOffDisk = Serializer.deserializeHIT();
			
			// Add something to the read HomeInventory
			readOffDisk.addStorageUnit(new StorageUnit());
			
			// Check to make sure the old thing is still there
			assertTrue(readOffDisk.getStorageUnitsCount() == oldStorageUnitCount);
			
		} catch (InvalidUnitException | InvalidAmountException e) {

		}
		

	}
}
