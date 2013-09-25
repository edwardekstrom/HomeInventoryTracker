package data_structures;

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
