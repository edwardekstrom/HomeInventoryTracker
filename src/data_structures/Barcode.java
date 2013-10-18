package data_structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * @author Capchu
 * This Class is used to store the barcodes for scanning.
 */
public class Barcode implements Serializable{

	private String _barcode;
	
	/**
	 * A Constructor to hold a barcode
	 *
	 * @post this.code() == <code>code</code>
	 * @param <code>code</code> A string containing the code to store.
	 * @throws <code>IllegalArgumentException</code> when the given code is invalid
	 *
	 */
	public Barcode(String barcode) throws IllegalArgumentException {
		setBarcode(barcode);
	}
	
	/**
     * Empty Constructor, Auto Generates a barcode
     * 
     * @post Barcode.validate(this.code()) == true;
     */
    public Barcode() throws IllegalArgumentException{
        setBarcode(this.generateCode());
    }

	/**
	 * @return the _barcode
	 */
	public String getBarcode() {
		return _barcode;
	}

	/**
	 * @param barcode the _barcode to set
	 * @return true if successful 
	 */
	public void setBarcode(String barcode) throws IllegalArgumentException{
		if(validate(barcode)){
			_barcode = barcode;
		}else{
			throw new IllegalArgumentException("Invalid Barcode");
		}
	}

	/**
     * Generate a check digit for a UPA
     * 
     * @param digits, 10 digits to generate the check digit
     * @return int check digit
     */
    public static int checkDigit(int[] digits){
        
        assert(digits.length == 11);
        
        int odds = 0;
        int evens = 0;
        for(int i=0;i<digits.length ;i++){
            if(i % 2 == 0){
                evens += digits[i];
            }
            else{
                odds += digits[i];
            }
        }
        odds *= 3;
        odds += evens;
        
        odds %= 10;
        if(!(odds == 0))
            odds = 10 - odds;
        return odds;
    }
	
	/**
	 * A Static method to test whether or not a barcode is valid
	 *
	 * @return wither a string is a valid barcode
	 */
	public static boolean validate(String code){
        if (code.matches("[0-9]{12}")){
            char[] stringDigits = code.toCharArray();
            
           
            int[] digits = new int[stringDigits.length];

            int i = 0;
            for(char digit: stringDigits){
                digits[i++] = Character.getNumericValue(digit);
                
            }
            int checkDigit = Barcode.checkDigit(Arrays.copyOfRange(digits,0,11));
           
            return checkDigit == digits[11];
        }
        else{
            
            return false;
        }
	}

	/**
     * A barcode gernator
     * 
     * @invariant this.generateCode() != this.generateCode()
     * @invariant this.validate(this.generateCode()) == true
     * @return a valid barcode string
     */
    public static String generateCode(){
        
        int[] mid = new int[11];
        mid[0] = 4;
        int checkDigit;
        
        Random randomGenerator = new Random();
        for(int i=1;i < 11;i++)
            mid[i] = randomGenerator.nextInt(10);
        
                
        checkDigit = Barcode.checkDigit(mid);
        
        String rString = "";
        for(int i=0;i<11;i++)
            rString += Integer.toString(mid[i]);
        rString += checkDigit;
        
        return rString;
    }

}
