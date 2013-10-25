package gui.inventory;

import gui.common.*;
import gui.item.*;
import gui.product.*;

import java.util.*;

import org.omg.CORBA._PolicyStub;

import com.sun.tools.internal.jxc.gen.config.Config;

import data_structures.HomeInventory;
import data_structures.Item;
import data_structures.Product;
import data_structures.ProductContainer;
import data_structures.ProductGroup;
import data_structures.Serializer;
import data_structures.StorageUnit;
import singletons.Configuration;
import singletons.ItemsManager;
import singletons.ProductsManager;
import sun.security.jca.GetInstance.Instance;
import ui_interaction.ItemFacade;
import ui_interaction.ProductFacade;
import ui_interaction.ProductGroupFacade;
import ui_interaction.StorageUnitFacade;

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
		
		HomeInventory homeInventory = Serializer.deserializeHIT();
		Serializer.de_storeManagers();
		
		StorageUnitFacade storageUnitFacade = StorageUnitFacade.getInstance();
		storageUnitFacade.addObserver(this);
		
		ProductGroupFacade productGroupFacade = ProductGroupFacade.getInstance();
		productGroupFacade.addObserver(this);
		
		ItemFacade itemFacade = ItemFacade.getInstance();
		itemFacade.addObserver(this);
		
		ProductFacade productFacade = ProductFacade.getInstance();
		productFacade.addObserver(this);
		
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
	
	private void loadProducts(){
		ArrayList<ProductData> productsList = new ArrayList<ProductData>();
		
		ProductContainer selectedContainer = (ProductContainer) getView().getSelectedProductContainer().getTag();
		StorageUnit storageUnit = selectedContainer.getStorageUnit();
		
		List<Product> produList = selectedContainer.getProducts();
		
		for(Product product: selectedContainer.getProducts()){
			int count = getProductItemCount(product);
			product.getTagData().setCount("" + count);
			productsList.add(product.getTagData());
		}
		ProductData[] products = productsList.toArray(new ProductData[productsList.size()]);
		getView().setProducts(products);
	}
	
	private void loadAllProducts() {
		ArrayList<ProductData> productDatas = new ArrayList<ProductData>();
		HomeInventory homeInventory = Configuration.getInstance().getHomeInventory();
		for(Product p: ProductsManager.getInstance().getAllProducts()){
			ProductData pData = p.getTagData();
			int count = getICountManager(p);
			pData.setCount("" + count);
			productDatas.add(pData);
		}
		ProductData[] products = productDatas.toArray(new ProductData[productDatas.size()]);
		getView().setProducts(products);
		
	}
	
	private int getProductItemCount(Product product){
		int count = 0;
		ProductContainer selectedContainer = (ProductContainer) getView().getSelectedProductContainer().getTag();
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
		
		ProductContainer selectedContainer = (ProductContainer) getView().getSelectedProductContainer().getTag();
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
		ProductContainer su = (ProductContainer) getView().getSelectedProductContainer().getTag();
		return su.canBeDeleted();
		//TODO test this
	}
	
	/**
	 * This method is called when the user selects the "Delete Storage Unit" menu item.
	 */
	@Override
	public void deleteStorageUnit() {
		StorageUnitFacade storageUnitFacade = StorageUnitFacade.getInstance();
		StorageUnit productContainer = (StorageUnit) getView().getSelectedProductContainer().getTag();
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
		ProductGroup productContainer = (ProductGroup) getView().getSelectedProductContainer().getTag();
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
		if(!selectedContainer.getName().equals("root")){
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


	/**
	 * This method is called when the selected item changes.
	 */
	@Override
	public void productSelectionChanged() {
		List<ItemData> itemDataList = new ArrayList<ItemData>();		
		ProductData selectedProduct = getView().getSelectedProduct();
//		if (selectedProduct != null) {
//			Date now = new Date();
//			GregorianCalendar cal = new GregorianCalendar();
//			int itemCount = Integer.parseInt(selectedProduct.getCount());
//			for (int i = 1; i <= itemCount; ++i) {
//				cal.setTime(now);
//				ItemData itemData = new ItemData();
//				itemData.setBarcode(getRandomBarcode());
//				cal.add(Calendar.MONTH, -rand.nextInt(12));
//				itemData.setEntryDate(cal.getTime());
//				cal.add(Calendar.MONTH, 3);
//				itemData.setExpirationDate(cal.getTime());
//				itemData.setProductGroup("Some Group");
//				itemData.setStorageUnit("Some Unit");
//				
//				itemDataList.add(itemData);
//			}
//		}
		if(getView().getSelectedProductContainer().getTag() instanceof HomeInventory){
			loadAllItems();
		}else{
			loadItems();
		}
		//getView().setItems(itemDataList.toArray(new ItemData[0]));
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
		ProductContainer pc = (ProductContainer)getView().getSelectedProductContainer().getTag();
		List<Item> iList = pc.getItems();
		boolean canDelete = true;
		
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
		ProductContainer pc = (ProductContainer)getView().getSelectedProductContainer().getTag();
		Product p = (Product) getView().getSelectedProduct().getTag();
		ProductFacade.getInstance().romoveProduct(p, pc);
		
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

		loadItems();

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

		productContainerSelectionChanged();

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
		//System.out.println("moveProductToContainer");
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
		ItemFacade itemFacade = ItemFacade.getInstance();
		itemFacade.moveItemInTree((Item)itemData.getTag(), (ProductContainer)containerData.getTag());
		//System.out.println("moveItemToContainer");
	}

	@Override
	public void update(Observable o, Object arg) {
		ProductContainerData pcData = getView().getSelectedProductContainer();

		// if (o instanceof StorageUnitFacade || o instanceof ProductGroupFacade ){
		// 	ProductContainer changed = StorageUnitFacade.getInstance().getChangedPC();
		// 	ProductContainerData changedData = changed.getTagData();

		// 	ProductContainerData  rootData = StorageUnitFacade.getInstance().getRootPCData();
		// 	if(changed instanceof StorageUnit)
		// 		getView().insertProductContainer(rootData,changedData,0);
		// }

		getView().setProductContainers(StorageUnitFacade.getInstance().getRootPCData());
		getView().selectProductContainer(pcData);	
	}
	

}

