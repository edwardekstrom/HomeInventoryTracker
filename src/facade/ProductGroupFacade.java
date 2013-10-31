/**
 * 
 */
package facade;

import gui.inventory.ProductContainerData;

import java.util.List;
import java.util.Observable;
import java.util.Observer;



import model.ProductContainer;
import model.ProductGroup;
import model.StorageUnit;
import singletons.Configuration;

/**
 * @author Capchu
 *
 */
public class ProductGroupFacade extends Observable {

	
	private static ProductGroupFacade _instance = null;
	private Configuration config;
	
	private ProductGroupFacade(){
		config = Configuration.getInstance();
	}
	
	public static ProductGroupFacade getInstance(){
		if (_instance == null){
			_instance = new ProductGroupFacade();
		}
		return _instance;
	}
	
	/**
	 * Add the given ProductGroup to the tree
	 * @param productGroup
	 */
	public void addProductGroup(ProductGroup productGroup){
		ProductContainer parent = productGroup.getContainer();
		parent.addProductGroup(productGroup);
		
		ProductContainerData pcData = new ProductContainerData();
		pcData.setName(productGroup.getName());
		pcData.setTag(productGroup);
		
		parent.getTagData().addChild(pcData);
		productGroup.setTagData(pcData);
		
		setChanged();
		notifyObservers(this);
		
	}

	public void removeProductGroup(ProductGroup toRemove){
		ProductContainerData pcData = toRemove.getTagData();
		
		toRemove.getContainer().removeProductGroup(toRemove);

		
		setChanged();
		notifyObservers(this);
		
	}

	public boolean canCreateChildWithName(ProductContainer productContainer, String name) {
		return productContainer.canAddProductGroupWithName(name);
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public void notifyObservers(Object arg) {
		super.notifyObservers(arg);
	}

	@Override
	protected synchronized void setChanged() {
		super.setChanged();
	}

	
}
