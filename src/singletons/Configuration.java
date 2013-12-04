package singletons;

import persistance.Persistor;
import model.HomeInventory;
import model.Serializer;

public class Configuration {
	private static Configuration _instance = null;
	
	private HomeInventory _homeInventory;
	private int _dataPersistence = 0;
	private Persistor _persistor = null;
	/**
	 * This method instantiates the instance of Configuration.
	 * It will only ever be called one time.
	 */
	private Configuration(){
		
		//_homeInventory = Serializer.deserializeHIT();
		_homeInventory = new HomeInventory();
	}
	
	/**
	 * This method returns the Configuration singleton
	 * @return Configuration, the only instance of it
	 */
	public static Configuration getInstance(){
		if (_instance == null){
			_instance = new Configuration();
			
		}
		return _instance;
	}
	
	public static HomeInventory getHIT(){
		return getInstance().getHomeInventory();
	}


	public HomeInventory getHomeInventory(){
		return _homeInventory;
	}
	
	public void setPersistor(Persistor p){
		_persistor = p;
	}
	
	public Persistor getPersistor(){
		return _persistor;
	}
	
	public void setHomeInventory(HomeInventory hi){
		_homeInventory = hi;
	}
}
