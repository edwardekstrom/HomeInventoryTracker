package singletons;

import model.HomeInventory;
import model.Serializer;

public class Configuration {
	private static Configuration _instance = null;
	
	private HomeInventory _homeInventory;
	private int _dataPersistence = 0;
	/**
	 * This method instantiates the instance of Configuration.
	 * It will only ever be called one time.
	 */
	private Configuration(){
		
		_homeInventory = Serializer.deserializeHIT();
		
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
}
