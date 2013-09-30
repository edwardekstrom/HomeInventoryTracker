package data_structures;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Capchu
 * This Class is used to store the barcodes for scanning.
 */
public class Barcode {

	private String _barcode;
	
	/**
	 *  Creates an instance of Barcode with nothing set
	 */
	public Barcode(String barcode) {
		_barcode = barcode;
	}
	
	/**
	 * Generates a new UPC-A valid local use Barcode.
	 * @return Barcode
	 */
	private String generateNewBarcode(){
		Random r = new Random();
		ArrayList<Integer> barcode = new ArrayList<Integer>();
		barcode.add(4);
		
		for(int i = 0; i < 11; i++){
			barcode.add(r.nextInt(10));
		}
		
		int lastDigit = 0;
		
		for(int i = 0; i<barcode.size(); i+=2){
			lastDigit = lastDigit + barcode.get(i);
		}
		
		lastDigit = lastDigit * 3;
		
		for(int i = 1; i< barcode.size(); i+=2){
			lastDigit = lastDigit + barcode.get(i);
		}
		
		lastDigit = lastDigit % 10;
		
		if(lastDigit!=0){
			lastDigit = 10 - lastDigit;
		}
		
		barcode.add(lastDigit);
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < barcode.size(); i++){
			sb.append(barcode.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * @return the _barcode
	 */
	public String get_barcode() {
		return _barcode;
	}

	/**
	 * @param barcode the _barcode to set
	 * @return true if successful 
	 */
	public boolean set_barcode(String barcode) {
		if(isValidBarcode(barcode)){
			_barcode = barcode;
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @param barcode the _barcode to check
	 * @return true if the barcode is valid 
	 */
	private boolean isValidBarcode(String barcode){
		return true;
	}
	

	
	
}
