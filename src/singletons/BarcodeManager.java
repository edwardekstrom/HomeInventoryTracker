package singletons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data_structures.Item;

public class BarcodeManager {

	private static BarcodeManager _instance = null;
	private List<String> _allBarcodesList;

	/**
	 * This method instantiates the instance of BarcodeManger. It will only ever
	 * be called one time.
	 */
	private BarcodeManager() {
		_allBarcodesList = new ArrayList<String>();
	}

	/**
	 * This method returns the ItemsManger singleton
	 * 
	 * @return ItemsManager, the only instance of it
	 */
	public static BarcodeManager getInstance() {
		if (_instance == null) {
			_instance = new BarcodeManager();
		}
		return _instance;
	}
	
	public boolean addUserBarcode(String barcode){
		if(_allBarcodesList.contains(barcode)){
			return false;
		}else{
			_allBarcodesList.add(barcode);
			return true;
		}
	}
	
	/**
	 * Generates a UPC-A valid local use Barcode.
	 * @return Unique Barcode
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
	 */
	private String generateNewBarcode() {
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
	
	public int barcodeListSize(){
		return _allBarcodesList.size();
	}
}
