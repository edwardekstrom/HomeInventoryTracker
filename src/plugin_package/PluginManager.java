package plugin_package;

import model.Barcode;
import model.Product;

/**
 * @author edwardekstrom
 *
 */
/**
 * @author edwardekstrom
 *
 */
public class PluginManager {
	
	
	/**
	 * The filename
	 */
	private String _fileName;
	/**
	 * Class Names
	 */
	private String[] _classNames;

	
	/**
	 * Constructs a new PluginManager with _filename
	 * set to "" and _classNames set to a new array
	 * of strings of length 10.
	 */
	public PluginManager() {
		_fileName = "";
		_classNames =  new String[10];
	}
	
	/**
	 * Constructs a new PluginManager with _filename
	 * set to the passed in param filename, and _classNames
	 *  set to the passed in param classnames.
	 * @param fileName the filename
	 * @param classNames the class names
	 */
	public PluginManager(String fileName, String[] classNames) {
		_fileName = fileName;
		_classNames =  classNames;
	}
	
	
	/**
	 * Returns the product associated with the 
	 * passed in barcode if one of the plugins
	 * can find it on their website, null otherwise.
	 * @param barcode the product barcode we're looking for
	 * @return
	 */
	public Product getProduct(Barcode barcode){
		return null;
	}
	
	
	
	/**
	 * Returns the class names arary
	 * @return _classNames
	 */
	private String[] getClassNames(){
		return _classNames;
	}
	
	
	
	/**
	 * Scrapes the website of each plugin
	 * in the chain and returns the Product
	 * associated with the barcode if one of
	 * them can find it, null otherwise.
	 * @param barcode The barcode
	 * @param classNames The class names
	 * @return
	 */
	private Product runPlugin(Barcode barcode, String[] classNames){
		return null;
	}
}
