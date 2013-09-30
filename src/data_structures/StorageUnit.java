package data_structures;


/**\
 * @author nRitchie
 * An implementation of ProductContainer for storage spaces
 */
public class StorageUnit extends ProductContainer{


	@Override
	public void moveProduct(Product product, ProductContainer productContainer) {
		ProductContainer containerWithProduct = this.productGroupWithProduct(product);
		productContainer.addProduct(product);
		for (int i = 0; i < containerWithProduct.getItems().size() ; i++) {
			Item itemToTransfer = containerWithProduct.getItems().get(i);
//			productContainer.addItem(item);
		}
	}

}
