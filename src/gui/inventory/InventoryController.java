package gui.inventory;

import facade.ItemFacade;
import facade.ProductFacade;
import facade.ProductGroupFacade;
import facade.StorageUnitFacade;
import gui.common.*;
import gui.item.*;
import gui.product.*;

import java.util.*;

import javax.swing.JOptionPane;

import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.Serializer;
import model.StorageUnit;

import org.omg.CORBA._PolicyStub;

import persistance.AbstractPerstistanceFactory;
import persistance.Persistor;

import com.sun.tools.internal.jxc.gen.config.Config;

import singletons.Configuration;
import singletons.ItemsManager;
import singletons.ProductsManager;
import sun.security.jca.GetInstance.Instance;
import gui.inventory.ProductContainerData;

/**
 * Controller class for inventory view.
 */
public class InventoryController extends Controller 
									implements IInventoryController, Observer{
	
	/**
	 * Constructor.
	 *  
	 * @param view Reference to the inventory view
	 */
	public InventoryController(IInventoryView view) {
		super(view);
		
		construct();
		Object[] colours = {"Java Serialization", "Database"};

		int n = JOptionPane.showOptionDialog(null,
		    "Choose Serialization Type ",
		    "",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    colours,
		    colours[0]);
		
		AbstractPerstistanceFactory apf = AbstractPerstistanceFactory.returnFactory(n);
		
		Persistor p = apf.buildPersistor();
		
		Configuration config = Configuration.getInstance();
		config.setPersistor(p);
		
		
		p.loadAll();
		
		StorageUnitFacade storageUnitFacade = StorageUnitFacade.getInstance();
		storageUnitFacade.addObserver(this);
		
		ProductGroupFacade productGroupFacade = ProductGroupFacade.getInstance();
		productGroupFacade.addObserver(this);
		
		ItemFacade itemFacade = ItemFacade.getInstance();
		itemFacade.addObserver(this);
		
		ProductFacade productFacade = ProductFacade.getInstance();
		productFacade.addObserver(this);
		
		ItemFacade.getInstance().registerInventoryController(this);

		update(null, null);
		
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IInventoryView getView() {
		return (IInventoryView)super.getView();
	}

	/**
	 * Loads data into the controller's view.
	 * 
	 *  {@pre None}
	 *  
	 *  {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues() {
		ProductContainerData root = new ProductContainerData();
		
		
		getView().setProductContainers(root);
	}
	

	private ProductContainer getSelectedContainer(){
		IInventoryView view = getView();
		ProductContainerData currentData = view.getSelectedProductContainer();
		Object tag = currentData.getTag();
		ProductContainer selectedContainer = (ProductContainer)currentData.getTag();
		return selectedContainer;
	}

	private void loadProducts(){

		ArrayList<ProductData> productsList = new ArrayList<ProductData>();
		ProductContainer selectedContainer = getSelectedContainer();
		ProductData selectedProduct = getView().getSelectedProduct();

		StorageUnit storageUnit = selectedContainer.getStorageUnit();
		
		List<Product> produList = selectedContainer.getProducts();
		
		for(Product product: selectedContainer.getProducts()){
			int count = getProductItemCount(product);
			product.getTagData().setCount("" + count);
			productsList.add(product.getTagData());
		}
		ProductData[] products = productsList.toArray(new ProductData[productsList.size()]);
		getView().setProducts(products);
		getView().selectProduct(selectedProduct);
	}
	
	private void loadAllProducts() {
		ArrayList<ProductData> productDatas = new ArrayList<ProductData>();
		ProductData productD = getView().getSelectedProduct();
		HomeInventory homeInventory = Configuration.getInstance().getHomeInventory();
		for(Product p: ProductsManager.getInstance().getAllProducts()){
			ProductData pData = p.getTagData();
			int count = getICountManager(p);
			pData.setCount("" + count);
			productDatas.add(pData);
		}
		ProductData[] products = productDatas.toArray(new ProductData[productDatas.size()]);
		getView().setProducts(products);
		getView().selectProduct(productD);
		
	}
	
	private int getProductItemCount(Product product){
		int count = 0;
		ProductContainer selectedContainer = getSelectedContainer();
		for (Item item : selectedContainer.getItems()) {
			if(item.getProduct() == product){
				count++;
			}
		}
		return count;
	}
	
	private int getICountManager(Product product){
		int count = 0;
		for (Item i : ItemsManager.getInstance().getAllItems()) {
			if (i.getProduct() == product) {
				count++;
			}
		}
		return count;
	}
	
	private void loadItems(){
		ArrayList<ItemData> itemDatas = new ArrayList<ItemData>();
		
		ProductContainer selectedContainer = getSelectedContainer();
		StorageUnit storageUnit = selectedContainer.getStorageUnit();
		
		if(getView().getSelectedProduct()!=null){
			Product product = (Product) getView().getSelectedProduct().getTag();

			for (Item item : selectedContainer.getItems()) {
				if(item.getProduct() == product){
					itemDatas.add(item.getTagData());
				}
			}

			getView().setItems(itemDatas.toArray(new ItemData[itemDatas.size()]));
		}else{
			getView().setItems(new ItemData[0]);
		}
	}
	
	private void loadAllItems(){
		if (getView().getSelectedProduct() != null) {
			ArrayList<ItemData> itemDatas = new ArrayList<ItemData>();
			HomeInventory homeInventory = Configuration.getInstance()
					.getHomeInventory();
			Product product = (Product) getView().getSelectedProduct().getTag();
			for (Item i : ItemsManager.getInstance().getAllItems()) {
				if (i.getProduct() == product) {
					itemDatas.add(i.getTagData());
				}
			}
			ItemData[] items = itemDatas
					.toArray(new ItemData[itemDatas.size()]);
			getView().setItems(items);
		}else{
			getView().setItems(new ItemData[0]);
		}
	}

	/**
	 * Sets the enable/disable state of all components in the controller's view.
	 * A component should be enabled only if the user is currently
	 * allowed to interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's view
	 * have been set appropriately.}
	 */
	@Override
	protected void enableComponents() {
		return;
	}
	
	//
	// IInventoryController overrides
	//

	/**
	 * Returns true if and only if the "Add Storage Unit" menu item should be enabled.
	 */
	@Override
	public boolean canAddStorageUnit() {
		if(getView().getSelectedProductContainer().getTag() instanceof HomeInventory)
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if and only if the "Add Items" menu item should be enabled.
	 */
	@Override
	public boolean canAddItems() {
		ProductContainer pc = (ProductContainer) getView().getSelectedProductContainer().getTag();
		if(pc == pc.getStorageUnit())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if and only if the "Transfer Items" menu item should be enabled.
	 */
	@Override
	public boolean canTransferItems() {
		return true;
	}
	
	/**
	 * Returns true if and only if the "Remove Items" menu item should be enabled.
	 */
	@Override
	public boolean canRemoveItems() {
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Storage Unit" menu item should be enabled.
	 */
	@Override
	public boolean canDeleteStorageUnit() {
		ProductContainer su = getSelectedContainer();
		return su.canBeDeleted();
		//TODO test this
	}
	
	/**
	 * This method is called when the user selects the "Delete Storage Unit" menu item.
	 */
	@Override
	public void deleteStorageUnit() {
		StorageUnitFacade storageUnitFacade = StorageUnitFacade.getInstance();
		StorageUnit productContainer = (StorageUnit)getSelectedContainer();
		storageUnitFacade.removeStorageUnit(productContainer);
	}

	/**
	 * Returns true if and only if the "Edit Storage Unit" menu item should be enabled.
	 */
	@Override
	public boolean canEditStorageUnit() {
		return true;
	}

	/**
	 * Returns true if and only if the "Add Product Group" menu item should be enabled.
	 */
	@Override
	public boolean canAddProductGroup() {
		return true;
	}

	/**
	 * Returns true if and only if the "Delete Product Group" menu item should be enabled.
	 */
	@Override
	public boolean canDeleteProductGroup() {
		return canDeleteStorageUnit();
	}

	/**
	 * Returns true if and only if the "Edit Product Group" menu item should be enabled.
	 */
	@Override
	public boolean canEditProductGroup() {
		return true;
	}
	
	/**
	 * This method is called when the user selects the "Delete Product Group" menu item.
	 */
	@Override
	public void deleteProductGroup() {
		ProductGroupFacade pgFacade = ProductGroupFacade.getInstance();
		ProductGroup productContainer = (ProductGroup) getSelectedContainer();
		pgFacade.removeProductGroup(productContainer);
	}

	private Random rand = new Random();
	
	private String getRandomBarcode() {
		Random rand = new Random();
		StringBuilder barcode = new StringBuilder();
		for (int i = 0; i < 12; ++i) {
			barcode.append(((Integer)rand.nextInt(10)).toString());
		}
		return barcode.toString();
	}

	/**
	 * This method is called when the selected item container changes.
	 */
	@Override
	public void productContainerSelectionChanged() {
		
		List<ProductData> productDataList = new ArrayList<ProductData>();		
		ProductContainerData selectedContainer = getView().getSelectedProductContainer();
		if(selectedContainer == null){}else{
		if(!(selectedContainer.getTag() instanceof HomeInventory)){
			loadProducts();
			loadItems();
		}else{
			getView().setContextUnit("All");
		}
		
		getView().setContextSupply("");
		getView().setContextGroup("");
		
		HomeInventory homeInventory = Configuration.getInstance().getHomeInventory();
		if(homeInventory.getStorageUnits().contains(selectedContainer.getTag())){
			getView().setContextUnit(selectedContainer.getName());
		}
		if(selectedContainer.getTag() instanceof ProductGroup){
			ProductGroup pg = (ProductGroup)selectedContainer.getTag();
			String group = pg.getName();
			String threeMonth = Float.toString(pg.getThreeMonthSup().getAmount()); 
			getView().setContextGroup(group);
			getView().setContextSupply(threeMonth);
		}else if(selectedContainer.getTag() instanceof HomeInventory){
			loadAllProducts();
			loadAllItems();
		}
		}
	}


	/**
	 * This method is called when the selected product changes.
	 */
	@Override
	public void productSelectionChanged() {
		update(null, null);
	}

	/**
	 * This method is called when the selected item changes.
	 */
	@Override
	public void itemSelectionChanged() {
		return;
	}

	/**
	 * Returns true if and only if the "Delete Product" menu item should be enabled.
	 */
	@Override
	public boolean canDeleteProduct() {
		Product p = (Product) getView().getSelectedProduct().getTag();
		ProductContainer pc = getSelectedContainer();
		List<Item> iList = pc.getItems();
		boolean canDelete = true;
		
		if(iList != null)
			for(Item item : iList){
				if(item.getProduct() == p){
					canDelete = false;
				}
			}
		
		return canDelete;
	}

	/**
	 * This method is called when the user selects the "Delete Product" menu item.
	 */
	@Override
	public void deleteProduct() {
		ProductContainer pc = getSelectedContainer();
		Product p = (Product) getView().getSelectedProduct().getTag();
		ProductFacade.getInstance().removeProduct(p, pc);
		
		getView().selectProductContainer(pc.getTagData());
		loadProducts();
	}

	/**
	 * Returns true if and only if the "Edit Item" menu item should be enabled.
	 */
	@Override
	public boolean canEditItem() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Edit Item" menu item.
	 */
	@Override
	public void editItem() {
		getView().displayEditItemView();
	}

	/**
	 * Returns true if and only if the "Remove Item" menu item should be enabled.
	 */
	@Override
	public boolean canRemoveItem() {
		try{
			ItemData id = getView().getSelectedItem();
			Item item = (Item)id.getTag();
			
			return true;
			
		}catch (NullPointerException npe){
			return false;
		}
	}

	/**
	 * This method is called when the user selects the "Remove Item" menu item.
	 */
	@Override
	public void removeItem() {
		ItemData id = getView().getSelectedItem();
		Item item = (Item)id.getTag();

		ItemFacade.getInstance().removeItem(item);

		update(null, null);

	}

	/**
	 * Returns true if and only if the "Edit Product" menu item should be enabled.
	 */
	@Override
	public boolean canEditProduct() {
		return true;
	}

	/**
	 * This method is called when the user selects the "Add Product Group" menu item.
	 */
	@Override
	public void addProductGroup() {
		getView().displayAddProductGroupView();
	}
	
	/**
	 * This method is called when the user selects the "Add Items" menu item.
	 */
	@Override
	public void addItems() {
		ProductContainerData pcd = getView().getSelectedProductContainer();
		ProductData pd = getView().getSelectedProduct();
		
		getView().displayAddItemBatchView();

		
		getView().selectProductContainer(pcd);
		getView().selectProduct(pd);

		update(null,null);

	}
	
	/**
	 * This method is called when the user selects the "Transfer Items" menu item.
	 */
	@Override
	public void transferItems() {
		getView().displayTransferItemBatchView();
	}
	
	/**
	 * This method is called when the user selects the "Remove Items" menu item.
	 */
	@Override
	public void removeItems() {
		getView().displayRemoveItemBatchView();
	}

	/**
	 * This method is called when the user selects the "Add Storage Unit" menu item.
	 */
	@Override
	public void addStorageUnit() {
		getView().displayAddStorageUnitView();
	}

	/**
	 * This method is called when the user selects the "Edit Product Group" menu item.
	 */
	@Override
	public void editProductGroup() {
		getView().displayEditProductGroupView();
	}

	/**
	 * This method is called when the user selects the "Edit Storage Unit" menu item.
	 */
	@Override
	public void editStorageUnit() {
		getView().displayEditStorageUnitView();
		update(null, null);
	}

	/**
	 * This method is called when the user selects the "Edit Product" menu item.
	 */
	@Override
	public void editProduct() {
		getView().displayEditProductView();
	}
	
	/**
	 * This method is called when the user drags a product into a
	 * product container.
	 * 
	 * @param productData Product dragged into the target product container
	 * @param containerData Target product container
	 */
	@Override
	public void addProductToContainer(ProductData productData, 
										ProductContainerData containerData) {
		ProductContainerData pcd = getView().getSelectedProductContainer();

		ProductFacade pFacade = ProductFacade.getInstance();
		ProductContainer container = (ProductContainer)containerData.getTag();
		Product product = (Product)productData.getTag();
		pFacade.addProductToContainer(product, container);
		//System.out.println("moveProductToContainer");
		
		
		getView().selectProductContainer(pcd);
		update(null, null);
	}

	/**
	 * This method is called when the user drags an item into
	 * a product container.
	 * 
	 * @param itemData Item dragged into the target product container
	 * @param containerData Target product container
	 */
	@Override
	public void moveItemToContainer(ItemData itemData,
									ProductContainerData containerData) {
		ProductContainerData pcd = getView().getSelectedProductContainer();
		ProductData pd = getView().getSelectedProduct();
		
		ItemFacade itemFacade = ItemFacade.getInstance();
//		itemFacade.moveItemInTree((Item)itemData.getTag(), 
		//(ProductContainer)containerData.getTag());
		//System.out.println("moveItemToContainer");
		Item item = (Item)itemData.getTag();
		itemFacade.dragAndDropItem((Item)itemData.getTag(), item.getContainer()
				                   , (ProductContainer)containerData.getTag());
		
		getView().selectProductContainer(pcd);
		getView().selectProduct(pd);
	}

	@Override
	public void update(Observable o, Object arg) {
		ProductContainerData pcData = getView().getSelectedProductContainer();
		ProductData productData = getView().getSelectedProduct();
		ItemData itemData = getView().getSelectedItem();

		// if (o instanceof StorageUnitFacade || o instanceof ProductGroupFacade ){
		// 	ProductContainer changed = StorageUnitFacade.getInstance().getChangedPC();
		// 	ProductContainerData changedData = changed.getTagData();

		// 	ProductContainerData  rootData = StorageUnitFacade.getInstance().getRootPCData();
		// 	if(changed instanceof StorageUnit)
		// 		getView().insertProductContainer(rootData,changedData,0);
		// }
		productContainerSelectionChanged();
		getView().setProductContainers(StorageUnitFacade.getInstance().getRootPCData());
		getView().selectProductContainer(pcData);	
		getView().selectProduct(productData);
		getView().selectItem(itemData);
	}
	

}

