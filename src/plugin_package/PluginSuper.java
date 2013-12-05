package plugin_package;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import model.Barcode;
import model.Product;


public abstract class PluginSuper {
	private Stack<String> _classNames = new Stack<String>();
	private PluginSuper _nextPlugin;
	
	//make sure that you call _nextPlugin.getProduct(barcode); at the end 
	//of this implementation
	public abstract String getProductDescription(String barcode);
	
	protected PluginSuper getNextPlugin(){
		return _nextPlugin;
	}
	public PluginSuper(Stack<String> names){
		_classNames = names;
		loadPlugin();
	}
	
	private void loadPlugin() {
		Class c = null;
		try {
			String className = _classNames.pop();
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		_nextPlugin = null;
		try {
//			Constructor constructor = c.getConstructors()[0];
			Constructor constructor = c.getConstructor(_classNames.getClass());
			_nextPlugin = (PluginSuper) constructor.newInstance(_classNames);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}