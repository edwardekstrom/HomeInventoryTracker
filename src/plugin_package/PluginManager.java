package plugin_package;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Stack;

import model.Barcode;
import model.Product;


public class PluginManager {
	private String _fileName = "file.txt";
	private Stack<String> _classNames = new Stack<String>();
	private PluginSuper _plugin;
	
	public PluginManager(){
		loadClassNames();
		loadPlugin();
	}
	
	public String getProduct(Barcode barcode){
		return _plugin.getProductDescription(barcode.getBarcode());
	}
	
	private void loadClassNames(){
	    try {
		    BufferedReader br = new BufferedReader(new FileReader(_fileName));
	        String line = br.readLine();

	        while (line != null) {
	            _classNames.push(line);
	            line = br.readLine();
	        }
	        br.close();
	    } catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void loadPlugin() {
		Class c = null;
		try {
			String className = _classNames.pop();
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		_plugin = null;
		try {
//			Constructor constructor = c.getConstructors()[0];
			Constructor constructor = c.getConstructor(_classNames.getClass());
			_plugin = (PluginSuper) constructor.newInstance(_classNames);
//			_plugin = (PluginInterface)c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}// catch (ALL_THE_EXCEPTIONS! e){
//			e.printStackTrace();
//		}
	}
}
