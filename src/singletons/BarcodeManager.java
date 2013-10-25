package singletons;

import hit_exceptions.IllegalNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.jdi.InvalidTypeException;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import data_structures.HomeInventory;
import data_structures.Item;

public class BarcodeManager {

	private static BarcodeManager _instance = null;
	private List<String> _allBarcodesList;

	/**
	 * This method instantiates the instance of BarcodeManger. 
	 * It will only ever be called one time.
	 * @precondition none
	 * @postcondition a new barcode manager is constructed
	 */
	private BarcodeManager() {
		assert true;
		_allBarcodesList = new ArrayList<String>();
	}

	/**
	 * This method returns the ItemsManger singleton
	 * 
	 * @return ItemsManager, the only instance of it
	 * @precondition none
	 * @postcondition The BarcodeManager exists and there is only one.
	 */
	public static BarcodeManager getInstance() {
		assert true;
		if (_instance == null) {
			_instance = new BarcodeManager();
		}
		return _instance;
	}
	
	/**
	 * 
	 * @param barcode The barcode to be added to the manager
	 * @return true if the barcode was added, false otherwise
	 * @precondition the barcode passed must be alphanumeric
	 * @postcondition the list of all barcodes is not empty
	 * @postcondition the passed barcode is in the list
	 */
	public boolean addUserBarcode(String barcode) throws IllegalNameException{
		if(!isAlphanumeric(barcode) ){
			throw new IllegalNameException();
		}
		if(_allBarcodesList.contains(barcode)){
			return false;
		}else{
			_allBarcodesList.add(barcode);
			return true;
		}
	}
	
/**
 * 	Checks if the string contains only alphanumeric
 * characters.
 * @param str a String
 * @return true if the string contains only alphanumeric, false otherwise
 * @precondition none
 * @postcondition none
 */
	private boolean isAlphanumeric(String str) {
		assert true;
	    for (int i=0; i<str.length(); i++) {
	        char c = str.charAt(i);
	        if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a)
	            return false;
	    }

	    return true;
	}
	
	/**
	 * Generates a unique UPC-A valid local use Barcode.
	 * @return Unique Barcode
	 * @precondition none
	 * @postcondition the list of all barcodes has the new unique
	 * barcode in it.
	 */
	public String generateUniqueBarcode() {
		String barcode = generateNewBarcode();
		while (_allBarcodesList.contains(barcode)) {
			barcode = generateNewBarcode();
		}
		_allBarcodesList.add(barcode);
		return barcode;
	}

	/**
	 * Generates a new UPC-A valid local use Barcode.
	 * 
	 * @return Barcode
	 * @precondition none
	 * @postcondition none
	 */
	private String generateNewBarcode() {
		assert true;
		Random r = new Random();
		ArrayList<Integer> barcode = new ArrayList<Integer>();
		barcode.add(4);

		for (int i = 0; i < 11; i++) {
			barcode.add(r.nextInt(10));
		}

		int lastDigit = 0;

		for (int i = 0; i < barcode.size(); i += 2) {
			lastDigit = lastDigit + barcode.get(i);
		}

		lastDigit = lastDigit * 3;

		for (int i = 1; i < barcode.size(); i += 2) {
			lastDigit = lastDigit + barcode.get(i);
		}

		lastDigit = lastDigit % 10;

		if (lastDigit != 0) {
			lastDigit = 10 - lastDigit;
		}

		barcode.add(lastDigit);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < barcode.size(); i++) {
			sb.append(barcode.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * returns the size of the list of all of the barcodes.
	 * @return Size of _allBarcodesList
	 * @precondition none
	 * @postcondition none
	 */
	public int barcodeListSize(){
		return _allBarcodesList.size();
	}
	
	public void storeBarcodeList(){
		HomeInventory hi = Configuration.getInstance().getHomeInventory();
		hi.setStoreBarcodeManagerList(_allBarcodesList);;
	}
	
	public void de_storeBarcodeList(){
		HomeInventory hi = Configuration.getInstance().getHomeInventory();
		_allBarcodesList = hi.getStoreBarcodeManagerList();
	}
}
