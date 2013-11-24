package plugin_package;

import model.Barcode;
import model.Product;

public interface IProductIdentifier {

	/**
	 * Returns the product associated with the 
	 * passed in barcode if one of the plugins
	 * can find it on their website, null otherwise.
	 * @param barcode the product barcode we're looking for
	 * @return
	 */
	public Product getProduct(Barcode barcode);
}
