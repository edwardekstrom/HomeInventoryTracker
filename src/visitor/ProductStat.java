package visitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Item;
import model.Product;
import model.UnitSize;

public class ProductStat {
	private GregorianCalendar _startDate = new GregorianCalendar();
	private GregorianCalendar _endDate = new GregorianCalendar();
	private float _currentSupply = 0f;
	private float _avgSupply = 0f;
	private float _avgAgeUsed = 0f;
	private float _avgAgeCurrent = 0f;
	
	private float _supplyUsed = 0f;
	private float _supplyAdded = 0f;
	private Product _product = null;
	private int _countOfItems = 0;
	private int _countOfItemsUsed = 0;
	private List<Item> _items = new ArrayList<Item>();
	private ItemLifespan _currentItemLife;
	private float _countOfItemsAlive = 0f;
	private int _usedAgeMax = 0;
	private int _currentAgeMax = 0;
	
	private Map<GregorianCalendar, Float> _amountMap = new HashMap<GregorianCalendar, Float>();
	
	public ProductStat(Product p, int months) {
		setTheProduct(p);
		_startDate.add(Calendar.MONTH, - months);
		
		long currentMSeconds = _startDate.getTimeInMillis();
		while(currentMSeconds < _endDate.getTimeInMillis()){
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(currentMSeconds);
			
			_amountMap.put(gc, 0f);
			
			currentMSeconds += (24l*60l*60l*1000l);
		}
	}
	
	/**
	 * @return the _supplyUsed
	 */
	public float getSupplyUsed() {
		return _supplyUsed;
	}
	/**
	 * @param _supplyUsed the _supplyUsed to set
	 */
	public void setSupplyUsed(float _supplyUsed) {
		this._supplyUsed = _supplyUsed;
	}

	/**
	 * @return the _product
	 */
	public Product getTheProduct() {
		return _product;
	}

	/**
	 * @param _product the _product to set
	 */
	public void setTheProduct(Product _product) {
		this._product = _product;
	}
	
	public void addItem(Item i){
		_countOfItems++;
		_currentItemLife = new ItemLifespan(i, _startDate, _endDate);
		updateAvgSupply(i);
		updateUsedSupply(i);
		updateAddedSupply(i);
		updateAmountMap(i);
	}
	
	private void updateAvgSupply(Item i){
		float newAvg = i.getProduct().getSizeAmount() * _currentItemLife.getTotalDaysAlive();
		_avgSupply = (_avgSupply + newAvg)/_countOfItems;
	}
	
	public float getMinSupply(){
		float min = Float.MAX_VALUE;
		for (GregorianCalendar day : _amountMap.keySet()){
			if(_amountMap.get(day) < min){
				min = _amountMap.get(day);
			}
		}
		return min;
	}
	
	public GregorianCalendar get_startDate() {
		return _startDate;
	}

	public void set_startDate(GregorianCalendar _startDate) {
		this._startDate = _startDate;
	}

	public GregorianCalendar get_endDate() {
		return _endDate;
	}

	public void set_endDate(GregorianCalendar _endDate) {
		this._endDate = _endDate;
	}

	public float get_currentSupply() {
		return _currentSupply;
	}

	public void set_currentSupply(float _currentSupply) {
		this._currentSupply = _currentSupply;
	}



	public float get_avgSupply() {
		return _avgSupply;
	}

	public void set_avgSupply(float _avgSupply) {
		this._avgSupply = _avgSupply;
	}

	public float get_avgAgeUsed() {
		return _avgAgeUsed;
	}

	public void set_avgAgeUsed(float _avgAgeUsed) {
		this._avgAgeUsed = _avgAgeUsed;
	}

	public float get_avgAgeCurrent() {
		return _avgAgeCurrent;
	}

	public void set_avgAgeCurrent(float _avgAgeCurrent) {
		this._avgAgeCurrent = _avgAgeCurrent;
	}

	public float get_supplyUsed() {
		return _supplyUsed;
	}

	public void set_supplyUsed(float _supplyUsed) {
		this._supplyUsed = _supplyUsed;
	}

	public float get_supplyAdded() {
		return _supplyAdded;
	}

	public void set_supplyAdded(float _supplyAdded) {
		this._supplyAdded = _supplyAdded;
	}

	public Product get_product() {
		return _product;
	}

	public void set_product(Product _product) {
		this._product = _product;
	}

	public int get_countOfItems() {
		return _countOfItems;
	}

	public void set_countOfItems(int _countOfItems) {
		this._countOfItems = _countOfItems;
	}

	public int get_countOfItemsUsed() {
		return _countOfItemsUsed;
	}

	public void set_countOfItemsUsed(int _countOfItemsUsed) {
		this._countOfItemsUsed = _countOfItemsUsed;
	}

	public List<Item> get_items() {
		return _items;
	}

	public void set_items(List<Item> _items) {
		this._items = _items;
	}

	public ItemLifespan get_currentItemLife() {
		return _currentItemLife;
	}

	public void set_currentItemLife(ItemLifespan _currentItemLife) {
		this._currentItemLife = _currentItemLife;
	}

	public float get_countOfItemsAlive() {
		return _countOfItemsAlive;
	}

	public void set_countOfItemsAlive(float _countOfItemsAlive) {
		this._countOfItemsAlive = _countOfItemsAlive;
	}

	public int get_usedAgeMax() {
		return _usedAgeMax;
	}

	public void set_usedAgeMax(int _usedAgeMax) {
		this._usedAgeMax = _usedAgeMax;
	}

	public int get_currentAgeMax() {
		return _currentAgeMax;
	}

	public void set_currentAgeMax(int _currentAgeMax) {
		this._currentAgeMax = _currentAgeMax;
	}

	public Map<GregorianCalendar, Float> get_amountMap() {
		return _amountMap;
	}

	public void set_amountMap(Map<GregorianCalendar, Float> _amountMap) {
		this._amountMap = _amountMap;
	}

	public float getMaxSupply(){
		float max = 0;
		for (GregorianCalendar day : _amountMap.keySet()){
			if(_amountMap.get(day) > max){
				max = _amountMap.get(day);
			}
		}
		return max;
	}
	
	private void updateUsedSupply(Item i){
		if(i.getExitTime() != null){
			_supplyUsed += i.getProduct().getSizeAmount();
			updateUsedAgeAvg(i);
			updateUsedAgeMax(i);
		}else{
			updateCurrentAgeAvg(i);
			updateCurrentAgeMax(i);
		}
	}
	
	private void updateAddedSupply(Item i){
		if(_currentItemLife.getBirthday() != _startDate){
			_supplyAdded += i.getProduct().getSizeAmount();
		}
	}
	
	private void updateUsedAgeAvg(Item i){
		_countOfItemsUsed++;
		_avgAgeUsed = (_avgAgeUsed + _currentItemLife.getTotalDaysAliveOutside())/_countOfItemsUsed;
	}
	
	private void updateUsedAgeMax(Item i){
		if(_currentItemLife.getTotalDaysAliveOutside() > _usedAgeMax){
			_usedAgeMax = _currentItemLife.getTotalDaysAliveOutside();
		}
	}
	
	private void updateCurrentAgeAvg(Item i){
		_countOfItemsAlive++;
		_avgAgeCurrent = (_avgAgeCurrent + _currentItemLife.getTotalDaysAliveOutside())/_countOfItemsAlive;
	}
	
	private void updateCurrentAgeMax(Item i){
		if(_currentItemLife.getTotalDaysAliveOutside() > _currentAgeMax){
			_currentAgeMax = _currentItemLife.getTotalDaysAliveOutside();
		}
	}
	
	private void updateAmountMap(Item i){
		for (GregorianCalendar day : _amountMap.keySet()){
			if(_currentItemLife.isAlive(day)){
				float newValue = _amountMap.get(day) + i.getProduct().getSizeAmount();
			}
		}
	}
	
	private void updateCurrentSupply(Item i){
		
	}
	
}
